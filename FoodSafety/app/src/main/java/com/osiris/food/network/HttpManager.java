package com.osiris.food.network;


import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.JsonObject;
import com.osiris.food.utils.ThreadPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import me.jessyan.autosize.utils.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

import static com.osiris.food.network.ApiRequestTag.API_HOST;

public final class HttpManager {
	private static final OkHttpClient requestClient;
	private static final OkHttpClient downloadClient;
	private final static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded");
	public final static String TOKEN_PREFIX = "jwt ";
	private final static String EMPTY_JSON = new JsonObject().toString();
	private final static Handler handler = new Handler(Looper.getMainLooper());

	static {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS);
		builder.addInterceptor(new LogInterceptor());

		requestClient = builder.build();
		downloadClient = new OkHttpClient.Builder()
				.connectTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.build();
	}

	private static String checkUrlPrefix(String url) {
		if (!url.startsWith("http")) {
			url = API_HOST + url;
		}
		return url;
	}

	private static <T> void executeCall(final Request request, final OnResponse<T> onResponse) {
		if (onResponse == null) {
			throw new NullPointerException("OnResponse is null!");
		}
		final Call call = requestClient.newCall(request);
		final String reqUrl = request.url().toString();
		onResponse.onStart();
		ThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				Response response = null;
				try {
					response = call.execute();
					if (response == null) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								onResponse.onFailed(-1, "response null", reqUrl);
								onResponse.onCompleted();
							}
						});
						return;
					}
					if (response.isSuccessful()) {
						String result = response.body().string();
						onResponse.setSuccess(true);
						final T t = onResponse.analyseResult(result);
						handler.post(new Runnable() {
							@Override
							public void run() {
								onResponse.onSuccess(t);
								onResponse.onCompleted();
							}
						});
					} else {
						final int code = response.code();
						final String msg = response.message();
						final String body = response.body().string();
						handler.post(new Runnable() {
							@Override
							public void run() {
								onResponse.onFailed(code, msg, reqUrl);
								onResponse.onErrorMsgBody(body);
								onResponse.onCompleted();
							}
						});
					}
				} catch (final IOException e) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							onResponse.onFailed(-1, e.getMessage(), reqUrl);
							onResponse.onCompleted();
						}
					});
				} finally {
					if (response != null && response.body() != null) {
						response.body().close();
					}
				}
			}
		});
	}

	private static void addHeadersToRequest(ArrayMap<String, String> headers, Request.Builder builder) {
		if (headers != null && headers.size() > 0) {
			Set<Map.Entry<String, String>> entrySet = headers.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	private static <T> void basePost(String url, ArrayMap<String, String> headers, JsonObject params, Object tag,
	                                 OnResponse<T> onResponse) {
		final RequestBody reqBody = RequestBody.create(MEDIA_TYPE_JSON, params == null ? EMPTY_JSON : params.toString
				());
		Request.Builder builder = new Request.Builder().url(checkUrlPrefix(url));
		if (tag != null) {
			if (tag instanceof JsonObject) {
				throw new IllegalArgumentException("tag cannot be JsonObject.");
			}
			builder.tag(tag.getClass().getName());
		}
		addHeadersToRequest(headers, builder);
		Request request = builder.post(reqBody).build();
		executeCall(request, onResponse);
	}

	public static <T> void postForm(String url, ArrayMap<String, String> params, Object tag, OnResponse<T> onResponse) {
		// 构造Multipart请求体，并设置类型为Form
		MultipartBody.Builder multiBuilder = new MultipartBody.Builder().setType(MultipartBody
				.FORM);
		// params是存放参数的ArrayMap
		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		// 遍历参数集合，添加到请求体
		for (Map.Entry<String, String> entry : entrySet) {
			File file = new File(entry.getValue());
			if (file.exists()) {
				// addFormDataPart方法三个参数的方法，分别对应要提交的key，value和文件对象
				multiBuilder.addFormDataPart(entry.getKey(), entry.getValue(), RequestBody.create
						(null, file));
			} else {
				// addFormDataPart方法三个参数的方法，分别对应要提交的key，value
				multiBuilder.addFormDataPart(entry.getKey(), entry.getValue());
			}
		}
		// 构造Request对象，方法为POST
		Request.Builder reqBuilder = new Request.Builder().url(checkUrlPrefix(url)).post(multiBuilder.build());
		// 根据需要添加的header信息
		reqBuilder.addHeader("Authorization", TOKEN_PREFIX + GlobalParams.token);
		// 设置tag
		if (tag != null) {
			reqBuilder.tag(tag.getClass().getName());
		}
		Request request = reqBuilder.build();
		executeCall(request, onResponse);
	}

	private static <T> void post(String url, ArrayMap<String, String> headers, JsonObject params, Object tag,
	                             OnResponse<T> onResponse) {
		if (headers == null) {
			headers = new ArrayMap<>();
		}
		headers.put("Authorization", TOKEN_PREFIX + GlobalParams.token);
		basePost(url, headers, params, tag, onResponse);
	}
	private static <T> void postNotoken(String url, ArrayMap<String, String> headers, JsonObject params, Object tag,
	                             OnResponse<T> onResponse) {
		if (headers == null) {
			headers = new ArrayMap<>();
		}
		//headers.put("Authorization", TOKEN_PREFIX + GlobalParams.token);
		basePost(url, headers, params, tag, onResponse);
	}

	public static <T> void post(String url, JsonObject params, Object tag, OnResponse<T> onResponse) {
		post(url, null, params, tag, onResponse);
	}

	public static <T> void postNotoken(String url, JsonObject params, Object tag, OnResponse<T> onResponse) {
		postNotoken(url, null, params, tag, onResponse);
	}
	public static <T> void post(String url, Object tag, OnResponse<T> onResponse) {
		post(url, null, null, tag, onResponse);
	}

	public static <T> void postImageForm(String url, ArrayMap<String, String> params, Object tag, OnResponse<T>
			onResponse) {
		// 构造Multipart请求体，并设置类型为Form
		MultipartBody.Builder multiBuilder = new MultipartBody.Builder().setType(MultipartBody
				.FORM);
		// params是存放参数的ArrayMap
		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		// 遍历参数集合，添加到请求体
		for (Map.Entry<String, String> entry : entrySet) {
			File file = new File(entry.getValue());
			if (file.exists()) {
				// addFormDataPart方法三个参数的方法，分别对应要提交的key，value和文件对象
				multiBuilder.addFormDataPart(entry.getKey(), entry.getValue(), RequestBody.create
						(MediaType.parse("image/png"), file));
			} else {
				// addFormDataPart方法三个参数的方法，分别对应要提交的key，value
				multiBuilder.addFormDataPart(entry.getKey(), entry.getValue());
			}
		}
		// 构造Request对象，方法为POST
		Request.Builder reqBuilder = new Request.Builder().url(checkUrlPrefix(url)).post(multiBuilder.build());
		// 根据需要添加的header信息
		reqBuilder.addHeader("Authorization", TOKEN_PREFIX + GlobalParams.token);
		// 设置tag
		if (tag != null) {
			reqBuilder.tag(tag.getClass().getName());
		}
		Request request = reqBuilder.build();
		executeCall(request, onResponse);
	}

	public static <T> void getNoToken(String url, ArrayMap<String, String> headers, ArrayMap<String, String> params,
	                                  Object tag, final OnResponse<T> onResponse) {
		String url1 = handleUrl(checkUrlPrefix(url), params);
		Log.i("HttpManager", "--->>> url = " + url1);
		Request.Builder builder = new Request.Builder().url(url1);
		if (tag != null) {
			if (tag instanceof ArrayMap) {
				throw new IllegalArgumentException("tag cannot be ArrayMap.");
			}
			builder.tag(tag.getClass().getName());
		}
		addHeadersToRequest(headers, builder);
		Request request = builder.get().build();
		executeCall(request, onResponse);
	}

	private static String handleUrl(String url, ArrayMap<String, String> params) {
		if (params == null || params.size() <= 0) {
			return url;
		}
		StringBuilder sb = new StringBuilder(url);
		sb.append("?");
		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		for (Map.Entry<String, String> entry : entrySet) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static <T> void get(String url, ArrayMap<String, String> headers, ArrayMap<String, String> params, Object
			tag, final OnResponse<T> onResponse) {
		if (headers == null) {
			headers = new ArrayMap<>();
		}
		headers.put("Authorization", TOKEN_PREFIX + GlobalParams.token);
		Log.i("HttpManager", "--->>> Authorization = " + headers.get("Authorization"));

		getNoToken(url, headers, params, tag, onResponse);
	}

	public static <T> void get(String url, ArrayMap<String, String> params, Object tag, OnResponse<T> response) {
		get(url, null, params, tag, response);
	}

	public static <T> void get(String url, Object tag, OnResponse<T> response) {
		get(url, null, null, tag, response);
	}

	public static <T> void put(String url, JsonObject params, Object tag, OnResponse<T> onResponse) {
		final RequestBody reqBody = RequestBody.create(MEDIA_TYPE_JSON, params == null ?
				EMPTY_JSON : params
				.toString());
		Request.Builder builder = new Request.Builder().url(checkUrlPrefix(url));
		if (tag != null) {
			if (tag instanceof JsonObject) {
				throw new IllegalArgumentException("tag cannot be JsonObject.");
			}
			builder.tag(tag.getClass().getName());
		}
		builder.addHeader("Authorization", TOKEN_PREFIX + GlobalParams.token);
		Request request = builder.put(reqBody).build();
		executeCall(request, onResponse);
	}

	public static <T> void delete(String url, Object tag, OnResponse<T> onResponse) {
		Request.Builder builder = new Request.Builder().url(checkUrlPrefix(url));
		if (tag != null) {
			if (tag instanceof JsonObject) {
				throw new IllegalArgumentException("tag cannot be JsonObject.");
			}
			if (tag instanceof ArrayMap) {
				throw new IllegalArgumentException("tag cannot be ArrayMap.");
			}
			builder.tag(tag.getClass().getName());
		}
		builder.addHeader("Authorization", TOKEN_PREFIX + GlobalParams.token);
		Request request = builder.delete().build();
		executeCall(request, onResponse);
	}

	public static <T> void delete(String url, JsonObject params, Object tag, OnResponse<T> onResponse) {
		Request.Builder builder = new Request.Builder().url(checkUrlPrefix(url));
		if (tag != null) {
			if (tag instanceof JsonObject) {
				throw new IllegalArgumentException("tag cannot be JsonObject.");
			}
			if (tag instanceof ArrayMap) {
				throw new IllegalArgumentException("tag cannot be ArrayMap.");
			}
			builder.tag(tag.getClass().getName());
		}
		builder.addHeader("Authorization", TOKEN_PREFIX + GlobalParams.token);
		final RequestBody reqBody = RequestBody.create(MEDIA_TYPE_JSON, params == null ? EMPTY_JSON : params.toString
				());
		Request request = builder.delete(reqBody).build();
		executeCall(request, onResponse);
	}

	public static <T> void patch(String url, JsonObject params, Object tag, OnResponse<T> onResponse) {
		final RequestBody reqBody = RequestBody.create(MEDIA_TYPE_JSON, params == null ?
				EMPTY_JSON : params
				.toString());
		Request.Builder builder = new Request.Builder().url(checkUrlPrefix(url));
		if (tag != null) {
			if (tag instanceof JsonObject) {
				throw new IllegalArgumentException("tag cannot be JsonObject.");
			}
			builder.tag(tag.getClass().getName());
		}
		builder.header("Authorization", TOKEN_PREFIX + GlobalParams.token);
		Request request = builder.patch(reqBody).build();
		executeCall(request, onResponse);
	}

	public static void download(String downloadUrl, Object tag, final String downloadPath, final String fileName,
	                            final DownloaderListener listener) {
		OkHttpClient client = downloadClient.newBuilder()
				.addInterceptor(new Interceptor() {
					@Override
					public Response intercept(Chain chain) throws IOException {
						Response originalResponse = chain.proceed(chain.request());
						return originalResponse
								.newBuilder()
								.body(new ProgressResponseBody(originalResponse.body(), listener))
								.build();
					}
				}).build();
		File path = new File(downloadPath);
		final File file = new File(downloadPath, fileName);
		if (!path.exists()) {
			path.mkdirs();
		}
		Request.Builder builder = new Request.Builder().url(downloadUrl);
		if (tag != null) {
			if (tag instanceof JsonObject) {
				throw new IllegalArgumentException("tag cannot be JsonObject.");
			}
			if (tag instanceof ArrayMap) {
				throw new IllegalArgumentException("tag cannot be ArrayMap.");
			}
			builder.tag(tag.getClass().getName());
		}
		Request request = builder.build();
		listener.start();
		//发送异步请求
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				listener.onFail(e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				//将返回结果转化为流，并写入文件
				int len;
				byte[] buf = new byte[1024];
				InputStream inputStream = response.body().byteStream();
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				while ((len = inputStream.read(buf)) != -1) {
					fileOutputStream.write(buf, 0, len);
				}
				fileOutputStream.flush();
				fileOutputStream.close();
				inputStream.close();
			}
		});
	}


	public static void cancel(Object tag) {
		if (tag == null) {
			requestClient.dispatcher().cancelAll();
			return;
		}
		List<Call> calls = requestClient.dispatcher().queuedCalls();
		for (int i = calls.size() - 1; i >= 0; i--) {
			Call call = calls.get(i);
			if (call.request().tag().equals(tag.getClass().getName())) {
				call.cancel();
			}
		}
	}

	public abstract static class OnResponse<T> {
		private boolean success = false;

		public void onStart() {
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public boolean isSuccess() {
			return success;
		}

		/**
		 * 解析服务器返回的数据,非主线程
		 *
		 * @param body 服务器返回的字符串
		 * @return 所需要被解析成的类型
		 */
		public abstract T analyseResult(String body);

		/**
		 * 此方法只有服务器成功返回时回调
		 *
		 * @param result
		 */
		public abstract void onSuccess(T result);

		public void onCompleted() {
		}

		public void onErrorMsgBody(String body) {

		}

		/**
		 * 网络请求失败的回调，包括网络错误，请求失败等
		 *
		 * @param code
		 * @param msg
		 */
		public void onFailed(int code, String msg, String url) {
			switch (code) {
				case -1:
					//PromptManager.showToast(R.string.request_error);
					break;
				case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
					//PromptManager.showToast(R.string.network_timeout);
					break;
				case HttpURLConnection.HTTP_INTERNAL_ERROR:
					//PromptManager.showToast(R.string.serverproblemmsg);
					break;
				case HttpURLConnection.HTTP_BAD_REQUEST:
					//PromptManager.showToast(R.string.request_error);
					break;
				case HttpURLConnection.HTTP_FORBIDDEN:
					//PromptManager.showToast(R.string.has_no_permission);
				default:
					//PromptManager.showToast(R.string.request_failed);
					break;
			}
		}
	}

	public static class LogInterceptor implements Interceptor {
		@Override
		public Response intercept(Chain chain) throws IOException {
			Request request = chain.request();
			Response response = chain.proceed(request);
			ResponseBody responseBody = response.body();
			String content = responseBody.string();
			StringBuilder logBuilder = new StringBuilder();
			logBuilder.append("Request:");
			logBuilder.append("\n\t");
			logBuilder.append(request.url());
			logBuilder.append("\n");
			logBuilder.append("Response:");
			logBuilder.append("\n\t");
			logBuilder.append("code=").append(response.code()).append(",");
			logBuilder.append("message=").append(response.message()).append(",");
			logBuilder.append("\n\t");
			logBuilder.append("body=").append(content);
			LogUtils.d(logBuilder.toString());
			return response
					.newBuilder()
					.body(ResponseBody.create(responseBody.contentType(), content))
					.build();
		}
	}

	private static class ProgressResponseBody extends ResponseBody {
		private final ResponseBody responseBody;
		private final DownloaderListener downloaderListener;
		private BufferedSource bufferedSource;

		public ProgressResponseBody(ResponseBody responseBody, DownloaderListener
				downloaderListener) {
			this.responseBody = responseBody;
			this.downloaderListener = downloaderListener;
		}

		@Override
		public MediaType contentType() {
			return responseBody.contentType();
		}


		@Override
		public long contentLength() {
			return responseBody.contentLength();
		}

		@Override
		public BufferedSource source() {
			if (bufferedSource == null) {
				bufferedSource = Okio.buffer(source(responseBody.source()));
			}
			return bufferedSource;
		}

		private Source source(Source source) {
			return new ForwardingSource(source) {
				long totalBytesRead = 0L;
				long startTime = System.nanoTime();

				@Override
				public long read(Buffer sink, long byteCount) throws IOException {
					long bytesRead = super.read(sink, byteCount);
					totalBytesRead += bytesRead != -1 ? bytesRead : 0;
					float kb = totalBytesRead / 1024;
					double second = (System.nanoTime() - startTime) * Math.pow(10, -9);
					downloaderListener.update(totalBytesRead, responseBody.contentLength(), kb / second, bytesRead ==
							-1);
					return bytesRead;
				}
			};
		}
	}

	public interface DownloaderListener {
		void start();

		/**
		 * @param bytesRead     已下载字节数
		 * @param contentLength 总字节数
		 * @param done          是否下载完成
		 */
		void update(long bytesRead, long contentLength, double speed, boolean done);

		void onFail(Exception e);
	}
}

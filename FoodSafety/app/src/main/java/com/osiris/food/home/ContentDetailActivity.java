package com.osiris.food.home;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.ContenDetail;
import com.osiris.food.model.LessonDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ContentDetailActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.iv_content)
	ImageView ivContent;
	@BindView(R.id.tv_content)
	TextView tvContent;
	private int id = 0;
	private boolean isLesson;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_content_detail;
	}

	@Override
	public void init() {

		id = getIntent().getIntExtra("id", 0);
		isLesson = getIntent().getBooleanExtra("lesson", false);
		getData();

		//	uploadTask();

	}

	@OnClick({R.id.rl_back})
	void onClik(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}


	private void getData() {

		String url ;
		if (isLesson){
			url =  ApiRequestTag.API_HOST + "/api/v1/lessons/" + id;
		}else {
			url = ApiRequestTag.API_HOST + "/api/v1/contents/" + id;
		}
		LogUtils.d("zkf url :" + url);

		showLoadDialog();
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("success")) {


					if (isLesson){
						LessonDetail.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(), LessonDetail.DataBean.class);
						tvContent.setText(Html.fromHtml(dataBean.getSummary()));
						tvTitle.setText(dataBean.getName());

					}else {
						ContenDetail.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(), ContenDetail.DataBean.class);
						tvContent.setText(Html.fromHtml(dataBean.getContent()));

						LogUtils.d("zkf dataBean.getThumb():" + dataBean.getThumb());
					/*if (!TextUtils.isEmpty(dataBean.getThumb())) {
						Picasso.with(mActivity)
								.load(dataBean.getThumb())
								.into(ivContent);
					}*/
						tvTitle.setText(dataBean.getTitle());
					}

					cancelLoadDialog();

				} else if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("error")) {

					tvTitle.setText("文章不存在");
					cancelLoadDialog();

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
			}
		});

	}

	//1登录2阅读文章3观看视频4文章学习市场5视频学习市场
	private void uploadTask() {

		String url = ApiRequestTag.API_HOST + "/api/v1/report/task";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("task_id", "2");

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf upload task successResult:" + successResult);

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf upload task code:" + code);

			}
		});


	}


}

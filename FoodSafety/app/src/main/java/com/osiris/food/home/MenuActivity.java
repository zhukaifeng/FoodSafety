package com.osiris.food.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.ExitEvent;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.event.UploadVideoInfo;
import com.osiris.food.home.fragment.ApplyFragment;
import com.osiris.food.home.fragment.CityNewsFragment;
import com.osiris.food.home.fragment.ExamFragment;
import com.osiris.food.home.fragment.HomeFragment;
import com.osiris.food.home.fragment.IndustryInformationFragment;
import com.osiris.food.home.fragment.MineFragment;
import com.osiris.food.home.fragment.PoliccyRegualationFragment;
import com.osiris.food.home.fragment.StudyFragment;
import com.osiris.food.model.LearnsPulicBean;
import com.osiris.food.model.StudyCourse;
import com.osiris.food.model.UserInfo;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.IBackInterface;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MenuActivity extends BaseActivity implements IBackInterface {

	@BindView(R.id.img_apply)
	ImageView img_apply;
	@BindView(R.id.img_study)
	ImageView img_study;
	@BindView(R.id.img_exam)
	ImageView img_exam;
	@BindView(R.id.img_mine)
	ImageView img_mine;
	@BindView(R.id.img_home)
	ImageView img_home;

	private static final String CURRENT_TAB = "currentTab";
	public static final String FRAGMENT_HOME = "homePageFragment";
	public static final String FRAGMENT_APPLY = "homeApplyFragment";
	public static final String FRAGMENT_STUDY = "homeStudyFragment";
	public static final String FRAGMENT_EXAM = "homeExamFragment";
	public static final String FRAGMENT_MINE = "homeMineFragment";
	public static final String FRAGMENT_POLICY = "policyFragment";
	public static final String FRAGMENT_NEWS = "newsFragment";
	public static final String FRAGMENT_INFORMATION = "informationFragment";


	private String currentFragment = FRAGMENT_HOME;
	private HashMap<String, BaseFragment> homePageFragmentMap = new HashMap<>();
	private AlertDialog.Builder builder;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_menu;
	}

	@Override
	public void init() {
		preferences = getPreferences(MODE_PRIVATE);
		editor = preferences.edit();
		showFragment(currentFragment);
		getUserInfo();
		getTotalScore();
		int course_id = preferences.getInt(COURSE_ID, 0);
		if (course_id == 0) {
			getClassList(false);
		} else {
			if (mInitInfoTimer == null) {
				mInitInfoTimer = new Timer(true);
				mInitInfoTimer.schedule(mInitInfoTask, 0, 6 * 1000); //延时0S，6s执行一次
			}
		}

	}


	@OnClick({R.id.ll_apply, R.id.ll_study, R.id.ll_exam, R.id.ll_mine, R.id.img_home})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_apply:
				showFragment(FRAGMENT_APPLY);
				break;
			case R.id.ll_study:
				showFragment(FRAGMENT_STUDY);
				break;
			case R.id.ll_exam:
				showFragment(FRAGMENT_EXAM);
				break;
			case R.id.ll_mine:
				showFragment(FRAGMENT_MINE);
				break;
			case R.id.img_home:
				showFragment(FRAGMENT_HOME);
				break;
		}

	}


	private void refreshTabRes(String tabName) {
		img_home.setImageResource(tabName.equals(FRAGMENT_HOME) ? R.drawable
				.home_home_pre : R
				.drawable.home_home_nor);
		img_apply.setImageResource(tabName.equals(FRAGMENT_APPLY) ? R.drawable
				.home_apply_pressed : R.drawable.home_apply_normal);
		img_study.setImageResource(tabName.equals(FRAGMENT_STUDY) ? R.drawable
				.home_study_pressed : R.drawable.home_study_normal);
		img_exam.setImageResource(tabName.equals(FRAGMENT_EXAM) ? R.drawable
				.home_exam_pressed : R.drawable.home_exam_normal);
		img_mine.setImageResource(tabName.equals(FRAGMENT_MINE) ? R.drawable
				.home_mine_pressed : R.drawable.home_mine_normal);
	}


	private void showFragment(String tabName) {
		currentFragment = tabName;
		switch (tabName) {
			case FRAGMENT_HOME:
				switchFragment(FRAGMENT_HOME);
				refreshTabRes(FRAGMENT_HOME);
				break;
			case FRAGMENT_APPLY:
				switchFragment(FRAGMENT_APPLY);
				refreshTabRes(FRAGMENT_APPLY);
				break;
			case FRAGMENT_STUDY:
				switchFragment(FRAGMENT_STUDY);
				refreshTabRes(FRAGMENT_STUDY);
				break;
			case FRAGMENT_EXAM:
				switchFragment(FRAGMENT_EXAM);
				refreshTabRes(FRAGMENT_EXAM);
				break;
			case FRAGMENT_MINE:
				switchFragment(FRAGMENT_MINE);
				refreshTabRes(FRAGMENT_MINE);
				break;
		}
	}


	public void switchFragment(String fragmentName) {
		BaseFragment fragment = getFragment(fragmentName);
		if (fragment == null) {
			return;
		}
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
				.commitAllowingStateLoss();
	}


	/**
	 * 缓存fragment
	 *
	 * @param fragmentName
	 * @return
	 */
	private BaseFragment getFragment(String fragmentName) {
		BaseFragment fragment = homePageFragmentMap.get(fragmentName);
		if (fragment == null) {
			switch (fragmentName) {
				case FRAGMENT_HOME:
					fragment = new HomeFragment();
					break;
				case FRAGMENT_APPLY:
					fragment = new ApplyFragment();
					break;
				case FRAGMENT_STUDY:
					fragment = new StudyFragment();

					break;
				case FRAGMENT_EXAM:
					fragment = new ExamFragment();
					break;
				case FRAGMENT_MINE:
					fragment = new MineFragment();
					break;
				case FRAGMENT_POLICY:
					fragment = new PoliccyRegualationFragment();
					break;
				case FRAGMENT_NEWS:
					fragment = new CityNewsFragment();
					break;
				case FRAGMENT_INFORMATION:
					fragment = new IndustryInformationFragment();
					break;
			}
			homePageFragmentMap.put(fragmentName, fragment);
		}
		return fragment;
	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onGetMessage(FragmentChangeEvent fragmentChangeEvent) {
		if (TextUtils.isEmpty(fragmentChangeEvent.getFrgment())) {
			return;
		}
		switchFragment(fragmentChangeEvent.getFrgment());
		refreshTabRes(FRAGMENT_HOME);
		/*switch (fragmentChangeEvent.getFrgment()){
			case FRAGMENT_HOME:

				break;
			case FRAGMENT_POLICY:
				switchFragment(fragmentChangeEvent.getFrgment());
				refreshTabRes(FRAGMENT_HOME);
				break;
			case FRAGMENT_NEWS:
				switchFragment(fragmentChangeEvent.getFrgment());
				refreshTabRes(FRAGMENT_HOME);
				break;

		}*/

	}


	private void getUserInfo() {

		String url = ApiRequestTag.API_HOST + "/api/v1/users/profile";


		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					UserInfo.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(), UserInfo.DataBean.class);
					GlobalParams.user_name = dataBean.getName();
					GlobalParams.phone = dataBean.getPhone();
					GlobalParams.avatar = dataBean.getAvatar();
					GlobalParams.gender = dataBean.getGender();
					GlobalParams.birthday = dataBean.getBirthday();
					GlobalParams.address = dataBean.getAddress();
					GlobalParams.company = dataBean.getCompany();
					GlobalParams.type = dataBean.getType();
					GlobalParams.created_at = dataBean.getCreated_at();
					GlobalParams.video_time = dataBean.getVideo_time();
					GlobalParams.identity_card_type = dataBean.getIdentity_card_type();
					LogUtils.d("zkf GlobalParams.user_name:" + GlobalParams.user_name);
					LogUtils.d("zkf GlobalParams.avatar:" + GlobalParams.avatar);
					LogUtils.d("zkf GlobalParams.type:" + GlobalParams.type);


				}

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});


	}

	private Fragment fragment;

	@Override
	public void setSelectedFragment(Fragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public void onBackPressed() {
		if (fragment != null && ((CityNewsFragment) fragment).onBackPressed()) {
			//实现具体的点击效果
			postEvent(new FragmentChangeEvent(FRAGMENT_HOME));
		} else if (fragment != null && ((PoliccyRegualationFragment) fragment).onBackPressed()) {
			postEvent(new FragmentChangeEvent(FRAGMENT_HOME));
		} else if (fragment != null && ((IndustryInformationFragment) fragment).onBackPressed()) {
			postEvent(new FragmentChangeEvent(FRAGMENT_HOME));
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onExitMessage(ExitEvent exitEvent) {

		finish();
	}


	private void uploadLookTime(int id, int start_time, int end_time) {

		LogUtils.d("zkf uploadLookTime");
		String url = ApiRequestTag.API_HOST + "/api/v1/report/video";


		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("video_id", String.valueOf(id));
		paramMap.put("start_time", String.valueOf(start_time));
		paramMap.put("end_time", String.valueOf(end_time));
		LogUtils.d("zkf start uploadLookTime");
		LogUtils.d("zkf video_id:" + id);

		RequestBody formBody = new FormBody.Builder()
				.add("video_id", String.valueOf(id))
				.add("start_time", String.valueOf(start_time))
				.add("end_time", String.valueOf(end_time))
				.build();

		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
				.url(url)
				.post(formBody)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "Bearer " + GlobalParams.access_token)
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {


			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.d("zkf", "onResponse: " + response.body().string());
			}
		});


		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA + 1, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("finish");
				LogUtils.d("zkf successresult111:" + successResult);
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("failed");
			}
		});


	}

	private UploadVideoInfo mUploadVideoInfo;

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onReceiveMessage(UploadVideoInfo uploadVideoInfo) {
		LogUtils.d("zkf reeeeeeeeeeee");
		mUploadVideoInfo = uploadVideoInfo;
		mHandler.sendEmptyMessage(0);
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:
					uploadLookTime(mUploadVideoInfo.getId(), mUploadVideoInfo.getStartTime(), mUploadVideoInfo.getEndTime());
					break;
				case 1:
					builder = new AlertDialog.Builder(MenuActivity.this).setIcon(R.drawable.ic_logo).setTitle("课程通知")
							.setMessage("您有新的课程需要学习，是否跳转去课程？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									//ToDo: 你想做的事情
									dialogInterface.dismiss();
									LogUtils.d("zkf jump");
									switchFragment(FRAGMENT_STUDY);
									refreshTabRes(FRAGMENT_STUDY);
									getClassList(true);

								}
							}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									//ToDo: 你想做的事情
									dialogInterface.dismiss();
								}
							});
					builder.create().show();
					break;
				case 2:
					LogUtils.d("zkf 6s one time start");
					int course_id = preferences.getInt(COURSE_ID, 0);
					if (course_id == 0) {
						getClassList(false);
					} else {
						applyNewCourse(course_id);
					}
					break;
			}
		}
	};

	private void getTotalScore() {

		String url = ApiRequestTag.API_HOST + "/api/v1/users/points";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					if (null != json.get("data").getAsJsonObject().get("user_point").getAsString()) {
						String user_point = json.get("data").getAsJsonObject().get("user_point").getAsString();
						String surplus_point = json.get("data").getAsJsonObject().get("surplus_point").getAsString();
						String total_point = json.get("data").getAsJsonObject().get("total_point").getAsString();

						GlobalParams.user_point = Integer.parseInt(user_point);
					}

				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code111:" + code);
			}
		});

	}


	private List<StudyCourse> dataList = new ArrayList<>();

	private void getClassList(boolean saveId) {
		dataList.clear();
		String url = ApiRequestTag.API_HOST + "/api/v1/lessons/learning";
		LogUtils.d("zkf  url:" + url);
		Map<String, String> paramMap = new HashMap<>();
		//	paramMap.put("type", "1");
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				Log.e("xzw", successResult);
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject().get("data").getAsJsonObject();
				if ((successResult.contains("courses"))) {
					LearnsPulicBean.DataBean.CoursesBean[] learnsMajorBean = JsonUtils.fromJson(json.get("courses").getAsJsonArray()
							, LearnsPulicBean.DataBean.CoursesBean[].class);
					List<LearnsPulicBean.DataBean.CoursesBean> coursesBeansList = new ArrayList<>();
					coursesBeansList.addAll(Arrays.asList(learnsMajorBean));
					//	int learnId = learnsMajorBean.getData().getLesson_id();
					//	List<LearnsMajorBean.DataBean.ListBean> listBeans = learnsMajorBean.getData().getList();
					for (int i = 0; i < coursesBeansList.size(); i++) {
						LearnsPulicBean.DataBean.CoursesBean bean = coursesBeansList.get(i);
						StudyCourse studyCourse = new StudyCourse();
						studyCourse.setId(bean.getId());
						studyCourse.setCourseName(bean.getTitle());
						studyCourse.setStartTime(bean.getStart_time());
						studyCourse.setVisited(bean.getVisited());
						studyCourse.setThumb(bean.getThumb());
						dataList.add(studyCourse);

					}
				}
				if ((successResult.contains("content"))) {
					LearnsPulicBean.DataBean.CoursesBean[] learnsMajorBean = JsonUtils.fromJson(json.get("content").getAsJsonArray()
							, LearnsPulicBean.DataBean.CoursesBean[].class);
					List<LearnsPulicBean.DataBean.CoursesBean> coursesBeansList = new ArrayList<>();
					coursesBeansList.addAll(Arrays.asList(learnsMajorBean));
					//	int learnId = learnsMajorBean.getData().getLesson_id();
					//	List<LearnsMajorBean.DataBean.ListBean> listBeans = learnsMajorBean.getData().getList();
					for (int i = 0; i < coursesBeansList.size(); i++) {
						LearnsPulicBean.DataBean.CoursesBean bean = coursesBeansList.get(i);
						StudyCourse studyCourse = new StudyCourse();
						studyCourse.setId(bean.getId());
						studyCourse.setCourseName(bean.getTitle());
						studyCourse.setStartTime(bean.getStart_time());
						studyCourse.setVisited(bean.getVisited());
						studyCourse.setThumb(bean.getThumb());
						dataList.add(studyCourse);
					}
				}
				Collections.sort(dataList, new IdComparator()); // 根据id排序
				if (saveId){
					editor.putInt(COURSE_ID,  dataList.get(dataList.size() - 1).getId());
					editor.commit();
				}else {
					applyNewCourse(dataList.get(dataList.size() - 1).getId());
				}

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				Log.e("xzw", msg);
			}
		});
	}


	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private String COURSE_ID = "acorse_id";
	private Timer mInitInfoTimer;

	private void applyNewCourse(int id) {

		String url = ApiRequestTag.API_HOST + "/api/v1/lessons/notice";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("last_id", String.valueOf(id));

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				if (successResult.contains("success")) {
					mHandler.sendEmptyMessage(1);
					editor.putInt(COURSE_ID, id);
					editor.commit();

				}
				if (mInitInfoTimer == null) {
					mInitInfoTimer = new Timer(true);
					mInitInfoTimer.schedule(mInitInfoTask, 0, 6 * 1000); //延时0S，6s执行一次
				}

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});


	}


	private TimerTask mInitInfoTask = new TimerTask() {
		@Override
		public void run() {
			mHandler.sendEmptyMessage(2);
		}
	};

}

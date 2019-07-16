package com.osiris.food.home.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.IdComparator;
import com.osiris.food.model.LearnsPulicBean;
import com.osiris.food.model.StudyCourse;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.autosize.utils.LogUtils;

import static android.content.Context.MODE_PRIVATE;
import static com.osiris.food.home.MenuActivity.FRAGMENT_STUDY;

public class ApplyFragment extends BaseFragment {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_identity)
	TextView tvIdentity;
	@BindView(R.id.tv_name)
	TextView tvName;
	@BindView(R.id.tv_sex)
	TextView tvSex;
	@BindView(R.id.tv_company_name)
	TextView tvCompanyName;
	@BindView(R.id.tv_study_time)
	TextView tvStudyTime;
	@BindView(R.id.tv_video_time)
	TextView tv_video_time;
	@BindView(R.id.tv_notice)
	TextView tvNotice;
	Unbinder unbinder;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private String COURSE_ID = "acorse_id";

	@Override
	protected int setLayout() {
		return R.layout.fragmnent_info;
	}

	@Override
	protected void initView() {
		tv_title.setText("个人信息");
		rl_back.setVisibility(View.GONE);
		tvName.setText(GlobalParams.user_name);
		tvSex.setText(GlobalParams.gender);
		if (!TextUtils.isEmpty(GlobalParams.company)) {
			tvCompanyName.setText(GlobalParams.company);
		}
		switch (GlobalParams.type) {
			case 1:
				tvIdentity.setText("餐饮服务从业人员");
				break;
			case 2:
				tvIdentity.setText("食品生产从业人员");
				break;
			case 3:
				tvIdentity.setText("食品流通从业人员");
				break;
			case 4:
				tvIdentity.setText("食品安全协管人员");
				break;
			case 5:
				tvIdentity.setText("食品安全管理员培训");
				break;
		}
		tvStudyTime.setText(GlobalParams.created_at);
		tv_video_time.setText(GlobalParams.video_time);
//		LogUtils.d("zkf save id :" + preferences.getInt("COURSE_ID",0));
//		if (preferences.getInt("COURSE_ID",0) == 0){
//			getClassList(true,0);
//		}else {
//			getClassList(false,preferences.getInt("COURSE_ID",0));
//		}

	}

	@Override
	protected void initData() {

	}

//	@OnClick({R.id.tv_continue_edu})
//	void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.tv_continue_edu:
//				Intent intent = new Intent(getActivity(), ApplyEduActivity.class);
//				startActivity(intent);
//				break;
//		}
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}


	private List<StudyCourse> dataList = new ArrayList<>();

	private void getClassList(boolean saveId, int oldId) {
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
				if (saveId) {
					preferences = getActivity().getPreferences(MODE_PRIVATE);
					editor = preferences.edit();
					editor.putInt(COURSE_ID, dataList.get(dataList.size() - 1).getId());
					editor.commit();
					LogUtils.d("zkf save id  iiiiiii:" + dataList.get(dataList.size() - 1).getId());
					applyNewCourse(dataList.get(dataList.size() - 2).getId());
				} else {
					applyNewCourse(oldId);
				}
//				if (mInitInfoTimer == null) {
//					mInitInfoTimer = new Timer(true);
//					mInitInfoTimer.schedule(mInitInfoTask, 0, 6 * 1000); //延时0S，6s执行一次
//				}

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				Log.e("xzw", msg);
			}
		});
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 2:
					LogUtils.d("zkf 6s one time start");
					int course_id = preferences.getInt(COURSE_ID, 0);
					if (course_id == 0) {
						getClassList(false, course_id);
					} else {
						applyNewCourse(course_id);
					}
					break;
			}
		}
	};


	private Timer mInitInfoTimer;

	private void applyNewCourse(int id) {
		LogUtils.d("zkf send apply new course id:" + id);

		String url = ApiRequestTag.API_HOST + "/api/v1/lessons/notice";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("last_id", String.valueOf(id));

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				if (successResult.contains("success")) {
					preferences = getActivity().getPreferences(MODE_PRIVATE);
//					mHandler.sendEmptyMessage(1);
					editor = preferences.edit();
					editor.putInt(COURSE_ID, id);
					editor.commit();
					if (null != tvNotice){
						tvNotice.setText("您有新的课程");
					}
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


	@Override
	public void onResume() {
		super.onResume();
		int course_id = preferences.getInt(COURSE_ID, 0);
		LogUtils.d("zkf iiii get course_id:" + course_id);
		if (course_id == 0) {
			getClassList(true, course_id);
		} else {
			applyNewCourse(course_id);
		}
	}

	@OnClick(R.id.rl_work_status)
	public void onViewClicked(View v) {
		switch (v.getId()){
			case R.id.rl_work_status:
				int course_id = preferences.getInt(COURSE_ID, 0);
				getClassList(true,course_id);

				postEvent(new FragmentChangeEvent(FRAGMENT_STUDY));
				break;

		}
	}
}

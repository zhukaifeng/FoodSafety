package com.osiris.food.home;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.ExitEvent;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.fragment.ApplyFragment;
import com.osiris.food.home.fragment.CityNewsFragment;
import com.osiris.food.home.fragment.ExamFragment;
import com.osiris.food.home.fragment.HomeFragment;
import com.osiris.food.home.fragment.IndustryInformationFragment;
import com.osiris.food.home.fragment.MineFragment;
import com.osiris.food.home.fragment.PoliccyRegualationFragment;
import com.osiris.food.home.fragment.StudyFragment;
import com.osiris.food.model.UserInfo;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.IBackInterface;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

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


	@Override
	public int getLayoutResId() {
		return R.layout.activity_menu;
	}

	@Override
	public void init() {
		showFragment(currentFragment);
		getUserInfo();

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
		LogUtils.d("zkf change");
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

		if (TextUtils.isEmpty(fragmentChangeEvent.getFrgment())){
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


	private void getUserInfo(){

		String url = ApiRequestTag.API_HOST+"/api/v1/users/profile";


		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					UserInfo.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(),UserInfo.DataBean.class);
					GlobalParams.user_name = dataBean.getName();
					GlobalParams.phone = dataBean.getPhone();
					GlobalParams.avatar = dataBean.getAvatar();
					GlobalParams.gender = dataBean.getGender();
					GlobalParams.birthday = dataBean.getBirthday();
					GlobalParams.address = dataBean.getAddress();
					GlobalParams.company = dataBean.getCompany();
					GlobalParams.type = dataBean.getType();
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
		} else if (fragment != null && ((PoliccyRegualationFragment) fragment).onBackPressed()){

		}else if (fragment != null && ((IndustryInformationFragment) fragment).onBackPressed()){

		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onExitMessage(ExitEvent exitEvent) {

		finish();
	}

}

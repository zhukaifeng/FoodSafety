package com.osiris.food.home;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.fragment.ApplyFragment;
import com.osiris.food.home.fragment.CityNewsFragment;
import com.osiris.food.home.fragment.ExamFragment;
import com.osiris.food.home.fragment.HomeFragment;
import com.osiris.food.home.fragment.IndustryInformationFragment;
import com.osiris.food.home.fragment.MineFragment;
import com.osiris.food.home.fragment.PoliccyRegualationFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class MenuActivity extends BaseActivity {

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
					fragment = new ApplyFragment();

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
			LogUtils.d("zkf put fragmentName:" + fragmentName);
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

}

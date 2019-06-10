package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.train.ApplyTrainingActivity;
import com.osiris.food.train.FaceTraingActivity;
import com.osiris.food.train.TrainRecordActivity;
import com.osiris.food.train.TrainingContentActivity;
import com.osiris.food.view.MyViewPager;
import com.osiris.food.view.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.OnClick;

public class StudyFragment extends BaseFragment {

	@BindView(R.id.tab_strip)
	PagerSlidingTabStrip tab_strip;
	@BindView(R.id.viewPager)
	MyViewPager mViewPager;
	@BindView(R.id.tv_date)
	TextView tv_date;
	@BindView(R.id.rl_apply_record)
	RelativeLayout rl_apply_record;
	@BindView(R.id.rl_training_record)
	RelativeLayout rl_training_record;
	@BindView(R.id.tv_study_score)
	TextView tv_study_score;
	@BindView(R.id.tv_total_score)
	TextView tv_total_score;
	@BindView(R.id.tv_complete_time)
	TextView tv_complete_time;
	@BindView(R.id.tv_train_time)
	TextView tv_train_time;

	private  String[] title;


	@Override
	protected int setLayout() {
		return R.layout.fragmnent_study;
	}

	@Override
	protected void initView() {

		title = new String[]{getResources().getString(R.string.public_class), getResources().getString(R.string
				.major_class)};
		mViewPager.setAdapter(new myPagerAdapter(getActivity().getSupportFragmentManager()));
		tab_strip.setViewPager(mViewPager);
		tab_strip.setTextSize((int) getResources().getDimension(R.dimen.sp16));




	}

	@Override
	protected void initData() {

	}



	@OnClick({R.id.rl_apply_record, R.id.rl_training_record, R.id.tv_face_check, R.id.tv_confirm_note})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_apply_record:
				Intent intent = new Intent(getActivity(), TrainingContentActivity.class);
				startActivity(intent);

				break;
			case R.id.rl_training_record:
				Intent intent2 = new Intent(getActivity(), TrainRecordActivity.class);
				startActivity(intent2);
				break;
			case R.id.tv_face_check:
				Intent intent3 = new Intent(getActivity(), ApplyTrainingActivity.class);
				startActivity(intent3);
				break;
			case R.id.tv_confirm_note:
				Intent intent4 = new Intent(getActivity(), FaceTraingActivity.class);
				startActivity(intent4);
				break;
		}
	}


	private class myPagerAdapter extends FragmentPagerAdapter {


		PublicCourseFragment fragment1;
		MajorCourseFragment fragment2;

		public myPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					fragment1 = new PublicCourseFragment();
					return fragment1;

				case 1:
					fragment2 = new MajorCourseFragment();
					return fragment2;

				default:
					return null;
			}
		}

		@Override
		public int getCount() {

			return title.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return title[position];
		}

	}




}

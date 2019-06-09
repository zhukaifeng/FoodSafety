package com.osiris.food.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.edu.ApplyEduActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyFragment extends BaseFragment {



	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;


	@Override
	protected int setLayout() {
		return R.layout.fragmnent_apply;
	}

	@Override
	protected void initView() {
		tv_title.setText(getString(R.string.apply_continue_eduction));
		rl_back.setVisibility(View.GONE);



	}

	@Override
	protected void initData() {

	}

	@OnClick({R.id.tv_continue_edu})
	void onClick(View v){
		switch (v.getId()){
			case R.id.tv_continue_edu:
				Intent intent = new Intent(getActivity(), ApplyEduActivity.class);
				startActivity(intent);
				break;
		}
	}
}

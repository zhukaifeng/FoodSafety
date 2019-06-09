package com.osiris.food.mine;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.view.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonInfoActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.iv_avatar)
	CircleImageView iv_avatar;
	@BindView(R.id.tv_nick_name)
	TextView tv_nick_name;
	@BindView(R.id.tv_sex)
	TextView tv_sex;
	@BindView(R.id.tv_birthday)
	TextView tv_birthday;
	@BindView(R.id.tv_study_num)
	TextView tv_study_num;
	@BindView(R.id.tv_phone_num)
	TextView tv_phone_num;
	@BindView(R.id.tv_location)
	TextView tv_location;
	@BindView(R.id.tv_work_status)
	TextView tv_work_status;
	@BindView(R.id.tv_pseronal_check)
	TextView tv_pseronal_check;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_personal_info;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.personal_info));

	}


	@OnClick({R.id.rl_back,R.id.iv_avatar,R.id.rl_nick_name,
			R.id.rl_sex,R.id.rl_birthday,R.id.rl_study_num,R.id.rl_phone,
			R.id.rl_location,R.id.rl_work_status,R.id.rl_personal_check,R.id.rl_qr_card})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.iv_avatar:
				break;
			case R.id.rl_nick_name:
				break;
			case R.id.rl_sex:
				break;
			case R.id.rl_birthday:
				break;
			case R.id.rl_study_num:
				break;
			case R.id.rl_phone:
				break;
			case R.id.rl_location:
				break;
			case R.id.rl_work_status:
				break;
			case R.id.rl_personal_check:
				break;
			case R.id.rl_qr_card:
				break;
		}
	}
}

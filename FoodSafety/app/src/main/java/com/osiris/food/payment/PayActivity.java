package com.osiris.food.payment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_pay_count)
	TextView tv_pay_count;
	@BindView(R.id.tv_pay_order_id)
	TextView tv_pay_order_id;
	@BindView(R.id.tv_course_name)
	TextView tv_course_name;
	@BindView(R.id.tv_pay)
	TextView tv_pay;
	@BindView(R.id.checkbox_ali)
	CheckBox checkbox_ali;
	@BindView(R.id.checkbox_wechat)
	CheckBox checkbox_wechat;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_payment;
	}

	@Override
	public void init() {


		tv_title.setText(getString(R.string.txt_payment_confirm));
		checkbox_ali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if (isChecked){
					checkbox_wechat.setChecked(false);
				}


			}
		});
		checkbox_wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if (isChecked){
					checkbox_ali.setChecked(false);
				}


			}
		});

	}


	@OnClick({R.id.rl_back,R.id.tv_confirm_pay})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
			case R.id.tv_confirm_pay:
				finish();
				break;
		}
	}
}

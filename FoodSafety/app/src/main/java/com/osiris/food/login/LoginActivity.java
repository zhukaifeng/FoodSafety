package com.osiris.food.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


	@BindView(R.id.checkbox_policy)
	CheckBox checkbox_policy;
	@BindView(R.id.edt_phone)
	EditText edt_phone;
	@BindView(R.id.edt_pwd)
	EditText edt_pwd;
	@BindView(R.id.edt_code)
	EditText edt_code;
	@BindView(R.id.tv_send)
	TextView tv_send;

	private CountDownTimer countDownTimer;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_login;
	}

	@Override
	public void init() {

	}


	@OnClick({R.id.tv_send, R.id.btn_login, R.id.tv_forget_pwd, R.id.tv_regist})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_send:
				startTime();
				break;
			case R.id.btn_login:


				break;
			case R.id.tv_forget_pwd:


				break;
			case R.id.tv_regist:


				break;
		}
	}

	private void startTime() {
		countDownTimer = new CountDownTimer(60000, 100) {
			@Override
			public void onTick(long millisUntilFinished) {
				tv_send.setEnabled(false);
				tv_send.setText(String.format(getResources().getString(R.string.send), (millisUntilFinished / 1000)));

			}

			@Override
			public void onFinish() {
				tv_send.setEnabled(true);
				tv_send.setText(getResources().getString(R.string.get_msg_code));

			}
		}.start();
	}


}

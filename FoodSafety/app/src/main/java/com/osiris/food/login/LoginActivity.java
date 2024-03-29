package com.osiris.food.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.home.MenuActivity;
import com.osiris.food.home.ResetPasswordActivity;
import com.osiris.food.model.Login;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.dialog.ContinueEduDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

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
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private String USER_ACCOUNT="account";
	private String USER_PWD="password";



	@Override
	public int getLayoutResId() {
		return R.layout.activity_login;
	}

	@Override
	public void init() {
		preferences=getPreferences(MODE_PRIVATE);
		editor=preferences.edit();
		String account=preferences.getString(USER_ACCOUNT,"");
		String pwd=preferences.getString(USER_PWD,"");
		LogUtils.d("zkf account:" + account + "   pwd:" + pwd);
		if (!TextUtils.isEmpty(account)){
			edt_phone.setText(account);
		}
		if (!TextUtils.isEmpty(pwd)){
			edt_pwd.setText(pwd);
		}
	}


	@OnClick({R.id.tv_send, R.id.btn_login, R.id.tv_forget_pwd, R.id.tv_regist, R.id.tv_policy})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_send:
				startTime();
				break;
			case R.id.btn_login:

				login();
				//Intent intent2 = new Intent(LoginActivity.this, MenuActivity.class);
				//startActivity(intent2);

				break;
			case R.id.tv_forget_pwd:
				Intent intent2 = new Intent(this, ResetPasswordActivity.class);
				startActivity(intent2);
				//showDialog();
				break;
			case R.id.tv_regist:
				Intent intent = new Intent(this, IdentitySelectionActivity.class);
				startActivity(intent);

				break;
			case R.id.tv_policy:
				Intent intent1 = new Intent(this, PolicyActivity.class);
				startActivity(intent1);
				break;
		}
	}

	private void login() {

		String url = ApiRequestTag.API_HOST + "/api/v1/login";


		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put("phone","18370894190");
//		paramMap.put("password","123456");


		if (!TextUtils.isEmpty(edt_phone.getText())) {
			paramMap.put("phone", edt_phone.getText().toString());
			//paramMap.put("phone","18370894190");
		} else {
			Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!TextUtils.isEmpty(edt_pwd.getText())) {
			paramMap.put("password", edt_pwd.getText().toString());
			//paramMap.put("password","123456");

		} else {
			Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			return;
		}


//		paramMap.put("phone", "18370894190");
//		paramMap.put("password", "123456");
		showLoadDialog();

		NetRequest.request(url, ApiRequestTag.LOGIN, paramMap, new NetRequestResultListener() {
					@Override
					public void requestSuccess(int tag, String successResult) {
						JsonParser parser = new JsonParser();
						JsonObject json = parser.parse(successResult).getAsJsonObject();
						Login data = JsonUtils.fromJson(json, Login.class);

						if (null != data) {
							GlobalParams.token_type = data.getData().getToken_type();
							GlobalParams.access_token = data.getData().getAccess_token();
							GlobalParams.expires_in = data.getData().getExpires_in();
							GlobalParams.refresh_token = data.getData().getRefresh_token();
							GlobalParams.user_id = data.getData().getId();
							GlobalParams.user_name = data.getData().getName();
							cancelLoadDialog();
					//		uploadTask();

							Intent intent2 = new Intent(LoginActivity.this, MenuActivity.class);

							editor.putString(USER_ACCOUNT,edt_phone.getText().toString());
							editor.putString(USER_PWD,edt_pwd.getText().toString());
							editor.commit();


							startActivity(intent2);
						}

					}

					@Override
					public void requestFailure(int tag, int code, String msg) {
						LogUtils.d("zkf return data:" + msg);
						cancelLoadDialog();
					}
				}
		);


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


	private void showDialog() {
		ContinueEduDialog.Builder preventBuilder = new ContinueEduDialog.Builder(this);
		preventBuilder.setPositiveButton(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});

		preventBuilder.create().show();
	}


	//1登录2阅读文章3观看视频4文章学习市场5视频学习市场
	private void uploadTask() {

		String url = ApiRequestTag.API_HOST + "/api/v1/report/task";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("task_id", "1");

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

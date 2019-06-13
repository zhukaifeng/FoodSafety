package com.osiris.food.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
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


	@Override
	public int getLayoutResId() {
		return R.layout.activity_login;
	}

	@Override
	public void init() {

	}


	@OnClick({R.id.tv_send, R.id.btn_login, R.id.tv_forget_pwd, R.id.tv_regist,R.id.tv_policy})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_send:
				startTime();
				break;
			case R.id.btn_login:


				login();


//				Intent intent2 = new Intent(this, MenuActivity.class);
//				startActivity(intent2);
				break;
			case R.id.tv_forget_pwd:

				//showDialog();
				break;
			case R.id.tv_regist:
				Intent intent = new Intent(this,RegistActivity.class);
				startActivity(intent);

				break;
			case R.id.tv_policy:
				Intent intent1 = new Intent(this,PolicyActivity.class);
				startActivity(intent1);
				break;
		}
	}

	private void login() {

		String url = ApiRequestTag.API_HOST+ "/api/v1/login";

		/*JsonObject json = new JsonObject();
		json.addProperty("phone","18370894190");
		json.addProperty("password","123456");

		HttpManager.postNotoken(url, json, this, new HttpManager.OnResponse<Object>() {
			@Override
			public Object analyseResult(String body) {
				LogUtils.d("zkf return data:" + body);

				return null;
			}

			@Override
			public void onSuccess(Object result) {

			}
		});*/


		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("phone","18370894190");
		paramMap.put("password","123456");
		NetRequest.request(url,ApiRequestTag.LOGIN,paramMap, new NetRequestResultListener() {
					@Override
					public void requestSuccess(int tag, String successResult) {
						LogUtils.d("zkf return data:" + successResult);
					}

					@Override
					public void requestFailure(int tag, int code, String msg) {
						LogUtils.d("zkf return data:" + msg);
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


}

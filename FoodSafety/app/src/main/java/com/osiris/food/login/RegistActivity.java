package com.osiris.food.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.CloseEvent;
import com.osiris.food.network.ApiRequestTag;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity {

	@BindView(R.id.edt_name)
	EditText edt_name;
	@BindView(R.id.edt_sex)
	EditText edt_sex;
	@BindView(R.id.edt_phone)
	EditText edt_phone;
	@BindView(R.id.edt_document)
	EditText edt_document;
	@BindView(R.id.edt_document_num)
	EditText edt_document_num;
	@BindView(R.id.edt_mail)
	EditText edt_mail;
	@BindView(R.id.edt_eduction)
	EditText edt_eduction;
	private int positionId = -1;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_regist;
	}

	@Override
	public void init() {
		positionId = getIntent().getIntExtra("identity",-1);

	}

	@OnClick({R.id.btn_next, R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_next:

				if (TextUtils.isEmpty(edt_name.getText().toString()) || TextUtils.isEmpty(edt_sex.getText().toString()) ||
						TextUtils.isEmpty(edt_phone.getText().toString()) || TextUtils.isEmpty(edt_document.getText().toString()) ||
						TextUtils.isEmpty(edt_document_num.getText().toString()) || TextUtils.isEmpty(edt_mail.getText().toString()) ||
						TextUtils.isEmpty(edt_eduction.getText().toString())) {
					Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
					return;
				}


				Intent intent = new Intent(this, RegistCompleteActivity.class);
				intent.putExtra("name", edt_name.getText().toString());
				intent.putExtra("sex", edt_sex.getText().toString());
				intent.putExtra("phone", edt_phone.getText().toString());
				intent.putExtra("document", edt_document.getText().toString());
				intent.putExtra("document_num", edt_document_num.getText().toString());
				intent.putExtra("mail", edt_mail.getText().toString());
				intent.putExtra("eduction", edt_eduction.getText().toString());
				intent.putExtra("identity",positionId);

				startActivity(intent);
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void registApp() {

		String url = ApiRequestTag.API_HOST + "/api/v1/signup";

		Map<String, String> paramMap = new HashMap<>();


		if (TextUtils.isEmpty(edt_name.getText().toString())) {
			Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
			return;
		} else {
			paramMap.put("name", edt_name.getText().toString());
		}
		if (TextUtils.isEmpty(edt_sex.getText().toString())) {
			Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (edt_sex.getText().toString().equals("男") || edt_sex.getText().toString().equals("女")) {
				paramMap.put("gender", edt_sex.getText().toString());
			} else {
				Toast.makeText(this, "请输入男或者女", Toast.LENGTH_SHORT).show();
			}

		}
		if (TextUtils.isEmpty(edt_phone.getText().toString())) {
			Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
			return;
		} else {
			paramMap.put("phone", edt_sex.getText().toString());
		}


		if (TextUtils.isEmpty(edt_document.getText().toString())) {
			Toast.makeText(this, "请输入证件类型", Toast.LENGTH_SHORT).show();
			return;
		} else {
			paramMap.put("gender", edt_document.getText().toString());
		}
		if (TextUtils.isEmpty(edt_document_num.getText().toString())) {
			Toast.makeText(this, "请输入证件号码", Toast.LENGTH_SHORT).show();
			return;
		} else {
			paramMap.put("gender", edt_sex.getText().toString());
		}
		if (TextUtils.isEmpty(edt_mail.getText().toString())) {
			Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
			return;
		} else {
			paramMap.put("gender", edt_sex.getText().toString());
		}
		if (TextUtils.isEmpty(edt_mail.getText().toString())) {
			Toast.makeText(this, "请输入学历", Toast.LENGTH_SHORT).show();
			return;
		} else {
			paramMap.put("gender", edt_sex.getText().toString());
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onGetMessage(CloseEvent closeEvent) {
		finish();

	}


}

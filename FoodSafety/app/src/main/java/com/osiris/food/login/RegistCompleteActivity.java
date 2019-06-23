package com.osiris.food.login;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.view.dialog.RegistCompleteDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistCompleteActivity extends BaseActivity {


	@BindView(R.id.edt_industry)
	EditText edt_industry;
	@BindView(R.id.edt_post)
	EditText edt_post;
	@BindView(R.id.edt_company_name)
	EditText edt_company_name;
	@BindView(R.id.edt_company_phone)
	EditText edt_company_phone;
	@BindView(R.id.edt_company_location)
	EditText edt_company_location;
	@BindView(R.id.edt_company_detail_location)
	EditText edt_company_detail_location;
	private String name;
	private String sex;
	private String phone;
	private String document;
	private String document_num;
	private String mail;
	private String eduction;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_complete_regist;
	}

	@Override
	public void init() {
		name = getIntent().getStringExtra("name");
		sex = getIntent().getStringExtra("sex");
		phone = getIntent().getStringExtra("phone");
		document = getIntent().getStringExtra("document");
		document_num = getIntent().getStringExtra("document_num");
		mail = getIntent().getStringExtra("mail");
		eduction = getIntent().getStringExtra("eduction");

	}

	@OnClick({R.id.btn_regist, R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_regist:
				showRegistComplete();
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}

	private void showRegistComplete() {
		RegistCompleteDialog.Builder preventBuilder = new RegistCompleteDialog.Builder(this);
		preventBuilder.setPositiveButton(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});

		preventBuilder.create().show();
	}


	private void registApp() {

		String url = ApiRequestTag.API_HOST + "/api/v1/signup";

		Map<String, String> paramMap = new HashMap<>();

		paramMap.put("name", name);
		paramMap.put("sex", sex);
		paramMap.put("phone", phone);
		if (document.contains("身份证")){
			paramMap.put("id_type", "1");
		}else if (document.contains("军官证")){
			paramMap.put("id_type", "2");
		}else {
			paramMap.put("id_type", "3");
		}

		paramMap.put("id_no", document_num);
		paramMap.put("email", mail);
		paramMap.put("gender", eduction);




	}


}

package com.osiris.food.train;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyTrainingActivity extends BaseActivity {


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
	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_training_apply;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.txt_title_train_apply));

	}



	@OnClick({R.id.tv_cancel,R.id.tv_confirm,R.id.iv_pic,R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.tv_cancel:

				break;
			case R.id.tv_confirm:

				break;
			case R.id.iv_pic:

				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}

}

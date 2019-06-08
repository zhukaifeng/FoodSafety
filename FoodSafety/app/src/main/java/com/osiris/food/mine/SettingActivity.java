package com.osiris.food.mine;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_version)
	TextView tv_version;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_setting;
	}

	@Override
	public void init() {

	}

	@OnClick({R.id.rl_back,R.id.rl_account,R.id.rl_news,R.id.rl_privacy,R.id.rl_custom,R.id.rl_app_version,R.id.btn_exit})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
			case R.id.rl_account:
				break;
			case R.id.rl_news:
				break;
			case R.id.rl_privacy:
				break;
			case R.id.rl_custom:
				break;
			case R.id.rl_app_version:

				break;
			case R.id.btn_exit:

				break;
		}
	}
}

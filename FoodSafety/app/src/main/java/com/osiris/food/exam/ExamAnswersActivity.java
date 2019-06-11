package com.osiris.food.exam;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ExamAnswersActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_exam_answers;
	}

	@Override
	public void init() {

		tv_title.setText(getString(R.string.exam_answer));



	}

	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}





}

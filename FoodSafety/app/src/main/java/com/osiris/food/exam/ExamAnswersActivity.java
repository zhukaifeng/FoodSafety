package com.osiris.food.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.ExamDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ExamAnswersActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_current)
	TextView tvCurrent;
	@BindView(R.id.tv_total)
	TextView tvTotal;
	@BindView(R.id.tv_subject)
	TextView tvSubject;
	@BindView(R.id.checkbox_a)
	CheckBox checkboxA;
	@BindView(R.id.tv_answer_a)
	TextView tvAnswerA;
	@BindView(R.id.linear_a)
	LinearLayout linearA;
	@BindView(R.id.checkbox_b)
	CheckBox checkboxB;
	@BindView(R.id.tv_answer_b)
	TextView tvAnswerB;
	@BindView(R.id.linear_b)
	LinearLayout linearB;
	@BindView(R.id.checkbox_c)
	CheckBox checkboxC;
	@BindView(R.id.tv_answer_c)
	TextView tvAnswerC;
	@BindView(R.id.linear_c)
	LinearLayout linearC;
	@BindView(R.id.checkbox_d)
	CheckBox checkboxD;
	@BindView(R.id.tv_answer_d)
	TextView tvAnswerD;
	@BindView(R.id.linear_d)
	LinearLayout linearD;
	@BindView(R.id.tv_exam_title)
	TextView tv_exam_title;

	private int mExamId = 0;
	private List<ExamDetail.DataBean.QuestionItemsBean> dataList = new ArrayList<>();
	private int position = 0;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_exam_answers;
	}

	@Override
	public void init() {

		tv_title.setText(getString(R.string.exam_answer));
		mExamId = getIntent().getIntExtra("exam_id", 0);
		getData();


	}

	@OnClick({R.id.rl_back,R.id.tv_last_question,R.id.tv_next_question})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.tv_last_question:
				position--;
				if (position<0){
					return;
				}


				tvSubject.setText(dataList.get(position).getAnswer());
				tvAnswerA.setText(dataList.get(position).getSelect().get(0));
				tvAnswerB.setText(dataList.get(position).getSelect().get(1));
				tvAnswerC.setText(dataList.get(position).getSelect().get(2));
				tvAnswerD.setText(dataList.get(position).getSelect().get(3));
				break;
			case R.id.tv_next_question:

				position ++;
				if (position>dataList.size()){
					return;
				}
				tvSubject.setText(dataList.get(position).getAnswer());
				tvAnswerA.setText(dataList.get(position).getSelect().get(0));
				tvAnswerB.setText(dataList.get(position).getSelect().get(1));
				tvAnswerC.setText(dataList.get(position).getSelect().get(2));
				tvAnswerD.setText(dataList.get(position).getSelect().get(3));



				break;
		}
	}


	private void getData() {
		if (mExamId == 0)
			return;

		String url = ApiRequestTag.API_HOST + "/api/v1/papers/" + mExamId;
		NetRequest.requestNoParam(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					ExamDetail.DataBean.QuestionItemsBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonObject().get("question_items").getAsJsonArray(), ExamDetail.DataBean.QuestionItemsBean[].class);
					if (dataList.size() > 0) {
						dataList.clear();
					}

					String title = JsonUtils.fromJson(json.get("data").getAsJsonObject().get("name").getAsString(),String.class);
					tv_exam_title.setText(title);

					dataList.addAll(Arrays.asList(data));

					tvSubject.setText(dataList.get(position).getAnswer());
					tvAnswerA.setText(dataList.get(position).getSelect().get(0));
					tvAnswerB.setText(dataList.get(position).getSelect().get(1));
					tvAnswerC.setText(dataList.get(position).getSelect().get(2));
					tvAnswerD.setText(dataList.get(position).getSelect().get(3));


				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf  code: " + code);
			}
		});


	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}

package com.osiris.food.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.StudyScoreAdapter;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class StudyScoreActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.rl_top)
	RelativeLayout rlTop;
	@BindView(R.id.rv_data)
	RecyclerView rvData;
	@BindView(R.id.tv_notification)
	TextView tvNotification;


	private List<String> dataList = new ArrayList<>();
	private StudyScoreAdapter dataAdapter = new StudyScoreAdapter(dataList);

	@Override
	public int getLayoutResId() {
		return R.layout.activity_study_score;
	}

	@Override
	public void init() {

		tvNotification.setSelected(true);


		dataList.add("1");
		dataList.add("1");
		dataList.add("1");
		dataList.add("1");
		dataList.add("1");

		rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		rvData.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				LogUtils.d("zkf click");
			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}



	@OnClick({R.id.tv_study_score, R.id.tv_score_detail,R.id.rl_back})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_study_score:
				Intent intent = new Intent(this,ScoreDetailActivity.class);
				startActivity(intent);
				break;
			case R.id.tv_score_detail:
				Intent intent1 = new Intent(this,ScoreRuleActivity.class);
				startActivity(intent1);
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}
}

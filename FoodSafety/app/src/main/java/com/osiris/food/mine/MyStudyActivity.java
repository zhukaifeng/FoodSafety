package com.osiris.food.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.StudyAdapter;
import com.osiris.food.model.StudyCourse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyStudyActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<StudyCourse> dataList = new ArrayList<>();
	private StudyAdapter dataAdapter = new StudyAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_study;
	}

	@Override
	public void init() {


		tv_title.setText(getString(R.string.txt_title_mine_study));

//		主讲：食品安全
//		2018-01-01至2019-01-01
//		课程时长：3分21秒
//		已观看时长：0分0秒
		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));
		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));
		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));




		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

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

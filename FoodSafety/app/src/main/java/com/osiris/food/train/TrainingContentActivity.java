package com.osiris.food.train;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.train.adapter.TrainingContentAdapter;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TrainingContentActivity extends BaseActivity {



	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;


	private List<String> dataList = new ArrayList<>();
	private TrainingContentAdapter dataAdapter = new TrainingContentAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_training_content;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.training_content));




		dataList.add("《国务院食品安全办等14部门关于提升......");
		dataList.add("全面落实餐饮服务食品安全主体责任......");
		dataList.add("全面提升餐饮业质量安全水平（初级）");
		dataList.add("全面提升餐饮业质量安全水平（中级）");


		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

			}
		});

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

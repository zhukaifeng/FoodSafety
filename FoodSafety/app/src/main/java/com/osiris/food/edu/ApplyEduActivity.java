package com.osiris.food.edu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.edu.adapter.ApplyEduAdapter;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyEduActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<String> dataList = new ArrayList<>();
	private ApplyEduAdapter dataAdapter = new ApplyEduAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_apply_edu;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.apply_continue_eduction));

		dataList.add("0");
		dataList.add("1");
		dataList.add("2");
		dataList.add("3");

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

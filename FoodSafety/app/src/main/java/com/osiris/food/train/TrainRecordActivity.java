package com.osiris.food.train;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.train.adapter.TrainRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TrainRecordActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;


	private List<String> dataList = new ArrayList<>();
	private TrainRecordAdapter dataAdapter = new TrainRecordAdapter(dataList);

	@Override
	public int getLayoutResId() {
		return R.layout.activity_train_reccord;
	}

	@Override
	public void init() {

		dataList.add("1");
		dataList.add("2");
		dataList.add("3");
		dataList.add("4");


		tv_title.setText(getString(R.string.txt_train_record));


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

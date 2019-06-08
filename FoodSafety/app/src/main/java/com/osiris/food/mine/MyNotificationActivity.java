package com.osiris.food.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.NotificationAdapter;
import com.osiris.food.utils.TextUti;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyNotificationActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<String> dataList = new ArrayList<>();
	private NotificationAdapter dataAdapter = new NotificationAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_notification;
	}

	@Override
	public void init() {


		tv_title.setText(getString(R.string.txt_title_mine_notification));


		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
		dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));




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

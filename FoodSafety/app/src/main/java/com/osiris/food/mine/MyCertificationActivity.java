package com.osiris.food.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.CerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCertificationActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<String> dataList = new ArrayList<>();
	private CerAdapter dataAdapter = new CerAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_certification;
	}

	@Override
	public void init() {


		tv_title.setText(getString(R.string.txt_title_mine_certificate));


		dataList.add("恭喜您通过食品安全考核");
		dataList.add("恭喜您获得XX证书");
		dataList.add("恭喜您通过食品安全考核");
		dataList.add("恭喜您获得XX证书");



		rv_data.setLayoutManager(new GridLayoutManager(this, 2));
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

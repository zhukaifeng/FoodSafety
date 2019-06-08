package com.osiris.food.login;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.login.adapter.IdentitySelectAdapter;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IdentitySelectionActivity extends BaseActivity {


	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	private List<String> dataList = new ArrayList<>();
	private IdentitySelectAdapter dataAdapter = new IdentitySelectAdapter(dataList);



	@Override
	public int getLayoutResId() {
		return R.layout.activity_select_identity;
	}

	@Override
	public void init() {
		dataList.add("餐饮服务从业人员");
		dataList.add("食品生产从业人员");
		dataList.add("食品流通从业人员");
		dataList.add("食品安全协管人员");
		dataList.add("食品安全管理员培训");


		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

			}
		});

	}



	@OnClick({R.id.tv_cancel,R.id.tv_confirm,R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.tv_confirm:

				break;
			case R.id.tv_cancel:

				break;
			case R.id.rl_back:
				finish();
				break;
		}

	}

}

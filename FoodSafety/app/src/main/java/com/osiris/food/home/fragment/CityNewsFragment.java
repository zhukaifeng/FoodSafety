package com.osiris.food.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.adapter.CityNewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.osiris.food.home.MenuActivity.FRAGMENT_HOME;

public class CityNewsFragment extends BaseFragment {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;


	private List<String> dataList = new ArrayList<>();
	private CityNewsAdapter dataAdapter = new CityNewsAdapter(dataList);


	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				postEvent(new FragmentChangeEvent(FRAGMENT_HOME));
				break;
		}
	}


	@Override
	protected int setLayout() {
		return R.layout.activity_city_news;
	}

	@Override
	protected void initView() {
		dataList.add("1");
		dataList.add("2");
		dataList.add("3");
		dataList.add("4");

		tv_title.setText(getString(R.string.city_dynamic));

		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
	}

	@Override
	protected void initData() {

	}
}

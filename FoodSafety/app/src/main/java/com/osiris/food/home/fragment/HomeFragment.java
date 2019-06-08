package com.osiris.food.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.adapter.HomeNewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.osiris.food.home.MenuActivity.FRAGMENT_INFORMATION;
import static com.osiris.food.home.MenuActivity.FRAGMENT_NEWS;
import static com.osiris.food.home.MenuActivity.FRAGMENT_POLICY;

public class HomeFragment extends BaseFragment {


	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_news_title)
	TextView tv_news_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	@BindView(R.id.iv_title_pic)
	ImageView iv_title_pic;


	private List<String> dataList = new ArrayList<>();
	private HomeNewsAdapter dataAdapter = new HomeNewsAdapter(dataList);


	@Override
	protected int setLayout() {
		return R.layout.fragmnent_home;
	}

	@Override
	protected void initView() {

		dataList.add("1");
		dataList.add("2");
		dataList.add("3");
		dataList.add("4");

		tv_title.setText("泰州市海陵区食品安全培训");
		tv_news_title.setSelected(true);


		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

	}

	@Override
	protected void initData() {

	}


	@OnClick({R.id.linear_policy_regulation, R.id.linear_city_dynamic, R.id.linear_industry_information})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.linear_policy_regulation:
				postEvent(new FragmentChangeEvent(FRAGMENT_POLICY));
				break;
			case R.id.linear_city_dynamic:
				postEvent(new FragmentChangeEvent(FRAGMENT_NEWS));

				break;
			case R.id.linear_industry_information:
				postEvent(new FragmentChangeEvent(FRAGMENT_INFORMATION));

				break;
		}
	}
}

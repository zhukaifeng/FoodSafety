package com.osiris.food.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.exam.adapter.ExamAdapter;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExamFragment extends BaseFragment {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<String> dataList = new ArrayList<>();
	private ExamAdapter dataAdapter = new ExamAdapter(dataList);


	@Override
	protected int setLayout() {
		return R.layout.fragmnent_exam;
	}

	@Override
	protected void initView() {

		tv_title.setText(getString(R.string.exam));

		dataList.add("1");
		dataList.add("2");
		dataList.add("3");


		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

			}
		});


	}

	@Override
	protected void initData() {

	}
}

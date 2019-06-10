package com.osiris.food.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.mine.adapter.StudyAdapter;
import com.osiris.food.model.StudyCourse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MajorCourseFragment extends BaseFragment {


	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<StudyCourse> dataList = new ArrayList<>();
	private StudyAdapter dataAdapter = new StudyAdapter(dataList);






	@Override
	protected int setLayout() {
		return R.layout.fragment_public_course;
	}

	@Override
	protected void initView() {

//		主讲：食品安全
//		2018-01-01至2019-01-01
//		课程时长：3分21秒
//		已观看时长：0分0秒
		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));
		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));
		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));




		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
	}

	@Override
	protected void initData() {

	}
}

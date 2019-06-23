package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.mine.adapter.StudyAdapter;
import com.osiris.food.model.MyStudy;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.train.TrainContentLookActivity;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MajorCourseFragment extends BaseFragment {


	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<MyStudy.DataBean> dataList = new ArrayList<>();
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
//		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));
//		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));
//		dataList.add(new StudyCourse("食品安全","2018-01-01","2019-01-01","3分21秒","0分0秒"));




		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(getActivity(), TrainContentLookActivity.class);
				startActivity(intent);
			}
		});
		getStudySataDetail();

	}

	@Override
	protected void initData() {

	}

	private void getStudySataDetail() {

		String url = ApiRequestTag.API_HOST + "/api/v1/users/learning";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					MyStudy.DataBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonArray(), MyStudy.DataBean[].class);
					if (dataList.size()>0) {
						dataList.clear();
					}
					dataList.addAll(Arrays.asList(data));
					dataAdapter.notifyDataSetChanged();


				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});

	}


}

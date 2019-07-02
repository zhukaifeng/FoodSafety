package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.exam.ExamAnswersActivity;
import com.osiris.food.exam.adapter.ExamAdapter;
import com.osiris.food.model.ExamList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.jessyan.autosize.utils.LogUtils;

public class ExamFragment extends BaseFragment {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<ExamList.DataBean> dataList = new ArrayList<>();

	private ExamAdapter dataAdapter = new ExamAdapter(dataList);


	@Override
	protected int setLayout() {
		return R.layout.fragmnent_exam;
	}

	@Override
	protected void initView() {

		tv_title.setText(getString(R.string.exam));
		rl_back.setVisibility(View.GONE);


		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

				Intent intent = new Intent(getActivity(), ExamAnswersActivity.class);
				intent.putExtra("exam_id",dataList.get(position).getId());
				startActivity(intent);

			}
		});

		getData();


	}

	@Override
	protected void initData() {

	}



	private void getData(){

		showLoadDialog();
		String url = ApiRequestTag.API_HOST + "/api/v1/papers";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA,  new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					ExamList.DataBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonArray(), ExamList.DataBean[].class);
					if (dataList.size()>0) {
						dataList.clear();
					}

					dataList.addAll(Arrays.asList(data));
					dataAdapter.notifyDataSetChanged();
				}
				cancelLoadDialog();

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf  code: " + code);
				cancelLoadDialog();
			}
		});




	}


}

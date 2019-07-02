package com.osiris.food.edu;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.edu.adapter.ApplyEduAdapter;
import com.osiris.food.model.EduList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.dialog.ContinueEduDialog;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ApplyEduActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<EduList.DataBean> dataList = new ArrayList<>();
	private ApplyEduAdapter dataAdapter = new ApplyEduAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_apply_edu;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.apply_continue_eduction));


		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				LogUtils.d("zkf dasda click :" + position);
				showDialog();

			}
		});

		getEduList();


	}


	@OnClick({R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void getEduList() {
		showLoadDialog();

		String url = ApiRequestTag.API_HOST + "/api/v1/lessons";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					EduList.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(), EduList.DataBean[].class);
					dataList.addAll(Arrays.asList(dataBeans));
					dataAdapter.notifyDataSetChanged();
					cancelLoadDialog();

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
			}
		});

	}

	private void showDialog() {
		ContinueEduDialog.Builder preventBuilder = new ContinueEduDialog.Builder(this);
		preventBuilder.setPositiveButton(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});

		preventBuilder.create().show();
	}
}

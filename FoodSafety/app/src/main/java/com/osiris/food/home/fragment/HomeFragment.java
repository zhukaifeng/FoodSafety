package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.ContentDetailActivity;
import com.osiris.food.home.adapter.HomeNewsAdapter;
import com.osiris.food.model.Message;
import com.osiris.food.model.PolicyList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

import static com.osiris.food.home.MenuActivity.FRAGMENT_INFORMATION;
import static com.osiris.food.home.MenuActivity.FRAGMENT_NEWS;
import static com.osiris.food.home.MenuActivity.FRAGMENT_POLICY;
import static com.osiris.food.network.ApiRequestTag.REQUEST_DATA;

public class HomeFragment extends BaseFragment {


	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_news_title)
	TextView tv_news_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	@BindView(R.id.iv_title_pic)
	ImageView iv_title_pic;


	private List<PolicyList.DataBeanX.DataBean> dataList = new ArrayList<>();
	private HomeNewsAdapter dataAdapter = new HomeNewsAdapter(dataList);


	@Override
	protected int setLayout() {
		return R.layout.fragmnent_home;
	}

	@Override
	protected void initView() {


		tv_title.setText("泰州市海陵区食品安全培训");
		tv_news_title.setSelected(true);


		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(getActivity(), ContentDetailActivity.class);
				intent.putExtra("id",dataList.get(position).getId());
				startActivity(intent);
			}
		});
		getData();
		getMessage();

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





	private void getData() {
		String url = ApiRequestTag.API_HOST + "/api/v1/contents";
		showLoadDialog();

		NetRequest.requestNoParamWithToken(url, REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					PolicyList.DataBeanX.DataBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonObject().get("data").getAsJsonArray(), PolicyList.DataBeanX.DataBean[].class);
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
				LogUtils.d("zkf code :" + code);
				cancelLoadDialog();

			}
		});



	}

	private void getMessage(){

		String url = ApiRequestTag.API_HOST + "/api/v1/messages/carousel";

		NetRequest.requestNoParamWithToken(url, REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					Message.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(), Message.DataBean[].class);
					List<Message.DataBean> list = new ArrayList<>();
					list.addAll(Arrays.asList(dataBeans));
					if (list.size()>0){
						String msg = list.get(0).getSubject();
						tv_news_title.setText(msg);
					}
				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code :" + code);


			}
		});



	}




}

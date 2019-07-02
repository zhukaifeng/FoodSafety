package com.osiris.food.home;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.ContenDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ContentDetailActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.iv_content)
	ImageView ivContent;
	@BindView(R.id.tv_content)
	TextView tvContent;
	private int id = 0;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_content_detail;
	}

	@Override
	public void init() {

		id = getIntent().getIntExtra("id", 0);

		getData();

	}

	@OnClick({R.id.rl_back})
	void onClik(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}


	private void getData() {

		String url = ApiRequestTag.API_HOST + "/api/v1/contents/" + id;

		showLoadDialog();
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {

					ContenDetail.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(), ContenDetail.DataBean.class);
					tvContent.setText(Html.fromHtml(dataBean.getContent()));

					LogUtils.d("zkf dataBean.getThumb():" + dataBean.getThumb());
					/*if (!TextUtils.isEmpty(dataBean.getThumb())) {
						Picasso.with(mActivity)
								.load(dataBean.getThumb())
								.into(ivContent);
					}*/
					tvTitle.setText(dataBean.getTitle());
					cancelLoadDialog();

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
			}
		});

	}


}

package com.osiris.food.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.mine.ConnectUsActivity;
import com.osiris.food.mine.MyCertificationActivity;
import com.osiris.food.mine.MyNotificationActivity;
import com.osiris.food.mine.MyOrderActivity;
import com.osiris.food.mine.MyStudyActivity;
import com.osiris.food.mine.PersonInfoActivity;
import com.osiris.food.mine.ScoreRuleActivity;
import com.osiris.food.mine.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_study_score)
	TextView tv_study_score;
	@BindView(R.id.tv_name)
	TextView tv_name;
	@BindView(R.id.iv_avatar)
	ImageView iv_avatar;
	@BindView(R.id.iv_qr)
	ImageView iv_qr;




	@Override
	protected int setLayout() {
		return R.layout.fragmnent_mine;
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {

	}

	@OnClick({R.id.rl_mine_order,R.id.rl_mine_score,R.id.rl_mine_study,R.id.rl_mine_certificate,
			R.id.rl_mine_notification,R.id.rl_mine_contract,R.id.rl_mine_setting,R.id.rl_personal_info})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_mine_order:
				Intent intent = new Intent(getActivity(), MyOrderActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_mine_score:
				Intent intent2 = new Intent(getActivity(), ScoreRuleActivity.class);
				startActivity(intent2);
				break;
			case R.id.rl_mine_study:
				Intent intent3 = new Intent(getActivity(), MyStudyActivity.class);
				startActivity(intent3);

				break;
			case R.id.rl_mine_certificate:
				Intent intent4 = new Intent(getActivity(), MyCertificationActivity.class);
				startActivity(intent4);
				break;
			case R.id.rl_mine_notification:
				Intent intent5 = new Intent(getActivity(), MyNotificationActivity.class);
				startActivity(intent5);
				break;
			case R.id.rl_mine_contract:
				Intent intent6 = new Intent(getActivity(), ConnectUsActivity.class);
				startActivity(intent6);
				break;
			case R.id.rl_mine_setting:
				Intent intent7 = new Intent(getActivity(), SettingActivity.class);
				startActivity(intent7);

				break;
			case R.id.rl_personal_info:
				Intent intent8 = new Intent(getActivity(), PersonInfoActivity.class);
				startActivity(intent8);

				break;
		}
	}


}

package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



	private MyItemClickListener myItemClickListener;

	private List<String> dataList = new ArrayList<>();


	public StudyScoreAdapter(List<String> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_score, parent, false);//解决宽度不能铺满

		return new StudyScoreHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((StudyScoreHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class StudyScoreHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		@BindView(R.id.tv_study_title)
		TextView tvStudyTitle;
		@BindView(R.id.linear_title)
		LinearLayout linearTitle;
		@BindView(R.id.tv_btn_type)
		TextView tvBtnType;
		@BindView(R.id.tv_add_score)
		TextView tvAddScore;
		private MyItemClickListener myItemClickListener;

		public StudyScoreHolder(View itemView, MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			this.myItemClickListener = myItemClickListener;
			tvBtnType.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (null != myItemClickListener) {
				myItemClickListener.onItemClick(itemView, getLayoutPosition());
			}
		}

		public void bindData(String data) {


		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}
}



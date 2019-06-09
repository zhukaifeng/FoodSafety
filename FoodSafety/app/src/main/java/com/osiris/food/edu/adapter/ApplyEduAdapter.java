package com.osiris.food.edu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyEduAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<String> dataList = new ArrayList<>();


	public ApplyEduAdapter(List<String> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apply_edu, parent, false);//解决宽度不能铺满

		return new ApplyEduHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((ApplyEduHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class ApplyEduHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


		@BindView(R.id.tv_apply)
		TextView tv_apply;
		@BindView(R.id.tv_project_id)
		TextView tv_project_id;
		@BindView(R.id.tv_level)
		TextView tv_level;
		@BindView(R.id.tv_price)
		TextView tv_price;
		@BindView(R.id.tv_title)
		TextView tv_title;


		private MyItemClickListener myItemClickListener;

		public ApplyEduHolder(View itemView, MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			this.myItemClickListener = myItemClickListener;
			itemView.setOnClickListener(this);
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



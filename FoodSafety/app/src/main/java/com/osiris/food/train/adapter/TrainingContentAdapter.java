package com.osiris.food.train.adapter;

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

public class TrainingContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<String> dataList = new ArrayList<>();


	public TrainingContentAdapter(List<String> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itenm_training_content, parent, false);//解决宽度不能铺满

		return new TrainRecordHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((TrainRecordHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class TrainRecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


		@BindView(R.id.tv_content)
		TextView tv_content;
		@BindView(R.id.view_line)
		View view_line;


		private MyItemClickListener myItemClickListener;

		public TrainRecordHolder(View itemView, MyItemClickListener myItemClickListener) {
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

			tv_content.setText(data);
			if (dataList.size() > 0) {
				if (getLayoutPosition() == dataList.size() - 1) {
					view_line.setVisibility(View.GONE);
				} else {
					view_line.setVisibility(View.VISIBLE);
				}
			}

		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}
}



package com.osiris.food.exam.adapter;

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

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<String> dataList = new ArrayList<>();


	public ExamAdapter(List<String> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_list, parent,false);//解决宽度不能铺满

		return new ExamHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((ExamHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class ExamHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_exam_name)
		TextView tv_exam_name;
		@BindView(R.id.tv_subject_num)
		TextView tv_subject_num;
		@BindView(R.id.tv_history_score)
		TextView tv_history_score;




		private MyItemClickListener myItemClickListener;

		public ExamHolder(View itemView,MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this,itemView);
			this.myItemClickListener = myItemClickListener;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (null != myItemClickListener){
				myItemClickListener.onItemClick(itemView,getLayoutPosition());
			}
		}

		public void bindData(String data){



		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}



package com.osiris.food.model;

import java.util.List;

public class ExamList {


	/**
	 * status : success
	 * code : 200
	 * data : [{"id":1,"name":"2018～2019年度食品安全管理员初级考试","created_at":"2019-06-18 23:18:26","question_numbers":4,"history_score":0}]
	 */

	private String status;
	private int code;
	private List<DataBean> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 1
		 * name : 2018～2019年度食品安全管理员初级考试
		 * created_at : 2019-06-18 23:18:26
		 * question_numbers : 4
		 * history_score : 0
		 */

		private int id;
		private String name;
		private String created_at;
		private int question_numbers;
		private String history_score;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public int getQuestion_numbers() {
			return question_numbers;
		}

		public void setQuestion_numbers(int question_numbers) {
			this.question_numbers = question_numbers;
		}

		public String getHistory_score() {
			return history_score;
		}

		public void setHistory_score(String history_score) {
			this.history_score = history_score;
		}
	}
}

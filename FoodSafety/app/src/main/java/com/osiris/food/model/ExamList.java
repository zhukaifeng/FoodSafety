package com.osiris.food.model;

public class ExamList {


	/**
	 * status : success
	 * code : 200
	 * data : {"id":9,"name":"食品安全中级考试","created_at":"2019-07-02 23:31:57","question_numbers":20,"history_score":0,"times":0}
	 */

	private String status;
	private int code;
	private DataBean data;

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

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 9
		 * name : 食品安全中级考试
		 * created_at : 2019-07-02 23:31:57
		 * question_numbers : 20
		 * history_score : 0
		 * times : 0
		 */

		private int id;
		private String name;
		private String created_at;
		private int question_numbers;
		private String history_score;
		private int times;

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

		public int getTimes() {
			return times;
		}

		public void setTimes(int times) {
			this.times = times;
		}
	}
}

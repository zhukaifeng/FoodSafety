package com.osiris.food.model;

import java.util.List;

public class Message {


	/**
	 * status : success
	 * code : 200
	 * data : [{"subject":"市场监督总局关于调整《食品经营许可证》..."}]
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
		 * subject : 市场监督总局关于调整《食品经营许可证》...
		 */

		private String subject;

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}
	}
}

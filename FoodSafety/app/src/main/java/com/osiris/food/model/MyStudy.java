package com.osiris.food.model;

import java.util.List;

public class MyStudy {


	/**
	 * status : success
	 * code : 200
	 * data : [{"lesson_id":3,"category_name":"食品安全","name":"2018～2019年度高级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"","time":"00:00:12","visited":"00:00:00"},{"lesson_id":2,"category_name":"食品安全","name":"2018～2019年度中级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"","time":"05:25:00","visited":"00:00:00"}]
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
		 * lesson_id : 3
		 * category_name : 食品安全
		 * name : 2018～2019年度高级食品安全培训
		 * start_time : 2019-06-01
		 * end_time : 2020-06-01
		 * thumb :
		 * time : 00:00:12
		 * visited : 00:00:00
		 */

		private int lesson_id;
		private String category_name;
		private String name;
		private String start_time;
		private String end_time;
		private String thumb;
		private String time;
		private String visited;

		public int getLesson_id() {
			return lesson_id;
		}

		public void setLesson_id(int lesson_id) {
			this.lesson_id = lesson_id;
		}

		public String getCategory_name() {
			return category_name;
		}

		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStart_time() {
			return start_time;
		}

		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}

		public String getEnd_time() {
			return end_time;
		}

		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getVisited() {
			return visited;
		}

		public void setVisited(String visited) {
			this.visited = visited;
		}
	}
}

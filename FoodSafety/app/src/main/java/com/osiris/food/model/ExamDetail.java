package com.osiris.food.model;

import java.util.List;

public class ExamDetail {


	/**
	 * status : success
	 * code : 200
	 * data : {"id":1,"name":"2018～2019年度食品安全管理员初级考试","questions":{"DXT":"1","MDXT":"2","BDXT":"3","PDT":"4","JDT":null},"question_items":[[{"id":1,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A","type":"1"}],[{"id":2,"question":"根据增值税法律制度的规定，下列行为中，应按照\u201c销售不动 产\u201d税目计缴增值税的是（）。（2017年）","select":["将建筑物广告位出租给其他单位用于发布广告","销售底商（建筑物底层商铺）","转让高速公路经营权","转让国有土地使用权"],"answer":"A|D|","type":"2"}],[{"id":3,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A|B|C|D||","type":"3"}],[{"id":4,"question":"餐饮服务提供者要建立保证餐饮食品安全的各项制度，配备经考核合格的（）","select":["对","错"],"answer":"B","type":"4"}]]}
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
		 * id : 1
		 * name : 2018～2019年度食品安全管理员初级考试
		 * questions : {"DXT":"1","MDXT":"2","BDXT":"3","PDT":"4","JDT":null}
		 * question_items : [[{"id":1,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A","type":"1"}],[{"id":2,"question":"根据增值税法律制度的规定，下列行为中，应按照\u201c销售不动 产\u201d税目计缴增值税的是（）。（2017年）","select":["将建筑物广告位出租给其他单位用于发布广告","销售底商（建筑物底层商铺）","转让高速公路经营权","转让国有土地使用权"],"answer":"A|D|","type":"2"}],[{"id":3,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A|B|C|D||","type":"3"}],[{"id":4,"question":"餐饮服务提供者要建立保证餐饮食品安全的各项制度，配备经考核合格的（）","select":["对","错"],"answer":"B","type":"4"}]]
		 */

		private int id;
		private String name;
		private QuestionsBean questions;
		private List<List<QuestionItemsBean>> question_items;

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

		public QuestionsBean getQuestions() {
			return questions;
		}

		public void setQuestions(QuestionsBean questions) {
			this.questions = questions;
		}

		public List<List<QuestionItemsBean>> getQuestion_items() {
			return question_items;
		}

		public void setQuestion_items(List<List<QuestionItemsBean>> question_items) {
			this.question_items = question_items;
		}

		public static class QuestionsBean {
			/**
			 * DXT : 1
			 * MDXT : 2
			 * BDXT : 3
			 * PDT : 4
			 * JDT : null
			 */

			private String DXT;
			private String MDXT;
			private String BDXT;
			private String PDT;
			private Object JDT;

			public String getDXT() {
				return DXT;
			}

			public void setDXT(String DXT) {
				this.DXT = DXT;
			}

			public String getMDXT() {
				return MDXT;
			}

			public void setMDXT(String MDXT) {
				this.MDXT = MDXT;
			}

			public String getBDXT() {
				return BDXT;
			}

			public void setBDXT(String BDXT) {
				this.BDXT = BDXT;
			}

			public String getPDT() {
				return PDT;
			}

			public void setPDT(String PDT) {
				this.PDT = PDT;
			}

			public Object getJDT() {
				return JDT;
			}

			public void setJDT(Object JDT) {
				this.JDT = JDT;
			}
		}

		public static class QuestionItemsBean {
			/**
			 * id : 1
			 * question : 根据增值税法律制度的规定，下列各项中，应按照“销售服务 ——建筑服务”税目计缴增值税的是（）。（2017年）
			 * select : ["平整土地","出售住宅","出租办公楼","转让土地使用权"]
			 * answer : A
			 * type : 1
			 */

			private int id;
			private String question;
			private String answer;
			private String type;
			private List<String> select;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getQuestion() {
				return question;
			}

			public void setQuestion(String question) {
				this.question = question;
			}

			public String getAnswer() {
				return answer;
			}

			public void setAnswer(String answer) {
				this.answer = answer;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public List<String> getSelect() {
				return select;
			}

			public void setSelect(List<String> select) {
				this.select = select;
			}
		}
	}
}

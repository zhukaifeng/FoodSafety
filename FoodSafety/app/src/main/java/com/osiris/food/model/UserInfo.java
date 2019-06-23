package com.osiris.food.model;

public class UserInfo {


	/**
	 * status : success
	 * code : 200
	 * data : {"name":"charles","phone":"18370894190","avatar":"th.jpeg","gender":"男","birthday":"1999-08-11","address":null,"company":"","identity_card_type":1}
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
		 * name : charles
		 * phone : 18370894190
		 * avatar : th.jpeg
		 * gender : 男
		 * birthday : 1999-08-11
		 * address : null
		 * company :
		 * identity_card_type : 1
		 */

		private String name;
		private String phone;
		private String avatar;
		private String gender;
		private String birthday;
		private String address;
		private String company;
		private int identity_card_type;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public int getIdentity_card_type() {
			return identity_card_type;
		}

		public void setIdentity_card_type(int identity_card_type) {
			this.identity_card_type = identity_card_type;
		}
	}
}

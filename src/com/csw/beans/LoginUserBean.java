package com.csw.beans;

public class LoginUserBean {
	private int outid;
	private String username;
	private String password;
	private int gender;
	private int age;
	private String emailAddress;
	private String telephone;
	private String address;
	private String zhiye;

	// level���e��1Ϊ��������Ա����2Ϊ���ݲ����û��ļ���0Ϊ��ͨ�û�����ֻ�������Ҳ��ע���û��Ĺ��ܣ�
	private int level = 2;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOutid() {
		return outid;
	}

	public void setOutid(int outid) {
		this.outid = outid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZhiye() {
		return zhiye;
	}

	public void setZhiye(String zhiye) {
		this.zhiye = zhiye;
	}
}

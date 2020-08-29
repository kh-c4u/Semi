package com.kh.semi.member.vo;

import java.io.Serializable;

public class Member implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2891510656231932499L;
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String address;
	public Member() {
		super();
	}
	
	// 로그인용
	public Member(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	//회원가입용
	public Member(String userId, String userPwd, String userName, String email, String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.address = address;
	}





	public String getUserId() {
		return userId;
	}





	public String getUserPwd() {
		return userPwd;
	}





	public String getUserName() {
		return userName;
	}





	public String getEmail() {
		return email;
	}





	public String getAddress() {
		return address;
	}





	public void setUserId(String userId) {
		this.userId = userId;
	}





	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", email=" + email
				+ ", address=" + address + "]";
	}
	
	
	
	
	
}

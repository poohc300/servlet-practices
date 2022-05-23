package com.douzone.guestbook.vo;


public class MemberVo {

	private Long no;
	private String firstName;
	private String lastName;
	private String email;
	private String message;
	private String createdAt;

	
	public Long getNo() {
		return no;
	}
	public String getMessage() {
		return message;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

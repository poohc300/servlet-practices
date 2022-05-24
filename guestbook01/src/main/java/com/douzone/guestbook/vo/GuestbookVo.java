package com.douzone.guestbook.vo;

import java.util.Date;

public class GuestbookVo {

	private Long no;
	private String title;
	private String content;
	private Date current;
	private Long memberNo;
	
	public Long getNo() {
		return no;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Date getCurrent() {
		return current;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCurrent(Date current) {
		this.current = current;
	}
	
}

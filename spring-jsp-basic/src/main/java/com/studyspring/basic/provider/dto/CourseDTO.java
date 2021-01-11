package com.studyspring.basic.provider.dto;

import com.studyspring.basic.member.MemberDTO;

public class CourseDTO {
	private String title;
	private int idx;
	private String date;
	private MemberDTO member;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	
	
	
	
}

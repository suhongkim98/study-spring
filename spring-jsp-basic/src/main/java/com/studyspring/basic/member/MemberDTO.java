package com.studyspring.basic.member;

import com.studyspring.basic.core.Role;

public class MemberDTO {
	private int idx;
	private String memberName;
	private String memberId;
	private String memberPassword;
	private Role role;
	
	public MemberDTO(String id, String password) {
		this.memberId = id;
		this.memberPassword = password;
	}
	public MemberDTO() {
		
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

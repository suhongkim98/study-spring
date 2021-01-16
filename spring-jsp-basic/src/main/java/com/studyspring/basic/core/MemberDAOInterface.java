package com.studyspring.basic.core;

import com.studyspring.basic.member.MemberDTO;

public interface MemberDAOInterface {
	public void insert(String name, String id, String password, Role role);
	public MemberDTO find(String id);
	public MemberDTO vaildMember(String id, String password);
	public int delete(String id);
	public int modify(String id);
}

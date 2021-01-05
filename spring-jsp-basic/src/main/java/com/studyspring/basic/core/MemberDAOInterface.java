package com.studyspring.basic.core;

import com.studyspring.basic.member.MemberDTO;

public interface MemberDAOInterface {
	public void insert(String name, String id, String password, Role role);
	public MemberDTO find(String id);
	public void delete(String id);
	public void modify(String id);
}

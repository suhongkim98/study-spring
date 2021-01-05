package com.studyspring.basic.core;

import com.studyspring.basic.member.MemberDTO;

public interface MemberServiceInterface {
	public void registerMember(String name, String id, String password, Role role);
	public MemberDTO findMember(String id);
	public void deleteMember(String id);
	public void modifyMember(String id);
}

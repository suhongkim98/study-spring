package com.studyspring.basic.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyspring.basic.core.MemberDAOInterface;
import com.studyspring.basic.core.Role;

@Repository
public class MemberDAO implements MemberDAOInterface{
	private SqlSessionTemplate session;
	
	@Autowired
	public MemberDAO(SqlSessionTemplate sqlSessionTemplate) {
		this.session = sqlSessionTemplate;
	}
	@Override
	public void insert(String name, String id, String password, Role role) {
		MemberDTO member = new MemberDTO();
		member.setMemberName(name);
		member.setMemberId(id);
		member.setMemberPassword(password);
		member.setRole(role);
		
		session.insert("memberTable.insertMember", member); // rowid는 mapper에서 넣어지도록 지정했음
	}

	@Override
	public MemberDTO find(String id) {
		MemberDTO memberDTO = session.selectOne("memberTable.selectMember",id);
		return memberDTO;
	}
	
	@Override
	public MemberDTO vaildMember(String id, String password) {
		MemberDTO member = session.selectOne("memberTable.validMember", new MemberDTO(id, password));
		return member;
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

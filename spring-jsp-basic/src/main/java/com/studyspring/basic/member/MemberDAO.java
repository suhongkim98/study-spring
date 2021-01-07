package com.studyspring.basic.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate session;
	
	public MemberDTO select(String id) {
		MemberDTO memberDTO = session.selectOne("memberTable.selectMember",id);
		return memberDTO;
	}
}

package com.studyspring.basic.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyspring.basic.controller.dto.LoginRequestDTO;
import com.studyspring.basic.controller.dto.RegisterRequestDTO;
import com.studyspring.basic.core.MemberServiceInterface;
import com.studyspring.basic.core.Role;
import com.studyspring.basic.exception.LoginFailedException;
import com.studyspring.basic.exception.RegisterFailedException;

@Service
public class MemberService implements MemberServiceInterface{
	private MemberDAO memberDAO;
	
	@Autowired
	public MemberService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public Optional<MemberDTO> registerMember(RegisterRequestDTO registerRequestDTO) {
		MemberDTO member = findMember(registerRequestDTO.getMemberId()).orElseGet(() -> null);
		if(member != null) throw new RegisterFailedException("이미 해당 계정이 존재합니다.");
		
		member = memberDAO.insert(registerRequestDTO.getMemberName(), registerRequestDTO.getMemberId(), registerRequestDTO.getMemberPassword(), Role.USER);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<MemberDTO> findMember(String id) {
		MemberDTO member = memberDAO.find(id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<MemberDTO> loginMember(LoginRequestDTO loginRequestDTO) {
		MemberDTO member = memberDAO.vaildMember(loginRequestDTO.getMemberId(), loginRequestDTO.getMemberPassword());
		if(member == null) throw new LoginFailedException();
		return Optional.ofNullable(member);
	}

	@Override
	public void deleteMember(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyMember(String id) {
		// TODO Auto-generated method stub
		
	}
	
	
}

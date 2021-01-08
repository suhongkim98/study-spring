package com.studyspring.basic.core;

import java.util.Optional;

import com.studyspring.basic.controller.dto.LoginRequestDTO;
import com.studyspring.basic.controller.dto.RegisterRequestDTO;
import com.studyspring.basic.member.MemberDTO;

public interface MemberServiceInterface {
	public Optional<MemberDTO> registerMember(RegisterRequestDTO registerRequestDTO);
	public Optional<MemberDTO> findMember(String id);
	public Optional<MemberDTO> loginMember(LoginRequestDTO loginRequestDTO);
	public void deleteMember(String id);
	public void modifyMember(String id);
}

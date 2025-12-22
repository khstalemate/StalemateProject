package com.stale.mate.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.model.dto.Member;
import com.stale.mate.model.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Member login(Member inputMember) {
		
		Member loginMember = mapper.login(inputMember.getMemberId());
		
		if(loginMember == null) {
			
			return null;
		}
		
		if(!encoder.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			
			return null;
		}
		
		loginMember.setMemberPw(null);
		
		return loginMember;
	}

	/** 아이디 중복검사 서비스
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/18
	 *
	 */
	@Override
	public int checkId(Member memberId) {
		return mapper.checkId(memberId);
	}

	/** 닉네임 중복검사 서비스
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/18
	 *
	 */
	@Override
	public int checkName(String memberName) {
		
		return mapper.checkName(memberName);
	}

	/** 회원 가입 서비스
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/19
	 *
	 */
	@Override
	public int signup(Member inputMember) {
		
		String encPw = encoder.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(encPw);
		
		return mapper.signup(inputMember);
	}



}

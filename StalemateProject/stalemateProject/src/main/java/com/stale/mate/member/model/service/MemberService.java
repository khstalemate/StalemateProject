package com.stale.mate.member.model.service;

import com.stale.mate.member.model.dto.Member;

public interface MemberService {

	Member login(Member inputMember);

	int checkId(Member memberId);

	int checkName(String memberName);

	int signup(Member inputMember);

	Member selectMemberByNo(int memberNo);




}

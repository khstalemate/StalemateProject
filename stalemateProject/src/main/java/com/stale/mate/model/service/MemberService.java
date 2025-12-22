package com.stale.mate.model.service;

import com.stale.mate.model.dto.Member;

public interface MemberService {

	Member login(Member inputMember);

	int checkId(Member memberId);

	int checkName(String memberName);

	int signup(Member inputMember);


}

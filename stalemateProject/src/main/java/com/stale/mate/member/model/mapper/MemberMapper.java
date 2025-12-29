package com.stale.mate.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(String memberId);

	int checkId(Member memberId);

	int checkName(String memberName);

	int signup(Member inputMember);

	Member selectMemberByNo(int memberNo);

	int userCheck(String memberId, String memberPhone);

}



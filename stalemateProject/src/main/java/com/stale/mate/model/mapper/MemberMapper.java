package com.stale.mate.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(String memberId);

	int checkId(String memberId);

	int checkName(String memberName);

	int signup(Member inputMember);

}



package com.stale.mate.myPage.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 정보 업데이트
	 * @param memberNo 
	 * @param memberName
	 * @param memberPhone
	 * @param profileUploadResult
	 * @return
	 */
	int profileUpdate(Member updateMemberInfo);
	
}

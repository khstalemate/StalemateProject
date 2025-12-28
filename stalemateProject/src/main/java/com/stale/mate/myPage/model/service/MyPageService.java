package com.stale.mate.myPage.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.stale.mate.member.model.dto.Member;

public interface MyPageService {

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 이미지 업로드
	 * @param profileImg
	 * @return 
	 */
	String profileUpload(MultipartFile profileImg) throws Exception;

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

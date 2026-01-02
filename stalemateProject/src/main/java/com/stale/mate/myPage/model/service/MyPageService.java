package com.stale.mate.myPage.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.stale.mate.member.model.dto.Member;

public interface MyPageService {

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 이미지 업로드
	 */
	String profileUpload(MultipartFile profileImg) throws Exception;

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 정보 업데이트
	 */
	int profileUpdate(Member updateMemberInfo);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 비밀번호 변경 기능
	 */
	int changePw(Map<String, Object> paramMap, int memberNo);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 회원탈퇴 기능
	 */
	int deleteMember(Member loginMember);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 회원정보 수정 후, session 재설정
	 * @return 
	 */
    Member setLoginMemberInfo(Member loginMember);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 신고 데이터 가져오기
	 */
    Map<String, Object> selectReportList(int cp);


}

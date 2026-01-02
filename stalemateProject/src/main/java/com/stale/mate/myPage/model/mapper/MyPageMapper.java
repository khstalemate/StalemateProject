package com.stale.mate.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.stale.mate.member.model.dto.Member;
import com.stale.mate.myPage.model.dto.Report;

@Mapper
public interface MyPageMapper {

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 정보 업데이트
	 */
	int profileUpdate(Member updateMemberInfo);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 비밀번호 변경 기능 - 비밀번호 조회
	 */
	String selectPw(int memberNo);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 비밀번호 변경 기능 - 비밀번호 수정
	 */
	int changePw(Map<String, Object> paramMap);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 회원탈퇴 기능
	 */
	int deleteMember(Member loginMember);

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 회원정보 수정 후, session 재설정
	 */
    Member getLoginMemberInfo(String memberId);


	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 신고 데이터 총 개수
	 */
    int getReportCount();

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 신고 데이터 목록 가져오기
	 */
    List<Report> selectReportList(RowBounds rowBounds);
	

}

package com.stale.mate.myPage.model.service;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.stale.mate.member.model.dto.Member;
import com.stale.mate.myPage.model.mapper.MyPageMapper;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageMapper mapper; 

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 이미지 업로드
	 */
	@Override
	public String profileUpload(MultipartFile profileImg) throws Exception {
		if (profileImg.isEmpty()) { // 업로드한 파일이 없을 경우
			return null;
		}

		// 업로드한 파일이 있을 경우
		// C:/uploadFiles/profile/파일명 으로 서버에 저장
		profileImg.transferTo(new File("C:/uploadFiles/profile/" + profileImg.getOriginalFilename()));
		
		// /uploadFiles/profile/파일명 리턴 (ResourceHandler와 일치하는 경로)
		return "/uploadFiles/profile/" + profileImg.getOriginalFilename();
	}

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 정보 업데이트
	 */
	@Override
	public int profileUpdate(Member updateMemberInfo) {
		return mapper.profileUpdate(updateMemberInfo);
	}

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 비밀번호 변경 기능
	 */
	@Override
	public int changePw(Map<String, Object> paramMap, int memberNo) {
		// 현재 비밀번호가 일치하는지 확인하기
		String originPw = mapper.selectPw(memberNo);

		// 다를 경우
		if (!bcrypt.matches((String) paramMap.get("currentPw"), originPw)) {
			return 0;
		}

		// 같을 경우
		String encPw = bcrypt.encode((String) paramMap.get("newPw"));

		paramMap.put("encPw", encPw);
		paramMap.put("memberNo", memberNo);

		return mapper.changePw(paramMap);
	}

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 회원탈퇴 기능
	 */
	@Override
	public int deleteMember(Member loginMember) {
		return mapper.deleteMember(loginMember);
	}
}

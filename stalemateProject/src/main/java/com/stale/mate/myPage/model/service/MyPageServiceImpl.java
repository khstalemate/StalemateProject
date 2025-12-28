package com.stale.mate.myPage.model.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stale.mate.member.model.dto.Member;
import com.stale.mate.myPage.model.mapper.MyPageMapper;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageMapper mapper; 
	
	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능 - 프로필 이미지 업로드
	 * @param profileImg
	 * @return
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
	 * @param memberNo 
	 * @param memberName
	 * @param memberPhone
	 * @param profileUploadResult
	 * @return
	 */
	@Override
	public int profileUpdate(Member updateMemberInfo) {
		return mapper.profileUpdate(updateMemberInfo);
	}
}

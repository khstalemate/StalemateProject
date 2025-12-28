package com.stale.mate.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stale.mate.member.model.dto.Member;
import com.stale.mate.member.model.service.MemberService;
import com.stale.mate.myPage.model.service.MyPageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("myPage")
@Controller
@Slf4j
public class MyPageController {

	@Autowired
	private MyPageService service;
	
	@Autowired
	private MemberService memberService;
	

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 내정보 관리 이동
	 * @return
	 */
	@GetMapping("info")
	public String myPage() {
		return "myPage/mypage";
	}
	
	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 내정보 수정 이동
	 * @return
	 */
	@GetMapping("edit")
	public String myPageEdit() {
		return "myPage/mypage_edit";
	}


	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-28
	 * 마이페이지 - 내정보 수정 기능
	 * @return
	 */
	@PostMapping("edit")
	public String myPageEditInfo(@SessionAttribute(value = "loginMember") Member loginMember,
								@RequestParam("memberName") String memberName, 
								@RequestParam("memberPhone") String memberPhone, 
								@RequestParam("profileImg") MultipartFile profileImg, RedirectAttributes ra,
								HttpServletRequest req) {
		
		String profileUploadResult = null;
		
		try {
			profileUploadResult = service.profileUpload(profileImg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		// 프로필 이미지 업로드 여부와 관계없이 정보 수정 가능하도록 변경
		Member updateMemberInfo = new Member();
		updateMemberInfo.setMemberNo(loginMember.getMemberNo());
		updateMemberInfo.setMemberName(memberName);
		updateMemberInfo.setMemberPhone(memberPhone);
		
		// 이미지가 업로드 되었다면, 경로를 설정 (없으면 기존 이미지 유지)
		if (profileUploadResult != null) {
			updateMemberInfo.setProfileImg(profileUploadResult);
		}
		else{
			updateMemberInfo.setProfileImg(loginMember.getProfileImg());
		}

		//DB Update 구문 실행
		int result = service.profileUpdate(updateMemberInfo);

		if(result > 0) {
			ra.addFlashAttribute("message", "정보 수정에 성공하였습니다. 다시 로그인 해주세요!");
			//세션제거
			req.getSession().invalidate();
		} else {
			ra.addFlashAttribute("message", "정보 수정에 실패하였습니다.");
		}
		
		return "redirect:/"; 
	}

	/** 마이페이지 중복검사 기능
	 * 작성자 : 유건우
	 * 작성일 : 2025/12/28
	 */
	@ResponseBody
	@GetMapping("checkName")
	public int checkName(@SessionAttribute(value = "loginMember") Member loginMember,
						@RequestParam("memberName") String memberName) {
		// 접속한 계정과 닉네임이 바뀌지 않았다면
		if (loginMember.getMemberName().equals(memberName)) {
			return 0;
		}

		// 닉네임이 변경되었다면 중복검사 진행
		return memberService.checkName(memberName);
	}

	
	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 비밀번호 변경 이동
	 * @return
	 */
	@GetMapping("changePW")
	public String myPagePW() {
		return "myPage/mypage_changePW";
	}

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 신고페이지 이동
	 * @return
	 */
	@GetMapping("report")
	public String myPageReport(){
		return "myPage/mypage_report";
	}

}

package com.stale.mate.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 메인페이지 이동
	 * @return
	 */
	@RequestMapping("/")
	public String mainPage() {
		return "common/main";
	}

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 내정보 관리 이동
	 * @return
	 */
	@GetMapping("mypage")
	public String myPage() {
		return "common/mypage";
	}
	
	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 내정보 수정 이동
	 * @return
	 */
	@GetMapping("mypageedit")
	public String myPageEdit() {
		return "common/mypage_edit";
	}
	
	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 비밀번호 변경 이동
	 * @return
	 */
	@GetMapping("mypagepw")
	public String myPagePW() {
		return "common/mypage_changePW";
	}

	/** 작성자 : 유건우
	 * 작성일자 : 2025-12-22
	 * 마이페이지 - 신고페이지 이동
	 * @return
	 */
	@GetMapping("mypagereport")
	public String myPageReport(){
		return "common/mypage_report";
	}

	/*
	 * //로그인 필터에서 로그인하지 않았을 때 리다이렉트로 요청
	 * 
	 * @GetMapping("loginError") public String errorPage(RedirectAttributes ra) {
	 * ra.addFlashAttribute("message", "로그인 후 이용해주세요.");
	 * 
	 * return "redirect:/"; }
	 */
}

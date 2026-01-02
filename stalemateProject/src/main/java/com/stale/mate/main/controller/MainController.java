package com.stale.mate.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/** 작성자 : 이승준
	 * 작성일자 : 2025-12-29
	 * 비로그인시 알람메세지
	 */
	@GetMapping("loginError") 
	public String errorPage(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "로그인 후 이용해주세요.");
	  
		return "redirect:/"; 
	}


	/** 작성자 : 한명호
	 * 작성일자 : 2025-12-29
	 * 마이페이지 - 기업상세보기 이동
	 * @return
	 */
	@GetMapping("businessinformation")
	public String businessinformation(){
		return "common/businessinformation";
	}

	/** 작성자 : 한명호
	 * 작성일자 : 2025-12-29
	 * 마이페이지 - faq 이동
	 * @return
	 */
	@GetMapping("faq")
	public String faq(){
		return "common/faq";
	}

	/** 작성자 : 한명호
	 * 작성일자 : 2025-12-29
	 * 마이페이지 - 개인정보 이동
	 * @return
	 */
	@GetMapping("personalinformation")
	public String personalinformation(){
		return "common/personalinformation";
	}
	/** 작성자 : 한명호
	 * 작성일자 : 2025-12-29
	 * 마이페이지 - 이용약관 이동
	 * @return
	 */
	@GetMapping("termsnagreement")
	public String termsnagreement(){
		return "common/termsnagreement";
	}
	
	

}

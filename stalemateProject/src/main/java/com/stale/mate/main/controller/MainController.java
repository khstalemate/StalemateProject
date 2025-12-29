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
	
	
	
	/** 작성자 : 이승줁
	 * 작성일자 : 2025-12-29
	 * 비로그인시 알람메세지
	 */
	@GetMapping("loginError") 
	public String errorPage(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "로그인 후 이용해주세요.");
	  
		return "redirect:/"; 
	}
}

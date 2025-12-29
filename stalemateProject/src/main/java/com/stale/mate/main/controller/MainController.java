package com.stale.mate.main.controller;

import org.springframework.stereotype.Controller;
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
}

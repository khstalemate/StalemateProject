package com.stale.mate.email.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stale.mate.email.model.service.EmailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService service;
	
	/** 작성자 : 이승준
	 * 작성일 : 2025-12-23
	 * 이메일 보내고 성공여부 기능
	 */
	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody Map<String, String> map) {
		
		String email = map.get("email");
		String authKey = service.sendEmail("signup", email);
		
		if(authKey != null) {
			
			return 1;
		}
		
		return 0;
	}
	
	/** 작성자 : 이승준
	 * 작성일 : 2025-12-23
	 * 이메일 인증 확인 기능
	 */ 
	@ResponseBody
	@PostMapping("checkAuthKey")
	public int checkAuthKey(@RequestBody Map<String, String> map) {
		
		return service.checkAuthKey(map);
	}	
	
}

package com.stale.mate.email.controller;

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
	
	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody String email) {
		
		String authKey = service.sendEmail("signup", email);
		return 0;
	}
}

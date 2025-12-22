package com.stale.mate.email.model.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.stale.mate.email.model.mapper.EmailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	private final EmailMapper mapper;
	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;
	
	@Override
	public String sendEmail(String type, String email) {
		
		return null;
	}
}

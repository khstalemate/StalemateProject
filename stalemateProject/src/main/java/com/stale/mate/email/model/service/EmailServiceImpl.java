package com.stale.mate.email.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.stale.mate.email.model.mapper.EmailMapper;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	private final EmailMapper mapper;
	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;
	
	/** 작성자 : 이승준
	 * 작성일 : 2025-12-22
	 * 메일 보내는 기능 
	 */
	@Override
	public String sendEmail(String type, String email) {
		
		String authKey = createAuthKey();
		
		Map<String, String> map = new HashMap<>();
		map.put("authKey", authKey);
		map.put("email", email);
		
		if(!storeAuthKey(map)) return null;
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setTo(email);
			helper.setSubject("MatE 회원가입 인증번호 메일입니다.");
			helper.setText( loadhtml(authKey, type), true);
			helper.addInline("logo", new ClassPathResource("static/images/logo.jpg"));
			
			mailSender.send(mimeMessage);
			
			return authKey;
			
		}catch (Exception e){
			e.printStackTrace();
			
			return null;		
		}
		
	}
	
	/** 작성자 : 이승준
	 * 작성일 : 2025-12-23
	 * HTML 문자열 완성기능
	 */
	private String loadhtml(String authKey, String type) {

		Context context = new Context();
		context.setVariable("authKey", authKey);
		
		return templateEngine.process("email/" + type, context);
	}


	/** 작성자 : 이승준
	 * 작성일 : 2025-12-23
	 * 인증키와 이메일 DB저장 기능
	 */
	@Transactional(rollbackFor = Exception.class)
	private boolean storeAuthKey(Map<String, String> map) {
		
		int result = mapper.updateAuthKey(map);
		
		if(result == 0) {
			result = mapper.insertAuthKey(map);
		}
		
		return result > 0;
	}


	/** 작성자 : 이승준
	 * 작성일 : 2025-12-23
	 * 인증번호 발급 기능
	 */
	private String createAuthKey() {
		return UUID.randomUUID().toString().substring(0, 6);
	}

	@Override
	public int checkAuthKey(Map<String, String> map) {
		
		return mapper.checkAuthKey(map);
		
	}
}

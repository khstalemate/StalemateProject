package com.stale.mate.email.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private final BCryptPasswordEncoder encoder;
	
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

	/** 작성자 : 이승준
	 * 작성일 : 2025-12-23
	 * 인증번호 확인 기능
	 *
	 */
	@Override
	public int checkAuthKey(Map<String, String> map) {
		
		return mapper.checkAuthKey(map);
		
	}

	/** 작성자 : 이승준
	 * 작성일 : 2025-12-29
	 * 비밀번호 초기화 메일보내는 기능
	 *
	 */
	@Override
	public String resetPwEmail(String type, String memberId, String memberPhone) {
		
		int userCheck = mapper.userCheck(memberId, memberPhone);
		if(userCheck == 0) {
			
			return null;
		}
	
		String authKey = createAuthKey();
		
		Map<String, String> map = new HashMap<>();
		map.put("authKey", authKey);
		map.put("email", memberId);
		
		if(!storeAuthKey(map)) return null;
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setTo(memberId);
			helper.setSubject("MatE 비밀번호 초기화 인증번호 메일입니다.");
			helper.setText( resetAuthKeyLoadhtml(authKey), true);
			helper.addInline("logo", new ClassPathResource("static/images/logo.jpg"));
			
			mailSender.send(mimeMessage);
			
			return authKey;
			
		}catch (Exception e){
			e.printStackTrace();
			
			return null;		
		}
		
	}

	/** 작성자 : 이승준
	 *  작성일 : 2025-12-30
	 *  비밀번호 초기화 인증용 html 발송 기능
	 */
	private String resetAuthKeyLoadhtml(String authKey) {
		Context context = new Context();
		context.setVariable("authKey", authKey);
		
		return templateEngine.process("email/signup", context);

	}

	/** 작성자 : 이승준
	 * 작성일 : 2025-12-29
	 * 비밀번호 초기화 확인 기능
	 */
	@Override
	public int resetPwAuthKey(Map<String, String> map) {
		
		return mapper.checkAuthKey(map);
	}

	/** 작성자 : 이승준
	 *  작성일 : 2025-12-30
	 *  비밀번호 초기화 DB업데이트 + 메일 발송 함수 호출기능
	 *
	 */
	@Override
	public int resetPwIssue(String memberId) {
		
		 String tempPw = UUID.randomUUID().toString().substring(0, 6);
		 
		 String encPw = encoder.encode(tempPw);
		 
		 int result = mapper.resetPassword(memberId, encPw);
		 
		 if(result == 0) return 0;
		 
		 sendResetPasswordMail(memberId, tempPw);
		 
		 return 1;
	}

	/** 작성자 : 이승준
	 *  작성일 : 2025-12-30
	 *  비밀번호 초기화후 임시비밀번호 메일 발송 기능
	 */
	private void sendResetPasswordMail(String memberId, String tempPw) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setTo(memberId);
			helper.setSubject("MatE 임시 비밀번호 메일입니다.");
			helper.setText( resetPasswordLoadhtml(tempPw), true);
			helper.addInline("logo", new ClassPathResource("static/images/logo.jpg"));
			
			mailSender.send(mimeMessage);
			
		}catch (Exception e){
			e.printStackTrace();
			
	}


	}

	/** 작성자 : 이승준
	 *  작성일 : 2025-12-30
	 *  임시비밀번호 발송 html 기능
	 */
	private String resetPasswordLoadhtml(String tempPw) {
		
		Context context = new Context();
		context.setVariable("tempPw", tempPw);
		
		return templateEngine.process("email/resetPassword", context);
	}
	
}

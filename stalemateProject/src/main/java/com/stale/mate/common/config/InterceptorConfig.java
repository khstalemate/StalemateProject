package com.stale.mate.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stale.mate.common.interceptor.LoginCheckInterceptor;
// import com.stale.mate.common.interceptor.LoginKeepInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
//	@Autowired
//	private LoginKeepInterceptor loginInterceptor; 
	
	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;
	
	/** 작성자 : 이승준
	 *  작성일 : 2025-12-24 
	 *  인터셉터 객체 추가 기능
	 *	
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		/*
		 * registry.addInterceptor(loginInterceptor) .addPathPatterns("/**")
		 * .excludePathPatterns("/css/**", "/js/**", "/favicon.ico");
		 */
		
		registry.addInterceptor(loginCheckInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/", "/loginError", "/member/login", "/member/signup",
							 "/member/checkId", "/member/checkName", "/email/**",
							 "/member/resetPw", "/;jsessionid=**",
							 "/css/**", "/js/**", "/favicon.ico");
		
	}
	

}



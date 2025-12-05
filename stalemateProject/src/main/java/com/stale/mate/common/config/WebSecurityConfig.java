package com.stale.mate.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


/* @author : 유건우
 * @date : 2024-06-11
 * @brief : Spring Security 설정 클래스 - 모든 요청에 대해 인증 없이 접근 허용
 */

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll());
		
		return http.build();
	}
}
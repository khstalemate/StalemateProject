package com.stale.mate.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stale.mate.member.model.dto.Member;
import com.stale.mate.member.model.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/** 미완성 로그인/로그인유지 기능, 테스트후 수정예정
	 * 
	 */
	@PostMapping("login")
	public String login(Member inputMember,
						RedirectAttributes ra,
						Model model,
						@RequestParam(value = "loginKeep", required = false) String loginkeep,
						HttpServletResponse resp,
						HttpServletRequest req) {
		
		Member loginMember = service.login(inputMember);
		
		if(loginMember == null) {
			
			ra.addFlashAttribute("message", "로그인 실패, 아이디 또는 비밀번호가 일치하지 않습니다.");
			
		}else {
			
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("loginKeep", "Y");
			cookie.setPath("/");
			
			if(loginkeep != null) {
				
				req.getSession().setMaxInactiveInterval(60*60*24*14);
				cookie.setMaxAge(60*60*24*14);
				
			}else {
					
				cookie.setMaxAge(0);
				
			}
			
			resp.addCookie(cookie);
			
		}
		
		return "redirect:/";
	}
	
	/** 로그아웃 기능
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/18
	 */
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:/";
	}
	
	/** 회원가입 페이지 이동
	 *  작성자 : 이승준
	 *  작성일 : 2025/12/18
	 */
	@GetMapping("signup")
	public String signupPage() {
		
		return "common/membership";
	}
	
	/** 아이디 중복검사 기능
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/18
	 */
	@ResponseBody
	@PostMapping("checkId")
	public int checkId(@RequestParam("memberId") String memberId) {
		
		return service.checkId(memberId);
	}
	
	/** 닉네임 중복검사 기능
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/18
	 */
	@ResponseBody
	@GetMapping("checkName")
	public int checkName(@RequestParam("memberName") String memberName) {
		
		return service.checkName(memberName);
	}
	
	/** 회원가입 기능
	 * 작성자 : 이승준
	 * 작성일 : 2025/12/18
	 * 
	 */
	@PostMapping("signup")
	public String signup(Member inputMember,
						 RedirectAttributes ra) {
		
		int result = service.signup(inputMember);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			message = inputMember.getMemberName() + "님이 회원가입에 성공하였습니다!";
			path =  "/";
			
		}else {
			message = "회원가입에 실패하였습니다.";
			path = "signup";
		
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
}

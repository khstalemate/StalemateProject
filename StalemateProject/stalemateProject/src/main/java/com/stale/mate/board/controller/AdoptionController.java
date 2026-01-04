package com.stale.mate.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stale.mate.board.model.service.AdoptionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("adoption")
@Slf4j
public class AdoptionController {
	
	@Autowired
	private AdoptionService service;
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회
	 * @return
	 */
	@GetMapping("/")
	public String adoption(@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			Model model) {
		Map<String, Object> map = null;
		map = service.selectPostList(cp);
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("postList", map.get("postList"));
		
		return "adoption/adoption";
	}
	
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 분양, 입양 게시판의 총 게시글 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getAllPostCount")
	public int getAllPostCount() {
		return service.getAllPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 상태가 분양인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getSalePostCount")
	public int getSalePostCount() {
		return service.getSalePostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 상태가 입양인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getAdoptionPostCount")
	public int getAdoptionPostCount() {
		return service.getAdoptionPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getTodayPostCount")
	public int getTodayPostCount() {
		return service.getTodayPostCount();
	}
}

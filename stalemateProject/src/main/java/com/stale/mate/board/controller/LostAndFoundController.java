package com.stale.mate.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stale.mate.board.model.service.LostAndFoundService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("lostandfound")
@Slf4j
public class LostAndFoundController {

	@Autowired
	private LostAndFoundService service;
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회(2025-12-23), 검색 기능 추가(2025-12-)
	 * @return
	 */
	@GetMapping("/")
	public String lostandfound(@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model,
							@RequestParam Map<String, Object> paramMap) {
		Map<String, Object> map = null;
		map = service.selectPostList(cp);
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("postList", map.get("postList"));
		
		return "lostandfound/lostandfound";
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 실종, 목격 게시판의 총 게시글 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getAllPostCount")
	public int getAllPostCount() {
		return service.getAllPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getLostPostCount")
	public int getLostPostCount() {
		return service.getLostPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getWitnessPostCount")
	public int getWitnessPostCount() {
		return service.getWitnessPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getTodayPostCount")
	public int getTodayPostCount() {
		return service.getTodayPostCount();
	}
	
	
}
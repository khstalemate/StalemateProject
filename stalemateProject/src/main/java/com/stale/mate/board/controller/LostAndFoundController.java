package com.stale.mate.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stale.mate.board.model.service.LostAndFoundService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("lostandfound")
@Slf4j
public class LostAndFoundController {

	@Autowired
	private LostAndFoundService service;
	
	@GetMapping("/")
	public String lostandfound() {
		return "lostandfound/lostandfound";
	}
	
	/**
	 * 실종, 분양 게시판의 총 게시글 개수 가져오기
	 * @return int 총 게시글 개수
	 */
	@ResponseBody
	@GetMapping("getAllPostCount")
	public int getAllPostCount() {
		return service.getAllPostCount();
	}
	
	/**
	 * 상태가 실종인 게시글의 개수 가져오기
	 * @return
	 */
	@ResponseBody
	@GetMapping("getLostPostCount")
	public int getLostPostCount() {
		return service.getLostPostCount();
	}
	
	/**
	 * 상태가 목격인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getWitnessPostCount")
	public int getWitnessPostCount() {
		return service.getWitnessPostCount();
	}
	
	/**
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getTodayPostCount")
	public int getTodayPostCount() {
		return service.getTodayPostCount();
	}
}
package com.stale.mate.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stale.mate.board.model.dto.Reply;
import com.stale.mate.board.model.service.ReplyService;

@RestController
@RequestMapping("reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	/**
	 * 작성자 : 최보윤	
	 * 작성일자 : 2026-01-02
	 * 댓글 목록 조회하기
	 */
	@GetMapping("")
	public List<Reply> selectReply(@RequestParam("postNo") int postNo) {
		return service.selectReply(postNo);
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 등록하기
	 */
	@PostMapping("")
	public int insertReply(@RequestBody Reply reply) {
		return service.insertReply(reply);
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 수정하기
	 */
	@PutMapping("")
	public int updateReply(@RequestBody Reply reply) {
		return service.updateReply(reply);
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 삭제하기
	 */
	@DeleteMapping("")
	public int deleteReply(@RequestBody int replyNo) {
		return service.deleteReply(replyNo);
	}

}

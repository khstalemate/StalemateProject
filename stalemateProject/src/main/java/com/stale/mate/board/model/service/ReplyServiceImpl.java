package com.stale.mate.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.board.model.dto.Reply;
import com.stale.mate.board.model.mapper.ReplyMapper;

@Service
@Transactional(rollbackFor=Exception.class)
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 목록 조회하기
	 */
	@Override
	public List<Reply> selectReply(int postNo) {
		return mapper.selectReply(postNo);
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 등록하기
	 */
	@Override
	public int insertReply(Reply reply) {
		return mapper.insertReply(reply);
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 수정하기
	 */
	@Override
	public int updateReply(Reply reply) {
		return mapper.updateReply(reply);
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 삭제하기
	 */
	@Override
	public int deleteReply(int replyNo) {
		return mapper.deleteReply(replyNo);
	}

}
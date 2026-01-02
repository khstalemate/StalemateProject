package com.stale.mate.board.model.service;

import java.util.List;

import com.stale.mate.board.model.dto.Reply;

public interface ReplyService {

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 목록 조회하기
	 */
	List<Reply> selectReply();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 등록하기
	 */
	int insertReply(Reply reply);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 수정하기
	 */
	int updateReply(Reply reply);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 삭제하기
	 */
	int deleteReply(int replyNo);
}

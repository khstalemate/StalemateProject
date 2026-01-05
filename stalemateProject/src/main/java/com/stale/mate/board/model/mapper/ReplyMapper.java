package com.stale.mate.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.board.model.dto.Reply;

@Mapper
public interface ReplyMapper {
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 목록 조회 SQL
	 * @param postNo 
	 */
	List<Reply> selectReply(int postNo);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 등록 SQL
	 */
	int insertReply(Reply reply);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 수정 SQL
	 */
	int updateReply(Reply reply);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 댓글 삭제 SQL
	 */
	int deleteReply(int replyNo);



}

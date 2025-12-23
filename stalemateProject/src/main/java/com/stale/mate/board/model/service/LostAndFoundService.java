package com.stale.mate.board.model.service;

import java.util.Map;

public interface LostAndFoundService {

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 실종, 분양 게시판의 총 게시글 개수 가져오기
	 * @return
	 */
	int getAllPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오기
	 * @return
	 */
	int getLostPostCount();

	/**	
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오기
	 * @return
	 */
	int getWitnessPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 * @return
	 */
	int getTodayPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회
	 * @param cp (현재 페이지 번호)
	 * @return
	 */
	Map<String, Object> selectPostList(int cp);
}
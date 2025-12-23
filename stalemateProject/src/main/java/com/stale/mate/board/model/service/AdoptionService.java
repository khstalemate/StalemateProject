package com.stale.mate.board.model.service;

import java.util.Map;

public interface AdoptionService {
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 분양, 입양 게시판의 총 게시글 개수 가져오기
	 * @return
	 */
	int getAllPostCount();

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 상태가 분양인 게시글의 개수 가져오기
	 * @return
	 */
	int getSalePostCount();

	/**	
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 상태가 입양인 게시글의 개수 가져오기
	 * @return
	 */
	int getAdoptionPostCount();

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 * @return
	 */
	int getTodayPostCount();

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회
	 * @param cp (현재 페이지 번호)
	 * @return
	 */
	Map<String, Object> selectPostList(int cp);
}

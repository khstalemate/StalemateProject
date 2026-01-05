package com.stale.mate.board.model.service;

import java.util.Map;

import com.stale.mate.board.model.dto.Post;

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
	 */
	Map<String, Object> selectPostList(int cp);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 검색 결과에 부합하는 게시글 목록 조회
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 상세 정보 가져오기
	 */
	Post getPost(int postNo);
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 조회수 증가하기
	 */
	int updateViews(int postNo);

}

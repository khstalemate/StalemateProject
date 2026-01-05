package com.stale.mate.board.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.stale.mate.board.model.dto.Post;

public interface LostAndFoundService {

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 실종, 분양 게시판의 총 게시글 개수 가져오기
	 */
	int getAllPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오기
	 */
	int getLostPostCount();

	/**	
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오기
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
	 */
	Map<String, Object> selectPostList(int cp);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-24
	 * 검색 결과에 부합하는 게시글 목록 조회
	 * @param paramMap (사용자가 입력한 검색 조건이 담긴 맵)
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * 게시글 상세 정보 가져오기
	 */
	Post getPost(int postNo);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * 게시글 작성하기
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int insertPost(Post inputPost, List<MultipartFile> images) throws IllegalStateException, IOException;

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-01
	 * 상태값 변경하기
	 */
	int updateStatus(int postNo, String status);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-02
	 * 조회수 증가하기
	 */
	int updateViews(int postNo);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 수정
	 */
	int updatePost(Post inputPost, List<MultipartFile> images) throws IllegalStateException, IOException;

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 삭제
	 */
	int deletePost(Map<String, Integer> map);
}
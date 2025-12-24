package com.stale.mate.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.board.model.dto.Pagination;
import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.mapper.LostAndFoundMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class LostAndFoundServiceImpl implements LostAndFoundService {
	
	@Autowired
	private LostAndFoundMapper mapper;

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 실종, 목격 게시판의 총 게시글 개수 가져오기
	 */
	@Override
	public int getAllPostCount() {
		return mapper.getAllPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오기
	 */
	@Override
	public int getLostPostCount() {
		return mapper.getLostPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오기
	 */
	@Override
	public int getWitnessPostCount() {
		return mapper.getWitnessPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@Override
	public int getTodayPostCount() {
		return mapper.getTodayPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회
	 */
	@Override
	public Map<String, Object> selectPostList(int cp) {
		int listCount = mapper.getAllPostCount();
		Pagination pagination = new Pagination(cp, listCount);
		int limit = pagination.getLimit();
		
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Post> postList = mapper.selectPostList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("postList", postList);
		
		return map;
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-24
	 * 검색 결과에 부합하는 게시글 목록 조회
	 */
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.getSearchCount(paramMap);
		Pagination pagination = new Pagination(cp, listCount);
		int limit = pagination.getLimit();
		
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Post> postList = mapper.selectSearchList(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postList", postList);
		map.put("pagination", pagination);
		
		return map;
	}
}

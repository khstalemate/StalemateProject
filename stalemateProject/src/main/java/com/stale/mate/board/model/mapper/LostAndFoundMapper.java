package com.stale.mate.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.stale.mate.board.model.dto.Post;

@Mapper
public interface LostAndFoundMapper {

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 총 게시글 가져오기 SQL
	 * @return
	 */
	int getAllPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오는 SQL
	 * @return
	 */
	int getLostPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오는 SQL
	 * @return
	 */
	int getWitnessPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오는 SQL
	 * @return
	 */
	int getTodayPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회 SQL
	 * @param rowBounds
	 * @return
	 */
	List<Post> selectPostList(RowBounds rowBounds);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-24
	 * 검색 결과에 부합하는 게시글 개수 가져오는 SQL
	 * @param paramMap (사용자가 입력한 검색 조건이 담긴 맵)
	 */
	int getSearchCount(Map<String, Object> paramMap);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-24
	 * 검색 결과에 부합하는 게시글 목록 조회 SQL
	 * @param paramMap (사용자가 입력한 검색 조건이 담긴 맵)
	 * @param rowBounds
	 */
	List<Post> selectSearchList(Map<String, Object> paramMap, RowBounds rowBounds);
}

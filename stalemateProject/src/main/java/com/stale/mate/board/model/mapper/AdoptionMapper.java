package com.stale.mate.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.dto.PostImg;

@Mapper
public interface AdoptionMapper {

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 총 게시글 가져오기 SQL
	 */
	int getAllPostCount();

	int getSalePostCount();

	int getAdoptionPostCount();

	int getTodayPostCount();

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 목록 조회 SQL
	 * @param rowBounds
	 */
	List<Post> selectPostList(RowBounds rowBounds);
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 검색 결과에 부합하는 게시글 개수 가져오는 SQL
	 * @param paramMap (사용자가 입력한 검색 조건이 담긴 맵)
	 */
	int getSearchCount(Map<String, Object> paramMap);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 검색 결과에 부합하는 게시글 목록 조회 SQL
	 * @param paramMap (사용자가 입력한 검색 조건이 담긴 맵)
	 */
	List<Post> selectSearchList(Map<String, Object> paramMap, RowBounds rowBounds);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 상세 정보 가져오는 SQL
	 */
	Post getPost(int postNo);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글의 조회수를 증가하는 SQL 
	 */
	int updateViews(int postNo);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글의 조회수를 조회하는 SQL 
	 */
	int selectViews(int postNo);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글을 삽입한 후 삽입된 게시글의 시퀀스 번호 가져오는 SQL 
	 */
	int insertPost(Post inputPost);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 삽입하고자 하는 게시글에 이미지 등록하는 SQL 
	 */
	int insertUploadList(List<PostImg> uploadList);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글의 상태값을 수정하는 SQL 
	 */
	int updateStatus(Map<String, Object> map);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 수정(이미지 제외)하는 SQL
	 */
	int updatePost(Post inputPost);
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 이미지 수정 SQL
	 */
	int updatePostImg(PostImg img);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 이미지 삽입 SQL
	 */
	int insertPostImg(PostImg img);

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 삭제
	 */
	int deletePost(Map<String, Integer> map);


}

package com.stale.mate.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.stale.mate.board.model.dto.Post;

@Mapper
public interface LostAndFoundMapper {

	// 총 게시글 가져오기 SQL
	int getAllPostCount();

	// 상태가 실종인 게시글의 개수 가져오는 SQL
	int getLostPostCount();

	// 상태가 목격인 게시글의 개수 가져오는 SQL
	int getWitnessPostCount();

	// 오늘 등록한 게시글의 개수 가져오는 SQL
	int getTodayPostCount();

	// 게시글 목록 조회 SQL
	List<Post> selectPostList(RowBounds rowBounds);
}

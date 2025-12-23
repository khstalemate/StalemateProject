package com.stale.mate.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.stale.mate.board.model.dto.Post;

@Mapper
public interface AdoptionMapper {

	int getAllPostCount();

	int getSalePostCount();

	int getAdoptionPostCount();

	int getTodayPostCount();

	List<Post> selectPostList(RowBounds rowBounds);

}

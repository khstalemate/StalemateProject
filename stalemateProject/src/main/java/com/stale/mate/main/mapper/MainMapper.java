package com.stale.mate.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.board.model.dto.Post;

@Mapper
public interface MainMapper {

	List<Post> selectLostandfoundList();

}

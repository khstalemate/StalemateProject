package com.stale.mate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.main.mapper.MainMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MainServiceImpl implements MainService{

	@Autowired
	private MainMapper mapper;

	/** 작성자 : 이승준
	 * 작성일자 : 2026-01-02
	 * 분양/실종 게시판 조회용
	 */
	@Override
	public List<Post> selectLostandfoundList() {
		
		return mapper.selectLostandfoundList();
	}

	/** 작성자 : 이승준
	 * 작성일자 : 2026-01-02
	 * 입양/분양 게시판 조회용
	 */
	@Override
	public List<Post> selectAdoptionList() {
		
		return mapper.selectAdoptionList();
	}
	
	
}

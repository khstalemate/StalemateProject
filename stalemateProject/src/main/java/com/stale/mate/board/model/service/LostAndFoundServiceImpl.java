package com.stale.mate.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.board.model.mapper.LostAndFoundMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class LostAndFoundServiceImpl implements LostAndFoundService {
	
	@Autowired
	private LostAndFoundMapper mapper;

	// 실종, 분양 게시판의 총 게시글 개수 가져오기
	@Override
	public int getAllPostCount() {
		return mapper.getAllPostCount();
	}

	// 상태가 실종인 게시글의 개수 가져오기
	@Override
	public int getLostPostCount() {
		return mapper.getLostPostCount();
	}

	// 상태가 목격인 게시글의 개수 가져오기
	@Override
	public int getWitnessPostCount() {
		return mapper.getWitnessPostCount();
	}

	// 오늘 등록한 게시글의 개수 가져오기
	@Override
	public int getTodayPostCount() {
		return mapper.getTodayPostCount();
	}
}

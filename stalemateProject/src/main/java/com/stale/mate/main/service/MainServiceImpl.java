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

	@Override
	public List<Post> selectLostandfoundList() {
		
		return mapper.selectLostandfoundList();
	}
	
	
}

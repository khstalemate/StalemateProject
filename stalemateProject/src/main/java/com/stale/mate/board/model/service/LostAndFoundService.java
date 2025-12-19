package com.stale.mate.board.model.service;

public interface LostAndFoundService {

	// 실종, 분양 게시판의 총 게시글 개수 가져오기
	int getAllPostCount();

	int getLostPostCount();

	int getWitnessPostCount();

	// 오늘 등록한 게시글의 개수 가져오기
	int getTodayPostCount();

}

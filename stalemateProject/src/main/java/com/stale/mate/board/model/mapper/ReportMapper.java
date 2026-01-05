package com.stale.mate.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.myPage.model.dto.Report;

@Mapper
public interface ReportMapper {
	int insertReport(Report report);

	//2026-01-06 유건우 수정 - 신고가 된 경우, 재신고 방지
    int checkReport(int report);

	//2026-01-06 유건우 수정 - 글 가져와서 DTO에 세팅 하기 위한 메소드
    Post getPost(int postNo);
}
package com.stale.mate.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.mapper.ReportMapper;
import com.stale.mate.myPage.model.dto.Report;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportMapper mapper;
	
	@Override
	public int insertReport(Report report) {
		Post post = mapper.getPost(report.getPostNo());
		report.setAuthorName(post.getMemberName());
		report.setPostNo(post.getPostNo());

		return mapper.insertReport(report);
	}

	//2026-01-06 유건우 수정 - 신고가 된 경우, 재신고 방지
	@Override
	public int checkReport(Report report) {
		return mapper.checkReport(report.getPostNo());
	}
}

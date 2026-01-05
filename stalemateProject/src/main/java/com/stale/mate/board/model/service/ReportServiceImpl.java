package com.stale.mate.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.mapper.LostAndFoundMapper;
import com.stale.mate.board.model.mapper.ReportMapper;
import com.stale.mate.myPage.model.dto.Report;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportMapper mapper;

	@Autowired
	private LostAndFoundMapper lostAndFoundMapper;
	
	@Override
	public int insertReport(Report report) {
		Post post = lostAndFoundMapper.getPost(report.getPostNo());
		report.setAuthorName(post.getMemberName());
		report.setPostNo(post.getPostNo());

		return mapper.insertReport(report);
	}
}

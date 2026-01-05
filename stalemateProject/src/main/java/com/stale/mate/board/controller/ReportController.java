package com.stale.mate.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.stale.mate.board.model.service.ReportService;
import com.stale.mate.member.model.dto.Member;
import com.stale.mate.myPage.model.dto.Report;

@RestController
@RequestMapping("report")
public class ReportController {
	@Autowired
	private ReportService service;

	@PostMapping("")
	public int insertReport(@RequestBody Report report, @SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		report.setMemberNo(loginMember.getMemberNo());

		//2026-01-06 유건우 수정 - 신고가 된 경우, 재신고 방지
		int result = 0;
		result = service.checkReport(report);

		if(result == 0) {
			return service.insertReport(report);
		}
		else{
			return -1;
		}
		
	}
}
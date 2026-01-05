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

		return service.insertReport(report);
	}
}
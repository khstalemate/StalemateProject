package com.stale.mate.board.model.service;

import com.stale.mate.myPage.model.dto.Report;

public interface ReportService {

	int insertReport(Report report);

	//2026-01-06 유건우 수정 - 신고가 된 경우, 재신고 방지
    int checkReport(Report report);

}
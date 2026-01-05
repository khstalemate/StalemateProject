package com.stale.mate.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.stale.mate.myPage.model.dto.Report;

@Mapper
public interface ReportMapper {
	int insertReport(Report report);
}
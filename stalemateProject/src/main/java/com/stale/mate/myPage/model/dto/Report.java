package com.stale.mate.myPage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private int reportNo;
    private String authorId;
    private String authorName;
    private String postTitle;
    private String content;
    private String reportReason;
    private String reporterName;
    private String reportDate;
    private int postNo;
    private int memberNo;
    private String category;
}
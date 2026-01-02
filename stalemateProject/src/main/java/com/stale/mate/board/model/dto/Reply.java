package com.stale.mate.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	private int replyNo;
	private String replyContent;
	private String replyTime;
	private String replyUpdate;
	private String replyDelete;
	private int postNo;
	private int memberNo;
	private String memberName;
	private String profileImg;
}
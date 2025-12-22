package com.stale.mate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private int memberNo;
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberPhone;
	private String memberDelFl;
	private String enrollDate;
	private String updateDate;
	private int authority;
	private String profileImg;
	
}


package com.stale.mate.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	private int postNo;
	private String postTitle;
	private String postDate;
	private String category;
	private String status;
	private String postDelete;
	private int views;
	private int adoptFee;
	private String missingDate;
	private String missingTime;
	private String location;
	private String species;
	private String gender;
	private int age;
	private int weight;
	private String content;
	private int menuNo;
	private int memberNo;
}
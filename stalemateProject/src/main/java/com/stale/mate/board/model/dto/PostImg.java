package com.stale.mate.board.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostImg {
	private int imgNo;
	private String imgPath;
	private String imgOriginalName;
	private String imgRename;
	private int postNo;
	private MultipartFile uploadFile;
}
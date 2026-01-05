package com.stale.mate.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stale.mate.board.model.dto.Pagination;
import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.dto.PostImg;
import com.stale.mate.board.model.mapper.AdoptionMapper;
import com.stale.mate.common.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdoptionServiceImpl implements AdoptionService{
	
	@Autowired
	private AdoptionMapper mapper;
	
	@Value("${board.web-path}")
	private String webPath;
	
	@Value("${board.folder-path}")
	private String folderPath;
	
	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 분양, 입양 게시판의 총 게시글 개수 가져오기
	 */
	@Override
	public int getAllPostCount() {
		return mapper.getAllPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 상태가 분양인 게시글의 개수 가져오기
	 */
	@Override
	public int getSalePostCount() {
		return mapper.getSalePostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 상태가 입양인 게시글의 개수 가져오기
	 */
	@Override
	public int getAdoptionPostCount() {
		return mapper.getAdoptionPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@Override
	public int getTodayPostCount() {
		return mapper.getTodayPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 수정자 : 유건우
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회
	 */
	@Override
	public Map<String, Object> selectPostList(int cp) {
		int listCount = mapper.getAllPostCount();
		Pagination pagination = new Pagination(cp, listCount);
		int limit = pagination.getLimit();
		
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Post> postList = mapper.selectPostList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("postList", postList);
		
		return map;
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 검색 결과에 부합하는 게시글 목록 조회
	 */
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.getSearchCount(paramMap);
		Pagination pagination = new Pagination(cp, listCount);
		int limit = pagination.getLimit();
		
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Post> postList = mapper.selectSearchList(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postList", postList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 상세 정보 가져오기
	 */
	@Override
	public Post getPost(int postNo) {
		return mapper.getPost(postNo);
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 조회수 증가하기
	 */
	@Override
	public int updateViews(int postNo) {
		int result = mapper.updateViews(postNo);
		
		if(result > 0) {
			return mapper.selectViews(postNo);
		}
		
		return -1;
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 작성하기
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Override
	public int insertPost(Post inputPost, List<MultipartFile> images) throws IllegalStateException, IOException {
		int result = mapper.insertPost(inputPost);
		if(result == 0) return 0;
		int postNo = inputPost.getPostNo();
		
		List<PostImg> uploadList = new ArrayList<>();
		int order = 1;
		
		for(int  i = 0; i < images.size(); i++) {
			if(!images.get(i).isEmpty()) {
				String originalName = images.get(i).getOriginalFilename();
				String rename = Utility.fileRename(originalName, order);
				PostImg img = PostImg.builder().imgOriginalName(originalName).imgRename(rename).imgPath(webPath)
						.postNo(postNo).uploadFile(images.get(i)).build();
				uploadList.add(img);
				order++;
			}
		}
		
		if(uploadList.isEmpty()) {
			return postNo;
		}
		
		result = mapper.insertUploadList(uploadList);
		if(result == uploadList.size()) {
			for(PostImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
			}
		} else {
			throw new RuntimeException();
		}
		
		return postNo;
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 상태값 변경하기
	 */
	public int updateStatus(int postNo, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("postNo", postNo);
		map.put("status", status);
		
		return mapper.updateStatus(map);
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 수정
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Override
	public int updatePost(Post inputPost, List<MultipartFile> images) throws IllegalStateException, IOException {
		int result = mapper.updatePost(inputPost);
		
		if(result == 0) {
			return 0;
		}
		
		List<PostImg> uploadList = new ArrayList<>();
		int order = 1;
		for(int i = 0; i < images.size(); i++ ) {
			if(!images.get(i).isEmpty()) {
				String originalName = images.get(i).getOriginalFilename();
				String rename = Utility.fileRename(originalName, order);
				
				PostImg img = PostImg.builder().imgOriginalName(originalName).imgRename(rename)
						.imgPath(webPath).postNo(inputPost.getPostNo()).uploadFile(images.get(i)).build();
				uploadList.add(img);
				
				result = mapper.updatePostImg(img);
				if(result == 0) {
					result = mapper.insertPostImg(img);
				}
				
				order++;
			}
			
			if(result == 0) {
				throw new RuntimeException();
			}
			
			if(uploadList.isEmpty()) {
				return result;
			}
			
			for(PostImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
			}
		}
		
		return result;
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-05
	 * 게시글 삭제
	 */
	@Override
	public int deletePost(Map<String, Integer> map) {
		return mapper.deletePost(map);
	}

}

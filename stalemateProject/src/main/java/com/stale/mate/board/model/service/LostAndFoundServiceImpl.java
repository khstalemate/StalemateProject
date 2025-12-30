package com.stale.mate.board.model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stale.mate.board.model.dto.Pagination;
import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.dto.PostImg;
import com.stale.mate.board.model.mapper.LostAndFoundMapper;
import com.stale.mate.common.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
@PropertySource("classpath:/config.properties")
public class LostAndFoundServiceImpl implements LostAndFoundService {
	
	@Autowired
	private LostAndFoundMapper mapper;
	
	@Value("${board.web-path}")
	private String webPath;
	
	@Value("${board.folder-path}")
	private String folderPath;

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 실종, 목격 게시판의 총 게시글 개수 가져오기
	 */
	@Override
	public int getAllPostCount() {
		return mapper.getAllPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오기
	 */
	@Override
	public int getLostPostCount() {
		return mapper.getLostPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오기
	 */
	@Override
	public int getWitnessPostCount() {
		return mapper.getWitnessPostCount();
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@Override
	public int getTodayPostCount() {
		return mapper.getTodayPostCount();
	}

	/**
	 * 작성자 : 최보윤
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
	 * 작성일자 : 2025-12-24
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
	 * 작성일자 : 2025-12-28
	 * 게시글 상세 정보 가져오기
	 */
	@Override
	public Post getPost(int postNo) {
		
		return mapper.getPost(postNo);
	}

	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * 게시글 작성하기
	 */
	@Override
	public int insertPost(Post inputPost, List<MultipartFile> images) {
		int result = mapper.insertPost(inputPost);
		if(result == 0) return 0;
		int postNo = inputPost.getPostNo();
		List<PostImg> uploadList = new ArrayList<>();

		for(int i = 0; i<images.size(); i++) {
			if(!images.get(i).isEmpty()) {
				String originalName = images.get(i).getOriginalFilename();
				String rename = Utility.fileRename(originalName);
				PostImg img = PostImg.builder().imgOriginalName(originalName).imgRename(rename).imgPath(webPath)
						.postNo(postNo).uploadFile(images.get(i)).build();
				uploadList.add(img);
			}
		}

		// 실제로 등록된 이미지 파일이 존재하지 않는다면 > 바로 postNo 반환
		if(uploadList.isEmpty()) {
			return postNo;
		}
		
		result = mapper.insertUploadList(uploadList);
		if(result == uploadList.size()) {
			for(PostImg img : uploadList) {
				// transferTo(경로) : 메모리 또는 임시 저장 경로에 업로드된 파일을 원하는 경로에 실제로 전송(서버의 어떤 폴더에 저장할지 지정) 
				img.getUploadFile.transferTo(new File(folderPath + img.getImgRename()));
			}
		} else {
			throw new RuntimeException();
		}
		
		return postNo;
	}
}

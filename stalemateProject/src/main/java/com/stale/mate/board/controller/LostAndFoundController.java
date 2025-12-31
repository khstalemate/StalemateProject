package com.stale.mate.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.service.LostAndFoundService;
import com.stale.mate.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("lostandfound")
@Slf4j
public class LostAndFoundController {

	@Autowired
	private LostAndFoundService service;
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회(2025-12-23), 검색 기능 추가(2025-12-)
	 * @return
	 */
	@GetMapping("/")
	public String lostandfound(@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model,
							@RequestParam Map<String, Object> paramMap) {
		Map<String, Object> map = null;
		
		if(paramMap.isEmpty()) {
			map = service.selectPostList(cp);
		} else {
			map = service.searchList(paramMap, cp);
		}
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("postList", map.get("postList"));
		
		return "lostandfound/lostandfound";
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 실종, 목격 게시판의 총 게시글 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getAllPostCount")
	public int getAllPostCount() {
		return service.getAllPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 실종인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getLostPostCount")
	public int getLostPostCount() {
		return service.getLostPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 상태가 목격인 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getWitnessPostCount")
	public int getWitnessPostCount() {
		return service.getWitnessPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 오늘 등록한 게시글의 개수 가져오기
	 */
	@ResponseBody
	@GetMapping("getTodayPostCount")
	public int getTodayPostCount() {
		return service.getTodayPostCount();
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * 게시글 상세 정보 가져오기
	 */
	@GetMapping("detail")
	public String getPost(@RequestParam("postNo") int postNo, @SessionAttribute(value = "loginMember", required = false) Member loginMember,
						Model model, RedirectAttributes ra) {
		Post post = service.getPost(postNo);
		
		String path = null;
		if(post == null) {
			path = "redirect:/lostandfound/";
			ra.addFlashAttribute("message", "해당 번호의 게시글이 존재하지 않습니다.");
		} else {
			path = "lostandfound/lostandfound_detail";
			model.addAttribute("post", post);
		}	
		
		return path;
	}
	
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * '글쓰기' 버튼을 눌렀을 때 게시글 작성 페이지로 이동시키기 
	 */
	@GetMapping("insert")
	public String showInsertPost() {
		return "lostandfound/lostandfound_edit";
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-28
	 * 게시글 작성하기
	 */
	@PostMapping("insert")
	public String insertPost(@ModelAttribute Post inputPost, @SessionAttribute("loginMember") Member loginMember,
						@RequestParam("uploadImg") List<MultipartFile> images, RedirectAttributes ra) throws IllegalStateException, IOException {
		inputPost.setMemberNo(loginMember.getMemberNo());
		int postNo = service.insertPost(inputPost, images);
		
		String path = null;
		String message = null;
		
		if(postNo > 0) {
			message = "게시글이 작성되었습니다.";
			path = "/detail?postNo=" + postNo;
		} else {
			path = "insert";
			message = "게시글 작성에 실패하였습니다.";
		}
		
		ra.addFlashAttribute("message", message);
		return "redirect:" + path;
	} 	
}
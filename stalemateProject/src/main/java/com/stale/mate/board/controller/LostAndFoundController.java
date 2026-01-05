package com.stale.mate.board.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stale.mate.board.model.dto.Post;
import com.stale.mate.board.model.service.AdoptionServiceImpl;
import com.stale.mate.board.model.service.LostAndFoundService;
import com.stale.mate.member.model.dto.Member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("lostandfound")
public class LostAndFoundController {

    private final AdoptionServiceImpl adoptionServiceImpl;

    private final AdoptionController adoptionController;

	@Autowired
	private LostAndFoundService service;

    LostAndFoundController(AdoptionController adoptionController, AdoptionServiceImpl adoptionServiceImpl) {
        this.adoptionController = adoptionController;
        this.adoptionServiceImpl = adoptionServiceImpl;
    }
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2025-12-23
	 * 게시글 목록 조회(2025-12-23), 검색 기능 추가(2025-12-26)
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
	@GetMapping("{postNo:[0-9]+}")
	public String getPost(@PathVariable("postNo") int postNo, @SessionAttribute(value = "loginMember", required = false) Member loginMember,
						Model model, RedirectAttributes ra,
						HttpServletRequest req, HttpServletResponse resp) {
		Post post = service.getPost(postNo);
		
		String path = null;
		if(post == null) {
			ra.addFlashAttribute("message", "해당 번호의 게시글이 존재하지 않습니다.");
			return "redirect:/lostandfound/";
		} else {
			if(loginMember == null || post.getMemberNo() != loginMember.getMemberNo()) {
				Cookie[] cookies = req.getCookies();
				Cookie c = null;
				
				if(cookies != null) {
					for(Cookie temp : cookies) {
						if(temp.getName().equals("viewPostNo")) {
							c = temp;
							break;
						}
					}
				}
				
				int result = 0;
				if(c == null) {
					c = new Cookie("viewPostNo", "[" + postNo + "]");
					result = service.updateViews(postNo);
				} else {
					if(c.getValue().indexOf("[" + postNo + "]") == -1) {
						c.setValue(c.getValue() + "[" + postNo + "]");
						result = service.updateViews(postNo);
					}
				}
				
				if(result > 0) {
					post.setViews(result);
					c.setPath("/");
					LocalDateTime now = LocalDateTime.now();
					
					LocalDateTime nextDayMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
					long secondsUntilNextDay = Duration.between(now, nextDayMidnight).getSeconds();
					c.setMaxAge((int)secondsUntilNextDay);
					
					resp.addCookie(c);
				}
			}
			
			path = "?postNo=" + postNo;
			model.addAttribute("post", post);
			
			if(!post.getImgList().isEmpty()) {
				model.addAttribute("imgList", post.getImgList());
			}
		}	
		
		return "lostandfound/lostandfound_detail";
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
			path = "/lostandfound/" + postNo;
		} else {
			message = "게시글 작성에 실패하였습니다.";
			path = "/lostandfound/insert";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	} 	
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-01
	 * 게시글의 상태값 변경
	 */
	@PostMapping("updateStatus")
	public String updateStatus(@RequestParam("postNo") int postNo, @RequestParam("status") String status,
							@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		
		int result = service.updateStatus(postNo, status);
		
		String message = null;
		if(result > 0) {
			message = "상태가 변경되었습니다.";
		} else {
			message = "상태 변경에 실패하였습니다.";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/lostandfound/" + postNo;
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 수정 화면으로 전환
	 */
	@GetMapping("{postNo:[0-9]+}/update")
	public String updatePost(@PathVariable("postNo") int postNo, @SessionAttribute("loginMember") Member loginMember,
							Model model, RedirectAttributes ra) {
		Post post = service.getPost(postNo);
		String message = null;
		String path = null;
		
		if(post == null) {
			message = "해당 게시글이 존재하지 않습니다.";
			path = "redirect:/lostandfound";
		} else if (post.getMemberNo() != loginMember.getMemberNo()) {
			message = "자신이 작성한 글만 수정할 수 있습니다.";
			path = "redirect:/lostandfound";
		} else {
			path = "lostandfound/lostandfound_edit";
			model.addAttribute("post", post);
		}
		
		ra.addFlashAttribute("message", message);
		
		return path; 
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 수정
	 */
	@PostMapping("{postNo:[0-9]+}/update")
	public String updatePost(@PathVariable("postNo") int postNo, @ModelAttribute Post inputPost,
						@RequestParam("uploadImg") List<MultipartFile> images, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
						@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) throws Exception {
		inputPost.setPostNo(postNo);
		inputPost.setMemberNo(loginMember.getMemberNo());
		int result = service.updatePost(inputPost, images);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "게시글이 수정되었습니다.";
			path = "/lostandfound/" + postNo;
		} else {
			message = "게시글 수정에 실패하였습니다.";
			path = "update";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	/**
	 * 작성자 : 최보윤
	 * 작성일자 : 2026-01-04
	 * 게시글 삭제
	 */
	@PostMapping("{postNo:[0-9]+}/delete")
	public String deletePost(@PathVariable("postNo") int postNo, @RequestParam(value="cp", required = false, defaultValue = "1") int cp,
							@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		Map<String, Integer> map = new HashMap<>();
		map.put("postNo", postNo);
		map.put("memberNo", loginMember.getMemberNo());
		
		int result = service.deletePost(map);
		String path = null;
		String message = null;
		
		if(result > 0) {
			message = "게시글이 삭제되었습니다.";
			path = "/lostandfound/?cp=" + cp;
		} else {
			message = "게시글 삭제에 실패하였습니다.";
			path = "/lostandfound/" + postNo;
		}
		
		ra.addFlashAttribute("message", message);
		return "redirect:"+ path;
	}
}
package kr.or.ddit.posts.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.posts.service.IPostsService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/posts")
@Controller
public class PostsController {
	@Resource(name="postsService")
	private IPostsService postsService;
	
	@RequestMapping("/postsPageList")
	public String postsPageList(BoardVO boardvo, Model model) {
		model.addAttribute("boardvo", boardvo);
		
		return "posts";
	}
	
	@RequestMapping("/postsPageListAjaxHtml")
	public String postsPageListHtml(BoardVO boardvo, PageVO pagevo, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardvo", boardvo);
		map.put("pagevo", pagevo);
		
		Map<String, Object> resultMap = postsService.postsPageList(map);
		
		Logger logger = LoggerFactory.getLogger(PostsController.class);
		logger.debug("postsList.size() : {}", (List<PostsVO>)resultMap.get("postsList"));
		
		model.addAllAttributes(resultMap);
		
		return "posts/postsPageListHtml";
	}
	
	@RequestMapping("/postsPaginationAjaxHtml")
	public String postsPaginationHtml(BoardVO boardvo, PageVO pagevo, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardvo", boardvo);
		map.put("pagevo", pagevo);
		
		Map<String, Object> resultMap = postsService.postsPageList(map);
		
		model.addAllAttributes(resultMap);
		
		return "posts/postsPaginationHtml";
	}
	
}


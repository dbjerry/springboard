package kr.or.ddit.posts.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.attachments.service.IAttachmentsService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.comm.model.CommVO;
import kr.or.ddit.comm.service.ICommService;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.posts.service.IPostsService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.StringUtil;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/posts")
@Controller
public class PostsController {
	@Resource(name="postsService")
	private IPostsService postsService;
	
	@Resource(name="commService")
	private ICommService commService;
	
	@Resource(name="attachmentsService")
	private IAttachmentsService attachService;
	
	
	/**
	* Method : postsPageList
	* 작성자 : jerry
	* 변경이력 :
	* @param boardvo
	* @param model
	* @return
	* Method 설명 : /WEB-INF/view/posts/posts.jsp로 이동
	*/
	@RequestMapping("/postsPageList")
	public String postsPageList(BoardVO boardvo, Model model) {
		model.addAttribute("boardvo", boardvo);
		
		return "posts";
	}
	
	/**
	* Method : postsPageListHtml
	* 작성자 : jerry
	* 변경이력 :
	* @param boardvo
	* @param pagevo
	* @param model
	* @return
	* Method 설명 : 게시글리스트 ajax적용
	*/
	@RequestMapping("/postsPageListAjaxHtml")
	public String postsPageListHtml(BoardVO boardvo, PageVO pagevo, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardvo", boardvo);
		map.put("pagevo", pagevo);
		
		Map<String, Object> resultMap = postsService.postsPageList(map);
		
		Logger logger = LoggerFactory.getLogger(PostsController.class);
		logger.debug("postsList.size() : {}", (List<PostsVO>)resultMap.get("postsList"));
		
		model.addAllAttributes(resultMap);
		model.addAttribute("page", pagevo.getPage());
		
		return "posts/postsPageListHtml";
	}
	
	/**
	* Method : postsPaginationHtml
	* 작성자 : jerry
	* 변경이력 :
	* @param boardvo
	* @param pagevo
	* @param model
	* @return
	* Method 설명 : 페이징처리 ajax적용
	*/
	@RequestMapping("/postsPaginationAjaxHtml")
	public String postsPaginationHtml(BoardVO boardvo, PageVO pagevo, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardvo", boardvo);
		map.put("pagevo", pagevo);
		
		Map<String, Object> resultMap = postsService.postsPageList(map);
		
		model.addAllAttributes(resultMap);
		
		return "posts/postsPaginationHtml";
	}
	
	/**
	* Method : postsDetail
	* 작성자 : jerry
	* 변경이력 :
	* @param posts_no
	* @param postsvo
	* @param model
	* @return
	* Method 설명 : 게시글 상세보기
	*/
	@RequestMapping("/postsDetail")
	public String postsDetail(PostsVO postsvo, Model model) {
		postsvo = postsService.getPostsDetail(postsvo.getPosts_no());
		
		model.addAttribute("postsvo", postsvo);
		
		/* 댓글 조회 */
		List<CommVO> commList = commService.selectComm(postsvo.getPosts_no());
		
		model.addAttribute("commList", commList);
		
		/* 첨부파일 조회 */
		List<AttachmentsVO> attaList = attachService.selectAtta(postsvo.getPosts_no());
		
		model.addAttribute("attaList", attaList);
		
		return "postsDetail";
	}
	
	/**
	* Method : addPostsForm
	* 작성자 : jerry
	* 변경이력 :
	* @param board_id
	* @param model
	* @return
	* Method 설명 : 게시글 등록 페이지로 이동
	*/
	@RequestMapping("/addPostsForm")
	public String addPostsForm(@RequestParam("board_id")String board_id, Model model) {
		model.addAttribute(board_id);
		return "addpostsForm";
	}
	
	/**
	* Method : insertParentPosts
	* 작성자 : jerry
	* 변경이력 :
	* @param request
	* @param boardvo
	* @param uservo
	* @param postsvo
	* @param attachvo
	* @param model
	* @return
	* @throws IOException
	* @throws ServletException
	* Method 설명 : 게시글 등록(부모)
	*/
	@RequestMapping(value="/insertParentPosts", method=RequestMethod.POST)
	public String insertParentPosts(HttpServletRequest request,
									BoardVO boardvo, UserVO uservo, PostsVO postsvo, AttachmentsVO attachvo, 
									Model model) throws IOException, ServletException {
		uservo = (UserVO)request.getSession().getAttribute("S_USER");
		
		String posts_cnt = request.getParameter("smarteditor");
		postsvo.setUserid(uservo.getUserId());
		postsvo.setPosts_cnt(posts_cnt);
		
		int insertCnt = postsService.insertParentPosts(postsvo);
		
		if(insertCnt > 0) {
			for(int i = 1; i <= 5; i++) {
				Part attachments = request.getPart("attachments"+i);
				
				if(attachments != null) {
					
					String contentDisposition = attachments.getHeader("Content-disposition");
					
					String fileName = StringUtil.getFileName(contentDisposition);
					
					String path = request.getServletContext().getRealPath("/attachfile");
					
					String attachfile = null;
					if(!(fileName.equals(""))){
						attachments.write(path + File.separator + fileName);
						//attachments.delete();
					
						attachfile = "/attachfile/" + fileName;
						
					}else{
						continue;
					}
					
					String maxPosts = postsService.maxPosts(boardvo.getBoard_id());
					
					attachvo.setAtta_file(attachfile);
		
					attachvo.setPosts_no(maxPosts);
					
					attachService.insertAtta(attachvo);
				}
			}
			return "redirect:/posts/postsPageList?page=1&pageSize=10&board_id="+boardvo.getBoard_id();
			
		}else {
			return "redirect:/posts/postsPageList?page=1&pageSize=10&board_id="+boardvo.getBoard_id();
			
		}
	}
	
	/**
	* Method : postsUpdateForm
	* 작성자 : jerry
	* 변경이력 :
	* @param posts_no
	* @param postsvo
	* @param model
	* @return
	* Method 설명 : update form으로 이동
	*/
	@RequestMapping("/postsUpdateForm")
	public String postsUpdateForm(@RequestParam("posts_no")String posts_no, PostsVO postsvo, Model model) {
		postsvo = postsService.getPostsDetail(posts_no);
		
		List<AttachmentsVO> update_attaList = attachService.selectAtta(posts_no);
		
		model.addAttribute("r_postsvo", postsvo);
		model.addAttribute("update_attaList", update_attaList);
		return "postsUpdateForm";
	}
	
	/**
	* Method : postsUpdate
	* 작성자 : jerry
	* 변경이력 :
	* @param posts_cnt
	* @param request
	* @param attachvo
	* @param postsvo
	* @param boardvo
	* @param model
	* @return
	* @throws IOException
	* @throws ServletException
	* Method 설명 : 게시글 수정
	*/
	@RequestMapping("/postsUpdate")
	public String postsUpdate(@RequestParam("smarteditor")String posts_cnt, HttpServletRequest request, 
								AttachmentsVO attachvo, PostsVO postsvo, 
								BoardVO boardvo, Model model) throws IOException, ServletException {
		Map<String , Object> map = new HashMap<String, Object>();
		List<AttachmentsVO> attaList = new ArrayList<AttachmentsVO>();
		
		postsvo.setPosts_cnt(posts_cnt);
		
		for(int i = 0; i < 5; i++) {
			Part attachments = request.getPart("files" + i);
			
			if(attachments != null) {
				String contentDisposition = attachments.getHeader("content-disposition");
				
				String fileName = StringUtil.getFileName(contentDisposition);
				
				String path = request.getServletContext().getRealPath("/attachfile");
				
				String attachfile = null;
				
				if(!(fileName.equals(""))) {
					attachments.write(path + File.separator + fileName);
					attachments.delete();
					
					attachfile = "/attachfile/" + fileName;
					
				} else {
					continue;
				}
				
				attachvo.setAtta_file(attachfile);
				attachvo.setPosts_no(posts_cnt);
				
				attaList.add(attachvo);
			}
		}
		map.put("postsvo", postsvo);
		map.put("attavo", attaList);
		
		Logger logger = LoggerFactory.getLogger(PostsController.class);
		logger.debug("map : {}", map);
		
		int updateCnt = postsService.updatePosts(map);
		
		if(updateCnt > 0) {
			return "redirect:/posts/postsPageList?page=1&pageSize=10&board_id="+boardvo.getBoard_id();
			
		} else {
			model.addAttribute(postsvo);
			return "postsUpdateForm";
		}
	}
	
	/**
	* Method : postsDelete
	* 작성자 : jerry
	* 변경이력 :
	* @param posts_no
	* @param board_id
	* @param model
	* @return
	* Method 설명 : 게시글 삭제 구분 수정
	*/
	@RequestMapping("/postsDelete")
	public String postsDelete(@RequestParam("posts_no")String posts_no, 
								BoardVO boardvo, Model model) {
		postsService.updateUse(posts_no);
		
		return "redirect:/posts/postsPageList?page=1&pageSize=10&board_id="+boardvo.getBoard_id();
	}
	
	@RequestMapping("/addReplyPostsForm")
	public String addReplyPostsForm(@RequestParam("posts_no")String posts_no, 
									@RequestParam("board_id")String board_id,
									Model model) {
		model.addAttribute("posts_no", posts_no);
		model.addAttribute("board_id", board_id);
		
		return "addReplyPostsForm";
	}
	
	@RequestMapping("/addReplyPosts")
	public String addReplyPosts(HttpServletRequest request, BoardVO boardvo, PostsVO postsvo) {
		UserVO uservo = new UserVO();
		uservo = (UserVO) request.getSession().getAttribute("S_USER");
		
		String userId = uservo.getUserId();
		String posts_cnt = request.getParameter("smarteditor");
		
		postsvo.setPosts_cnt(posts_cnt);
		postsvo.setUserid(userId);
		postsvo.setPosts_hno(postsvo.getPosts_no());
		
		postsService.insertChildPosts(postsvo);
		
		return "redirect:/posts/postsPageList?page=1&pageSize=10&board_id="+boardvo.getBoard_id();
	}
	
}


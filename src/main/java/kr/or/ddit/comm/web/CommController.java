package kr.or.ddit.comm.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.comm.model.CommVO;
import kr.or.ddit.comm.service.ICommService;
import kr.or.ddit.posts.model.PostsVO;

@RequestMapping("/comm")
@Controller
public class CommController {
	@Resource(name="commService")
	private ICommService commService;
	
	/**
	* Method : addComm
	* 작성자 : jerry
	* 변경이력 :
	* @param userid
	* @param postsvo
	* @param boardvo
	* @param commvo
	* @param model
	* @return
	* Method 설명 : 댓글 생성(저장)
	*/
	@RequestMapping("/addComm")
	public String addComm(@RequestParam("userid")String userid, PostsVO postsvo,
							BoardVO boardvo, CommVO commvo, Model model) {
		commvo.setUserid(userid);
		commService.insertComm(commvo);
		
		return "redirect:/posts/postsDetail?board_id="+boardvo.getBoard_id() + "&posts_no=" + postsvo.getPosts_no();
	}
	
	/**
	* Method : deleteComm
	* 작성자 : jerry
	* 변경이력 :
	* @param commvo
	* @param postsvo
	* @param model
	* @return
	* Method 설명 : 댓글 삭제 구분 수정
	*/
	@RequestMapping("/deleteComm")
	public String deleteComm(CommVO commvo, BoardVO boardvo, PostsVO postsvo, Model model) {
		commService.updateComm(commvo.getComm_id());
		
		return "redirect:/posts/postsDetail?board_id="+boardvo.getBoard_id() + "&posts_no=" + postsvo.getPosts_no();
	}
	
}

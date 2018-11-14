package kr.or.ddit.board.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	* Method : addBoard
	* 작성자 : jerry
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 관리 페이지로 이동
	*/
	@RequestMapping("/addBoard")
	public String addBoard() {
		return "board/addBoard";
	}
	
	/**
	* Method : boardInsert
	* 작성자 : jerry
	* 변경이력 :
	* @param boardvo
	* @param request
	* @return
	* Method 설명 : 게시판 생성
	*/
	@RequestMapping("/boardInsert")
	public String boardInsert(BoardVO boardvo, HttpServletRequest request) {
		UserVO uservo = (UserVO) request.getSession().getAttribute("S_USER");
		
		String userId = uservo.getUserId();
		
		boardvo.setUserId(userId);
		
		int insertCnt = boardService.insertBoard(boardvo);
		
		if(insertCnt > 0)
			return success(request);
			
		else
			return "board/addBoard";
	}
	
	/**
	* Method : boardUpdate
	* 작성자 : jerry
	* 변경이력 :
	* @param boardvo
	* @param request
	* @return
	* Method 설명 : 게시판 수정
	*/
	@RequestMapping("/boardUpdate")
	public String boardUpdate(BoardVO boardvo, HttpServletRequest request) {
		int updateCnt = boardService.updateBoard(boardvo);
		
		if(updateCnt > 0) 
			return success(request);
		else
			return "board/addBoard";
	}
	
	/**
	* Method : success
	* 작성자 : jerry
	* 변경이력 :
	* @param request
	* @return
	* Method 설명 : insert, update 성공시 addBoard.jsp로 이동 및 left.jsp 새로고침
	*/
	private String success(HttpServletRequest request) {
		List<BoardVO> boardList = boardService.selectAllBoard();
		request.getServletContext().setAttribute("boardList", boardList);
		
		return "board/addBoard";
	}
	
}


package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {

	/**
	 * Method : selectAllBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 조회
	 */
	List<BoardVO> selectAllBoard();

}


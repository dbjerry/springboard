package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {

	/**
	 * Method : selectAllBoard
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 조회
	 */
	List<BoardVO> selectAllBoard();

	/**
	 * Method : insertBoard
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 추가, Mybatis Query insert 성공시 1 (여러개여도 1) 반환
	 */
	int insertBoard(BoardVO boardvo);

	/**
	 * Method : updateBoard
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	int updateBoard(BoardVO boardvo);

}


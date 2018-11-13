package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

@Service
public class BoardService implements IBoardService{
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	/* 생성자 */
	public BoardService(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	/* 기본생성자 */
	public BoardService() {}

	/* getter & setter */
	public IBoardDao getBoardDao() {
		return boardDao;
	}
	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}

	/**
	 * Method : selectAllBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 조회
	 */
	@Override
	public List<BoardVO> selectAllBoard() {
		return boardDao.selectAllBoard();
	}
	
}

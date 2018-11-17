package kr.or.ddit.board;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.test.ServiceDaoTestConfig;

public class BoardDaoTest extends ServiceDaoTestConfig{

	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	* Method : insertBoardTest
	* 작성자 : jerry
	* 변경이력 :
	* Method 설명 : 게시판 생성 테스트
	*/
	@Test
	public void insertBoardTest() {
		/***Given***/

		/***When***/
		BoardVO boardvo = new BoardVO();
		boardvo.setUserId("minions");
		boardvo.setBoard_name("단위테스트");
		boardvo.setBoard_yn("Y");
		
		int insertCnt = boardService.insertBoard(boardvo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
	}

}

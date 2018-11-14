package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

@Repository
public class BoardDao implements IBoardDao {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	/**
	 * Method : selectUser
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 회원 검색(인자 : String)
	 */
	@Override
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> boardList = template.selectList("board.selectAllBoard");
		
		return boardList;
	}

	/**
	 * Method : insertBoard
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 추가, Mybatis Query insert 성공시 1 (여러개여도 1) 반환
	 */
	@Override
	public int insertBoard(BoardVO boardvo) {
		return template.insert("board.insertBoard", boardvo);
	}

	/**
	 * Method : updateBoard
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	@Override
	public int updateBoard(BoardVO boardvo) {
		return template.update("board.updateBoard", boardvo);
	}

}


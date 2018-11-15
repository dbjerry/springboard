package kr.or.ddit.comm.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.comm.model.CommVO;

@Repository
public class CommDao implements ICommDao{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	/**
	 * Method : selectComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	@Override
	public List<CommVO> selectComm(String posts_no) {
		return template.selectList("comm.selectComm", posts_no);
	}

	/**
	 * Method : insertComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commvo
	 * @return
	 * Method 설명 : 댓글 생성(저장)
	 */
	@Override
	public int insertComm(CommVO commvo) {
		return template.insert("comm.insertComm", commvo);
	}

	/**
	 * Method : updateComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commid
	 * @return
	 * Method 설명 : 댓글 삭제 구분
	 */
	@Override
	public int updateComm(String comm_id) {
		return template.update("comm.updateComm", comm_id);
	}

	
}

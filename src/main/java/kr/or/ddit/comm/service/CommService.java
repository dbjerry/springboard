package kr.or.ddit.comm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.comm.dao.ICommDao;
import kr.or.ddit.comm.model.CommVO;

@Service
public class CommService implements ICommService{
	@Resource(name="commDao")
	private ICommDao commDao;

	/* 기본생성자 */
	public CommService() {}
	
	/* getter & setter */
	public ICommDao getCommDao() {
		return commDao;
	}
	public void setCommDao(ICommDao commDao) {
		this.commDao = commDao;
	}

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
		return commDao.selectComm(posts_no);
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
		return commDao.insertComm(commvo);
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
		return commDao.updateComm(comm_id);
	}
	
	

}

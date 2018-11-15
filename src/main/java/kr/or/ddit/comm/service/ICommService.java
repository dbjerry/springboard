package kr.or.ddit.comm.service;

import java.util.List;

import kr.or.ddit.comm.model.CommVO;

public interface ICommService {

	/**
	 * Method : selectComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	List<CommVO> selectComm(String posts_no);

	/**
	 * Method : insertComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commvo
	 * @return
	 * Method 설명 : 댓글 생성(저장)
	 */
	int insertComm(CommVO commvo);

	/**
	 * Method : updateComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commid
	 * @return
	 * Method 설명 : 댓글 삭제 구분
	 */
	int updateComm(String comm_id);

}

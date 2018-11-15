package kr.or.ddit.attachments.service;

import java.util.List;

import kr.or.ddit.attachments.model.AttachmentsVO;

public interface IAttachmentsService {

	/**
	 * Method : selectAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 해당 게시글의 첨부파일 조회
	 */
	List<AttachmentsVO> selectAtta(String posts_no);

	/**
	 * Method : insertAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	public int insertAtta(AttachmentsVO attavo);

}

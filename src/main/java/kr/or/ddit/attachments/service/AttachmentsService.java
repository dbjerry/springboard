package kr.or.ddit.attachments.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.attachments.dao.IAttachmentsDao;
import kr.or.ddit.attachments.model.AttachmentsVO;

@Service
public class AttachmentsService implements IAttachmentsService{
	@Resource(name="attachmentsDao")
	private IAttachmentsDao attachDao;

	/* 기본생성자 */
	public AttachmentsService() {}

	/* getter & setter */
	public IAttachmentsDao getAttachmentsDao() {
		return attachDao;
	}
	public void setAttachmentsDao(IAttachmentsDao attachDao) {
		this.attachDao = attachDao;
	}
	
	/**
	 * Method : selectAtta
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 해당 게시글의 첨부파일 조회
	 */
	@Override
	public List<AttachmentsVO> selectAtta(String posts_no) {
		return attachDao.selectAtta(posts_no);
	}

	/**
	 * Method : insertAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAtta(AttachmentsVO attavo) {
		return attachDao.insertAtta(attavo);
	}
	
	
	
}

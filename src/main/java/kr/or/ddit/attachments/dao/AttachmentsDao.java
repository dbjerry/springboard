package kr.or.ddit.attachments.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachments.model.AttachmentsVO;

@Repository
public class AttachmentsDao implements IAttachmentsDao {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	/**
	 * Method : selectAtta
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 해당 게시글의 첨부파일 조회
	 */
	@Override
	public List<AttachmentsVO> selectAtta(String posts_no) {
		return template.selectList("atta.selectAtta", posts_no);
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
		return template.insert("atta.insertAtta", attavo);
	}

	/**
	 * Method : deleteAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param attano
	 * @return
	 * Method 설명 : 첨부파일 삭제
	 */
	@Override
	public int deleteAtta(String attaFileName) {
		return template.delete("atta.deleteAtta", attaFileName);
	}

}

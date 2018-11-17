package kr.or.ddit.posts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.attachments.dao.AttachmentsDao;
import kr.or.ddit.attachments.dao.IAttachmentsDao;
import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.attachments.service.IAttachmentsService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.posts.dao.IPostsDao;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.model.PageVO;

@Service
public class PostsService implements IPostsService{
	@Resource(name="postsDao")
	private IPostsDao postsDao;
	
	@Resource(name="attachmentsService")
	private IAttachmentsService attachService;
	
	@Resource(name="attachmentsDao")
	private IAttachmentsDao attachDao;
	
	/* 기본생성자 */
	public PostsService() {}
	
	/* getter & setter */
	public IPostsDao getPostsDao() {
		return postsDao;
	}
	public void setPostsDao(IPostsDao postsDao) {
		this.postsDao = postsDao;
	}

	/**
	 * Method : postsPageList
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	@Override
	public Map<String, Object> postsPageList(Map<String, Object> map) {
		List<PostsVO> postsList = postsDao.postsPageList(map);
		
		int postsCnt = postsDao.postsCnt(map);
		
		PageVO page = (PageVO) map.get("pagevo");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postsList", postsList);
		resultMap.put("postsCnt", (int)Math.ceil((double)postsCnt / page.getPageSize()));
		resultMap.put("page", page.getPage());
		
		return resultMap;
	}
	
	/**
	 * Method : postsCnt
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 게시글 총 갯수
	 */
	@Override
	public int postsCnt(Map<String, Object> cntMap) {
		return postsDao.postsCnt(cntMap);
	}

	/**
	* Method : getPostsDetail
	* 작성자 : jerry
	* 변경이력 :
	* @param posts_no
	* @return
	* Method 설명 : 게시글 상세보기
	*/
	@Override
	public PostsVO getPostsDetail(String posts_no) {
		return postsDao.getPostsDetail(posts_no);
	}

	/**
	 * Method : insertParentPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 원글(부모글) 작성
	 */
	@Override
	public int insertParentPosts(PostsVO postsvo) {
		return postsDao.insertParentPosts(postsvo);
	}

	/**
	 * Method : maxPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 최신번호
	 */
	@Override
	public String maxPosts(String board_id) {
		return postsDao.maxPosts(board_id);
	}

	/**
	 * Method : updatePosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePosts(Map<String, Object> map) {
		PostsVO postsvo = (PostsVO) map.get("postsvo");
		String[] attafile = (String[]) map.get("attafile");
		
		int postsCnt = postsDao.updatePosts(postsvo);
		
		for(String str : attafile) {
			attachService.deleteAtta(str);
		}
		
		List<AttachmentsVO> attaList = (List<AttachmentsVO>) map.get("attaList");
		
		int attaCnt = 0;
		for(AttachmentsVO attavo : attaList) {
			attaCnt += attachDao.insertAtta(attavo);
		}
		
		return postsCnt + attaCnt;
	}

	/**
	 * Method : updateUse
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 삭제 구분 수정
	 */
	@Override
	public int updateUse(String posts_no) {
		return postsDao.updateUse(posts_no);
	}

	/**
	 * Method : insertChildPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 답글(자식글) 작성
	 */
	@Override
	public int insertChildPosts(PostsVO postsvo) {
		return postsDao.insertChildPosts(postsvo);
	}

}

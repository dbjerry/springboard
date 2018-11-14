package kr.or.ddit.posts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.posts.dao.IPostsDao;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.model.PageVO;

@Service
public class PostsService implements IPostsService{
	@Resource(name="postsDao")
	private IPostsDao postsDao;
	
	/* 기본생성자 */
	public PostsService() {}
	
	/* getter & setter */
	public IPostsDao getPostsDao() {
		return postsDao;
	}
	public void setPostsDao(IPostsDao postsDao) {
		this.postsDao = postsDao;
	}

	@Override
	public Map<String, Object> postsPageList(Map<String, Object> map) {
		List<PostsVO> postsList = postsDao.postsPageList(map);
		
		BoardVO boardvo = (BoardVO) map.get("boardvo");
		
		int postsCnt = postsDao.postsCnt(boardvo.getBoard_id());
		
		PageVO page = (PageVO) map.get("pagevo");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postsList", postsList);
		resultMap.put("postsCnt", (int)Math.ceil((double)postsCnt / page.getPageSize()));
		
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
	public int postsCnt(String boardId) {
		return postsDao.postsCnt(boardId);
	}

}

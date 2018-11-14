package kr.or.ddit.posts.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.posts.model.PostsVO;

public interface IPostsDao {

	/**
	 * Method : postsPageList
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	public List<PostsVO> postsPageList(Map<String, Object> map);

	/**
	 * Method : postsCnt
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 게시글 총 갯수
	 */
	public int postsCnt(String boardId);
	
}

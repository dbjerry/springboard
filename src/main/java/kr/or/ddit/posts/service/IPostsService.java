package kr.or.ddit.posts.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.posts.model.PostsVO;

public interface IPostsService {

	/**
	 * Method : postsPageList
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	Map<String, Object> postsPageList(Map<String, Object> map);
	
	/**
	 * Method : postsCnt
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 게시글 총 갯수
	 */
	int postsCnt(Map<String, Object> cntMap);

	/**
	* Method : getPostsDetail
	* 작성자 : jerry
	* 변경이력 :
	* @param posts_no
	* @return
	* Method 설명 : 게시글 상세보기
	*/
	PostsVO getPostsDetail(String posts_no);

	/**
	 * Method : insertParentPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 원글(부모글) 작성
	 */
	int insertParentPosts(PostsVO postsvo);

	/**
	 * Method : maxPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 최신번호
	 */
	String maxPosts(String board_id);

	/**
	 * Method : updatePosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	int updatePosts(Map<String, Object> map);

	/**
	 * Method : updateUse
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 삭제 구분 수정
	 */
	int updateUse(String posts_no);

	/**
	 * Method : insertChildPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 답글(자식글) 작성
	 */
	int insertChildPosts(PostsVO postsvo);

}

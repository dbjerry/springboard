package kr.or.ddit.posts.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.posts.model.PostsVO;

@Repository
public class PostsDao implements IPostsDao {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	/**
	 * Method : postsPageList
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	@Override
	public List<PostsVO> postsPageList(Map<String, Object> map) {
		List<PostsVO> pageList = template.selectList("posts.postsPageList", map);
		
		return pageList;
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
		return template.selectOne("posts.postsCnt", boardId);
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
		return template.selectOne("posts.getPostsDetail", posts_no);
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
		return template.insert("posts.insertParentPosts", postsvo);
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
		return template.selectOne("posts.maxPosts", board_id);
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
	public int updatePosts(PostsVO postsvo) {
		return template.update("posts.updatePosts", postsvo);
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
		return template.update("posts.updateUse", posts_no);
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
		return template.insert("posts.insertChildPosts", postsvo);
	}
	
	
}


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
	
	
}


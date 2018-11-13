package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="userDao")
public class UserDao implements IUserDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
}

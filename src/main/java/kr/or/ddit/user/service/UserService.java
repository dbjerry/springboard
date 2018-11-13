package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Service("userService")
public class UserService implements IUserService{

	@Resource(name="userDao")
	IUserDao userDao;

	/**
	 * Method : selectUser
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 회원 검색(인자 : String)
	 */
	@Override
	public UserVO selectUser(String userId) {
		return userDao.selectUser(userId);
	}
	
}


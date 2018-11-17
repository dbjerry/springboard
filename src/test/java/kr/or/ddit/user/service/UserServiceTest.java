package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.test.ServiceDaoTestConfig;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends ServiceDaoTestConfig {
	Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Resource(name="userService")
	IUserService userService;

	@Test
	public void userControllerTest() {
		/***Given***/
		String userId = "minions";
		
		/***When***/
		UserVO user = userService.selectUser(userId);
		
		/***Then***/
		assertEquals("미니언즈", user.getName());

	}

}

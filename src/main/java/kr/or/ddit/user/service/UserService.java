package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;

@Service
public class UserService implements IUserService{

	@Resource(name="userDao")
	IUserDao dao;
	
}


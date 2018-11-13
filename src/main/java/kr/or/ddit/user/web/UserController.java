package kr.or.ddit.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVO;

@RequestMapping("/user")
@Controller
public class UserController {

	final String db_userId = "brown";
	final String db_pass = "brownpass";
	
	@RequestMapping("/loginView")
	public String login() {
		return "login/login";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String process(UserVO uservo, HttpServletRequest request) {
		
		String userId = uservo.getUserId();
		String pass = uservo.getPass();
		
		if(check(userId, pass)) {
			request.getSession().setAttribute("S_USER", uservo);
			
			return "main";
			
		} else {
			return "login/login";
		}
		
	}

	
	/**
	* Method : check
	* 작성자 : 김지태
	* 변경이력 :
	* @param userid
	* @return
	* Method 설명 : 아이디 비번 체크
	*/
	private boolean check(String userid, String pass) {
		if(db_userId.equals(userid) && db_pass.equals(pass)) {
			return true;
			
		} else {
			return false;
		}
	}
	
}

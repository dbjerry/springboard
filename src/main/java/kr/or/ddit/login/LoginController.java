package kr.or.ddit.login;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.sha.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/loginCon")
@Controller
public class LoginController {
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping("/loginView")
	public String login() {
		return "login/login";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String process(UserVO uservo, HttpServletRequest request, HttpServletResponse response) {
		String userId = uservo.getUserId();
		String pass = uservo.getPass();
		String rememberMe = request.getParameter("remember-me");
		
		if(rememberMe == null){
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
				
				// cookie 이름이 remember, userId 일 경우 maxage를
				// -1 설정하여 쿠키를 유효하지 않도록 설정
				if(cookie.getName().equals("remember") || cookie.getName().equals("userId")){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					
				}
			}
		} else {
			// response 객체에 쿠키를 저장
			Cookie cookie = new Cookie("remember", "Y");
			Cookie userIdCookie = new Cookie("userId", userId);
			// response 객체에 쿠키를 저장
    		
			response.addCookie(userIdCookie);
			response.addCookie(cookie);
		}
		
		UserVO getUser = userService.selectUser(userId);
		List<BoardVO> boardList = boardService.selectAllBoard();
		System.out.println("LoginController line 69) boardList : " + boardList);
		String encryptPass = KISA_SHA256.encrypt(pass);
			
		if(getUser != null && getUser.authPass(encryptPass)){
			
			request.getSession().setAttribute("S_USER", getUser);
			
			//회원등급별 보여지는 메뉴가 다르면 session으로 게시판목록이 보여져야 하지만
			//등급이 없다면 그냥 request내장객체로 set해도 무관
			//request.getSession().getServletContext()
			request.getServletContext().setAttribute("boardList", boardList);
			
			return "main";
		
		} else {
			return "login/login";
		}
		
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "login/login";
	}
	
}

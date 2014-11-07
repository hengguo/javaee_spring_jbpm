package demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.service.LoginService;
/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
public class LoginAction{
	@Resource
	private LoginService loginService;
	
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request){
		return new ModelAndView("login");
	}
	@RequestMapping("userLoginAction")
	public ModelAndView userLoginAction(HttpServletRequest request, String staffName, String staffPsw){
		ModelAndView mav = new ModelAndView();
		if(loginService.isLogin(staffName, staffPsw)){
			request.getSession().setAttribute("staffName", staffName);	//保存当前用户
			mav.setViewName("index");
		}else{
			mav.setViewName("login");
		}
		return mav;
		
	}
	

	

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}

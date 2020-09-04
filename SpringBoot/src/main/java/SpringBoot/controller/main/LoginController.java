package SpringBoot.controller.main;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SpringBoot.command.LoginCommand;
import SpringBoot.command.MemberCommand;
import SpringBoot.service.login.LoginService;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login() {
		return "redirect:/";
	}
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String submit(@Validated LoginCommand loginCommand, BindingResult result, 
						HttpSession session, HttpServletResponse response, Model model)throws Exception {
		if(result.hasErrors()) {
			return "thymeleaf/main";
		}
		String location = loginService.loginOk(loginCommand, session, response, model);
		return location;
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

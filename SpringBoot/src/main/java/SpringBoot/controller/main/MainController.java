package SpringBoot.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import SpringBoot.command.LoginCommand;

@Controller
public class MainController {
	@ModelAttribute
	LoginCommand setLoginCommand() {
        return new LoginCommand();
    }
	
	@RequestMapping("/")
	public String main() {
		return "thymeleaf/main";
	}
}

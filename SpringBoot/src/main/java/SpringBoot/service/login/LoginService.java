package SpringBoot.service.login;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.command.AuthInfo;
import SpringBoot.command.LoginCommand;
import SpringBoot.domain.MemberDTO;
import SpringBoot.mapper.MemberMapper;

@Service
@Component
public class LoginService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	AuthInfo authInfo;
	
	public String loginOk(LoginCommand loginCommand, HttpSession session, HttpServletResponse response, Model model)throws Exception {
		String location = "";
		MemberDTO dto = new MemberDTO();
		dto.setUserId(loginCommand.getUserId());
		List<MemberDTO> lists = memberMapper.selectByMember(dto);
		if(lists.size()==0) {
			model.addAttribute("valid_userId", "아이디가 존재하지않습니다.");
			location = "thymeleaf/main";
		}else {
			dto = lists.get(0);
			if(passwordEncoder.matches(loginCommand.getUserPw(), dto.getUserPw())) {
				authInfo = new AuthInfo(dto.getUserId(), dto.getUserPw(), dto.getUserEmail(), dto.getUserName());
				session.setAttribute("authInfo", authInfo);
				location = "redirect:/";
			}else {
				model.addAttribute("valid_userPw", "비밀번호가 일치하지않습니다. ");
				location = "thymeleaf/main";
			}
		}
		return location;
	}
}

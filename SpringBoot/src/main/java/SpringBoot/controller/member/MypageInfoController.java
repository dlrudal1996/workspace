package SpringBoot.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import SpringBoot.command.AuthInfo;
import SpringBoot.command.ChangePwCommand;
import SpringBoot.command.MemberCommand;
import SpringBoot.domain.MemberDTO;
import SpringBoot.service.member.MemberDeleteService;
import SpringBoot.service.member.MemberInfoService;
import SpringBoot.service.member.MemberModifyService;

@Controller
@RequestMapping("mypage")
public class MypageInfoController {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	PasswordEncoder PasswordEncoder;
	@Autowired
	MemberModifyService memberModifyService;
	@Autowired
	MemberDeleteService MemberDeleteService;
	
	@ModelAttribute
	ChangePwCommand setChangePwCommand() {
		return new ChangePwCommand();
	}
	
	@RequestMapping(value="myInfo", method = RequestMethod.GET)
	public String myInfo(HttpSession session, Model model)throws Exception {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		memberInfoService.myInfo(userId, model);
		return "thymeleaf/member/myInfo";
	}
	@RequestMapping("memberPw")
	public String memberPw() {
		return "thymeleaf/member/myPwCon";
	}
	@RequestMapping(value="mypagePwOk", method = RequestMethod.POST)
	public String mypagePwOk(HttpSession session, Model model, 
							 @RequestParam(value="userPw")String userPw)throws Exception {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		MemberDTO member = memberInfoService.memberInfo(userId, model);
		if(PasswordEncoder.matches(userPw, member.getUserPw())) {
			return "thymeleaf/member/myInfoModify";
		}else {
			model.addAttribute("valid_Pw", "비밀번호가 일치하지않습니다. ");
			return "thymeleaf/member/myPwCon";
		}
	}
	@RequestMapping(value = "myInfoModifyPro", method = RequestMethod.POST)
	public String myInfoModifyPro(MemberCommand memberCommand, Model model)throws Exception {
		Integer i = memberModifyService.memberModify(memberCommand, model);
		if(i > 0) {
			return "redirect:/mypage/myInfo";
		}else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/myInfoModify";
		}
		
	}
	@RequestMapping(value="memberPwForm", method = RequestMethod.GET)
	public String memberPwForm() {
		return "thymeleaf/member/pwModify_1";
	}
	@RequestMapping(value="pwModifyPro", method = RequestMethod.POST)
	public String memberPwFormPro(HttpSession session, Model model, @Validated ChangePwCommand changePwCommand, BindingResult result)throws Exception {
		if(result.hasErrors()) {
			return "thymeleaf/member/pwModify_1";
		}
		return memberModifyService.myPwChange(session, model, changePwCommand);
		 
	}
	@RequestMapping(value = "myInfoDel", method = RequestMethod.GET)
	public String myInfoDel() {
		return "thymeleaf/member/delete_pw";
	}
	@RequestMapping(value = "myInfoDelPro", method = RequestMethod.POST)
	public String myInfoDelPro(HttpSession session,@RequestParam(value="userPw")String userPw, Model model)throws Exception {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		MemberDTO member = memberInfoService.memberInfo(userId, model);
		if(PasswordEncoder.matches(userPw, member.getUserPw())) {
			MemberDeleteService.memberDelete(userId);
			return "redirect:/logout";
		}else {
			model.addAttribute("valid_pw", "비밀번호가 일치하지않습니다.");
			return "thymeleaf/member/delete_pw";
		}
	}
	
}


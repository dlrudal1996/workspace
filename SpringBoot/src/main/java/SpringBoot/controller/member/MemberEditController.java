package SpringBoot.controller.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import SpringBoot.command.MemberCommand;
import SpringBoot.service.member.MemberDeleteService;
import SpringBoot.service.member.MemberInfoService;
import SpringBoot.service.member.MemberModifyService;

@Controller
@RequestMapping("edit")
public class MemberEditController {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberModifyService memberModifyService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	//memberList.jsp : <a href ="/edit/memberInfo/${dto.userId }"> ${dto.userId } </a>
	//회원리스트 - 회원정보
	@RequestMapping(value="memberInfo/{id}", method = RequestMethod.GET)
	public String memberInfo(@PathVariable(value="id")String userId, Model model)throws Exception {
		memberInfoService.memberInfo(userId, model);
		return "thymeleaf/member/memberInfo";
	}
	//memberInfo.html : location.href="../memberModify?userId="+userId;  쿼리스트링으로 userId값을 받아옴
	//회원 정보 - 회원 수정(modify창에 값만 가져옴)
	@RequestMapping(value="memberModify", method = RequestMethod.GET)
	public String memberModify(@RequestParam(value="userId")String userId, Model model)throws Exception {
		memberInfoService.memberInfo(userId, model);
		return "thymeleaf/member/memberModify";
	}
	@RequestMapping(value="memberModifyPro", method = RequestMethod.POST)
	public String memberModifyPro(MemberCommand memberCommand, Model model)throws Exception {
		Integer i = memberModifyService.memberModify(memberCommand, model);
		if(i > 0) {
			return "redirect:/edit/memberInfo/"+memberCommand.getUserId();
		}else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/memberModify";
		}
	}
	// location.href="../memberInfoDel/"+userId; 주소로 userId값을 받아옴
	@RequestMapping(value = "memberInfoDel/{id}", method = RequestMethod.GET)
	public String memberInfoDel(@PathVariable(value="id")String userId)throws Exception {
		memberDeleteService.memberDelete(userId);
		return "redirect:/mem/memberList";
	}
}



package SpringBoot.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import SpringBoot.service.member.MemberListService;

@Controller
@RequestMapping("mem")
public class MemberInfoController {
	@Autowired
	MemberListService memberListService;
	
	@RequestMapping(value="memberList")
	public String memberList(@RequestParam(value="page", defaultValue = "1")Integer page, Model model) throws Exception {
		memberListService.memberList(page, model);
		return "/member/memberList";
	}
}

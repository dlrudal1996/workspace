package SpringBoot.service.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.command.MemberCommand;
import SpringBoot.domain.MemberDTO;
import SpringBoot.mapper.MemberMapper;

@Component
@Service
public class MemberInfoService {
	@Autowired
	MemberMapper memberMapper;
	
	public MemberDTO memberInfo(String userId, Model model)throws Exception {
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		//joinOk가 null인 값도 출력될 수 있도록 joinOk값도 member에 저장하고, memberMapper.xml에서 joinOk==null도 is not null이라고 선언
		member.setJoinOk(userId);
		member = memberMapper.selectByMember(member).get(0);
		
		model.addAttribute("memberCommand", member);
		return member;
	}

	public MemberDTO myInfo(String userId, Model model)throws Exception {
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		member.setJoinOk(userId);
		member = memberMapper.selectByMember(member).get(0);
		model.addAttribute("memberCommand", member);
		return member;
		
	}

}

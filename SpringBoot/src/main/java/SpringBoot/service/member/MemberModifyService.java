package SpringBoot.service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.command.AuthInfo;
import SpringBoot.command.ChangePwCommand;
import SpringBoot.command.MemberCommand;
import SpringBoot.domain.MemberDTO;
import SpringBoot.domain.PwChangeDTO;
import SpringBoot.mapper.MemberMapper;

@Service
@Component
public class MemberModifyService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Integer memberModify(MemberCommand memberCommand, Model model)throws Exception {
		MemberDTO member = new MemberDTO();					//web에서 받아오는 user의 수정 정보를  member에 담음
		member.setUserAddr(memberCommand.getUserAddr());
		member.setUserEmail(memberCommand.getUserEmail());
		member.setUserId(memberCommand.getUserId());
		member.setUserPh1(memberCommand.getUserPh1());
		member.setUserPh2(memberCommand.getUserPh2());
		
		MemberDTO dto = new MemberDTO();					//db에 저장되어있는 user의 정보를 가져옴
		dto = memberMapper.selectByMember(member).get(0);	//member 테이블에 있는 user의 정보를 가져오는데 특정 userId에 해당하는 정보만 가져오므로 get(0)
		if(passwordEncoder.matches(memberCommand.getUserPw(),dto.getUserPw())) {
			return memberMapper.memberUpdate(member);		//입력한 pw가 일치하면mapper를 이용하여 member의 정보를 업데이트하고, controller에 반환받함
		}
		return 0;											//입력한 pw가 불일치하면 controller에 o을 반환한다. 
		
	}

	public String myPwChange(HttpSession session, Model model, ChangePwCommand changePwCommand)throws Exception {
		if(!changePwCommand.isEqualNewPwToNewPwOk()) {
			model.addAttribute("valid_newPw", "새로운 비밀번호가 일치하지않습니다. ");
			return "thymeleaf/member/pwModify_1";
		}
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		MemberDTO member = new MemberDTO();
		member.setUserId(userId);
		member = memberMapper.selectByMember(member).get(0);
		if(passwordEncoder.matches(changePwCommand.getUserPw(), member.getUserPw())) {
			PwChangeDTO dto = new PwChangeDTO(userId, passwordEncoder.encode(changePwCommand.getNewPw()));
			memberMapper.pwChange(dto);
			return "redirect:/mypage/myInfo";
		}else {
			model.addAttribute("valid_pw", "현재 비밀번호가 일치하지않습니다.");
			return "thymeleaf/member/pwModify_1";
		}
	}

}

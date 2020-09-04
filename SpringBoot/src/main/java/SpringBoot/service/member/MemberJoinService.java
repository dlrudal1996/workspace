package SpringBoot.service.member;

import java.sql.Timestamp;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import SpringBoot.command.MemberCommand;
import SpringBoot.controller.MailAction;
import SpringBoot.controller.SmsSend;
import SpringBoot.domain.MemberDTO;
import SpringBoot.mapper.MemberMapper;

@Service
@Component
@Transactional
public class MemberJoinService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MailAction mailAction;
	
	public Integer insertMember(MemberCommand memberCommand, Model model)throws Exception {
		if(!memberCommand.UserPwEqualToUserPwCon()) {
			model.addAttribute("valid_userPwCon", "새암호가 일치하지않습니다.");
			return null;
		}
		Integer result = null;
		MemberDTO memberDTO = new MemberDTO();
		Timestamp userBirth = 
				Timestamp.valueOf(memberCommand.getUserBirth());
		memberDTO.setUserId(memberCommand.getUserId());
		memberDTO.setUserPw(passwordEncoder.encode(memberCommand.getUserPw()));
		memberDTO.setUserName(memberCommand.getUserName());
		memberDTO.setUserBirth(userBirth);
		memberDTO.setUserGender(memberCommand.getUserGender());
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		memberDTO.setUserEmail(memberCommand.getUserEmail());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		result = memberMapper.insertMember(memberDTO);

		if(result != null) {
			SmsSend ss = new SmsSend();
			try {
				mailAction.sendMail(memberDTO.getUserEmail(), 
						memberDTO.getUserId());
				ss.smsSend(memberDTO.getUserPh1(), 
						   memberDTO.getUserName()+"님 회원가입을 축하합니다.");
			} catch (MessagingException e) {
				ss.smsSend(memberDTO.getUserPh1(), 
						   memberDTO.getUserName()+"님 회원가입을 축하합니다. "+ "그러나 1588-0000 으로 문의 바랍니다.");
				e.printStackTrace();
			}
		}else {
			model.addAttribute("duplicate_userId", "사용중인 아이디 입니다. ");
		}
		return result;
	}

	public Integer joinOkUpdate(String joinOk, String reciver, String userId) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(userId);
		memberDTO.setUserEmail(reciver);
		memberDTO.setJoinOk(joinOk);
		
		return memberMapper.joinOkUpdate(memberDTO);
	}
	
}

package SpringBoot.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.controller.PageAction;
import SpringBoot.domain.MemberDTO;
import SpringBoot.domain.StartEndPageDTO;
import SpringBoot.mapper.MemberMapper;

@Service
@Component
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	
	public void memberList(Integer page, Model model)throws Exception{
		int limit = 5;		//한 페이지에 보이는 회원 수
		int limitPage = 10;	//페이징에서 보일 수 있는 페이지 수
		
		Long startRow = ((long)page -1 ) * limit +1;	//페이징에서 보이는 페이지에 따른 첫번째 숫자 ex)1,6,11,16.. limitPage가 10이므로
		Long endRow = startRow + limit -1;				//페이징에서 보이는 페이지에 따른 마지막 숫자 ex)5,10,15,20...
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(startRow, endRow);
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setStartEndPageDTO(startEndPageDTO);
		
		List<MemberDTO> members = memberMapper.selectByMember(memberDTO);
		int count = memberMapper.memberCount();
		
		model.addAttribute("lists", members);
		model.addAttribute("count", count);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "memberList?");
	
	}
	
}

package SpringBoot.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import SpringBoot.mapper.MemberMapper;

@Service
@Component
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	
	public void memberDelete(String userId)throws Exception {
		memberMapper.memberDelete(userId);
		
	}

}

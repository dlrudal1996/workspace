package SpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import SpringBoot.domain.MemberDTO;
import SpringBoot.domain.PwChangeDTO;

@Component
@Repository(value="SpringBoot.mapper")
public interface MemberMapper {

	public Integer insertMember(MemberDTO dto)throws Exception;
	public Integer joinOkUpdate(MemberDTO memberDTO);
	public List<MemberDTO> selectByMember(MemberDTO memberDTO)throws Exception;
    public Integer memberCount();
    public Integer memberUpdate(MemberDTO member)throws Exception;
    public Integer memberDelete(String userId)throws Exception;
    public Integer pwChange(PwChangeDTO dto)throws Exception;
}

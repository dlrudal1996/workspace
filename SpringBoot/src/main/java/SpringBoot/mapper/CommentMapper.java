package SpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import SpringBoot.domain.CommentDTO;

@Component
@Repository(value="SpringBoot.mapper.CommentMapper")
public interface CommentMapper {
	public List<CommentDTO> selectByComment(CommentDTO commentDTO)throws Exception;
	public Integer insertComment(CommentDTO commentDTO)throws Exception;
	
}

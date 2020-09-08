package SpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import SpringBoot.domain.CommentDTO;
import SpringBoot.domain.StartEndPageDTO;

@Component
@Repository(value="SpringBoot.mapper.CommentMapper")
public interface CommentMapper {
	public List<CommentDTO> selectByComment(StartEndPageDTO startEndPageDTO)throws Exception;
	public Integer insertComment(CommentDTO commentDTO)throws Exception;
	public Integer commentCount()throws Exception;
	public List<CommentDTO> commentDetail(CommentDTO dto)throws Exception;
}

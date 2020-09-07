package SpringBoot.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.domain.CommentDTO;
import SpringBoot.mapper.CommentMapper;

@Service
@Component
public class CommentListService {
	@Autowired
	CommentMapper commentMapper;
	
	public void commentList(Model model)throws Exception {
		CommentDTO commentDTO = new CommentDTO();
		List<CommentDTO> lists = commentMapper.selectByComment(commentDTO); 
		model.addAttribute("lists", lists);
	}

}

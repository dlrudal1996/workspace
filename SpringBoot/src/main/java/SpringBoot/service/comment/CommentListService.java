package SpringBoot.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.command.CommentCommand;
import SpringBoot.controller.PageAction;
import SpringBoot.domain.CommentDTO;
import SpringBoot.domain.LibraryBoardDTO;
import SpringBoot.domain.StartEndPageDTO;
import SpringBoot.mapper.CommentMapper;

@Service
@Component
public class CommentListService {
	@Autowired
	CommentMapper commentMapper;
	
	public void commentList(Integer page, Model model)throws Exception {
		int limit = 10;
		int limitPage = 10;
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(startRow, endRow, null, null);
		List<CommentDTO> lists = commentMapper.selectByComment(startEndPageDTO); 
		
		int count = commentMapper.commentCount();
		model.addAttribute("count", count);
		model.addAttribute("lists", lists);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "commentList?");
		
		
	}

	public String commentDetail(Long commentNo, Model model)throws Exception {
		CommentDTO dto = new CommentDTO();
		dto.setCommentNo(commentNo);
//		dto = commentMapper.commentDetail(dto);
		model.addAttribute("dto",dto);
		return "thymeleaf/comment/comment_Collection";
	}

}

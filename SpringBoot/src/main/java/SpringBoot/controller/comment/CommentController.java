package SpringBoot.controller.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SpringBoot.command.CommentCommand;
import SpringBoot.command.MemberCommand;
import SpringBoot.service.comment.CommentListService;
import SpringBoot.service.comment.CommentWriteService;

@Controller
@RequestMapping("comment")
public class CommentController {
	@Autowired
	CommentListService commentListService;
	@Autowired
	CommentWriteService commentWriteService;
	
	@RequestMapping(value="commentList", method = RequestMethod.GET)
	public String commentList(Model model)throws Exception {	//페이지 필요
		commentListService.commentList(model);
		return "comment/commentList";
	}
	@RequestMapping(value="commentForm", method = RequestMethod.GET)
	public String commentForm() {
		return "thymeleaf/comment/commentForm";
	}
	@RequestMapping(value="commentWrite", method = RequestMethod.POST)
	public String commentWrite(CommentCommand commentCommand, HttpSession session)throws Exception {
		commentWriteService.commentWrite(commentCommand, session);
		return "redirect:/comment/commentList";
	}
}

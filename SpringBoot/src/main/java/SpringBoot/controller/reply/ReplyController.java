package SpringBoot.controller.reply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("reply")
public class ReplyController {
	
	@RequestMapping(value="replyList", method = RequestMethod.GET)
	public String replyList() {
//		return "thymeleaf/comment/";
		return "member/commentList";
	}
}

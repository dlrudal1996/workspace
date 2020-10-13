package SpringBoot.service.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import SpringBoot.command.AuthInfo;
import SpringBoot.command.CommentCommand;
import SpringBoot.domain.CommentDTO;
import SpringBoot.domain.ReplyDTO;
import SpringBoot.mapper.CommentMapper;

@Service
@Component
@Transactional
public class CommentWriteService {
	@Autowired
	CommentMapper commentMapper;
	
	public void commentWrite(CommentCommand commentCommand, HttpSession session)throws Exception {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setCommentSubject(commentCommand.getCommentSubject());
		commentDTO.setCommentContent(commentCommand.getCommentContent());
		commentDTO.setCuserId(userId);
//		아래와 같이 작성 가능
//		CommentDTO commentDTO = new CommentDTO(null,userId,null,
//				commentCommand.getCommentSubject(),
//				commentCommand.getCommentContent());
		commentMapper.insertComment(commentDTO);
	}

	public void replyInsert(Long commentNo, String cuserId, String replyContent, HttpSession session)throws Exception {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setCommentNo(commentNo);
		replyDTO.setCuserId(cuserId);
		replyDTO.setReplyContent(replyContent);
		replyDTO.setRuserId(userId);
		commentMapper.insertReply(replyDTO);
	}
}

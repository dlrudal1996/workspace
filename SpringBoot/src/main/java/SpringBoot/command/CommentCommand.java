package SpringBoot.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CommentCommand {
	@NotBlank(message = "제목을 입력하세요")
	String commentSubject;
	@NotEmpty(message = "내용을 입력하세요")
	String commentContent;
}

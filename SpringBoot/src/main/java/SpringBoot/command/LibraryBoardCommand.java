package SpringBoot.command;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LibraryBoardCommand {
	String boardNum;
	@NotEmpty(message = "이름을 입력해주세요!")
	String boardName;
	@NotEmpty(message = "비밀번호를 입력해주세요!")
	String boardPass;
	@NotEmpty(message = "제목을 입력해주세요!")
	String boardSubject;
	String boardContent;
	MultipartFile [] report;
}

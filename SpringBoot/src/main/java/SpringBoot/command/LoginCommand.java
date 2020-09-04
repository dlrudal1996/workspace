package SpringBoot.command;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginCommand {
	@Size(min = 1, max = 20, message = "아이디를 입력하세요")
	String userId;
	@Size(min = 1, max = 20, message = "비밀번호를 입력하세요")
	String userPw;
}

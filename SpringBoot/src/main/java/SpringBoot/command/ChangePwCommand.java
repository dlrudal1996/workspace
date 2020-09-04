package SpringBoot.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class ChangePwCommand {
	String userId;
	@NotBlank(message = "현재 비밀번호를 입력하세요")
	String userPw;
	@Size(min=4, max=12, message = "새로운 비밀번호를 입력하세요")
	String newPw;
	@Size(min=4, max=12, message = "새로운 비밀번호를 한번 더 입력하세요")
	String NewPwOk;
	
	public boolean isEqualNewPwToNewPwOk() {
		if(newPw.equals(NewPwOk)) {
			return true;
		}
		return false;
	}
}

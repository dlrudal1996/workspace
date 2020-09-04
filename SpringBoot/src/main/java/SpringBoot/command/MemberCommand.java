package SpringBoot.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	//Web에서 입력받는 값
	@Size(min=4, max=12, message="아이디를 입력하세요")
	String userId;
	@NotEmpty(message="암호를 입력하세요")	//"", null 미허용
	String userPw;
	@NotBlank							//""," ", null미허용
	String userPwCon;
	@NotBlank(message="이름을 입력하세요")
	@Size(min=2, max=4)
	String userName;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
	LocalDateTime userBirth;
	@NotNull(message = "성별을 선택하세요")	//null 미허용
	String userGender;
	@NotEmpty(message = "이메일을 입력하세요")
	@Email
	String userEmail;
	@NotEmpty(message = "주소를 입력하세요")
	String userAddr;
	@NotEmpty(message = "대표전화번호를 입력하세요")
	String userPh1;
	String userPh2;
	
	public boolean UserPwEqualToUserPwCon() {
		if(userPw.equals(userPwCon)) {
			return true;
		}
		return false;
	}
}

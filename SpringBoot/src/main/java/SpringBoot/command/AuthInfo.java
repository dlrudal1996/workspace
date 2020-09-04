package SpringBoot.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInfo {
	private String userId;
	private String userPw;
	private String userEmail;
	private String userName;
}

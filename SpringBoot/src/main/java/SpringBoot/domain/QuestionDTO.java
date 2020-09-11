package SpringBoot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
	Integer questionNum;		//질문번호
	String questionTitle;		//질문
	String userId;				//작성자
}

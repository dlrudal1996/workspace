package SpringBoot.domain;

import java.util.List;

import lombok.Data;

@Data
public class QuestionOptionDTO {
	QuestionDTO question;		//questionDTO에 저장된 질문(question)
	List<OptionsDTO> options;	//대답목록
	public boolean isChoice() {	
		return options != null && !options.isEmpty();	//options가 null값이 아니고 " "이 아닐 때 
	}
}

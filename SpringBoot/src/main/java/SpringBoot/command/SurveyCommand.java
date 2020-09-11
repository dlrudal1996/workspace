package SpringBoot.command;

import lombok.Data;

@Data
public class SurveyCommand {
	String question;	//질문
	String options;		//답변문항
}

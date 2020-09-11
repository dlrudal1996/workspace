package SpringBoot.service.survey;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import SpringBoot.command.AuthInfo;
import SpringBoot.command.SurveyCommand;
import SpringBoot.domain.OptionsDTO;
import SpringBoot.domain.QuestionDTO;
import SpringBoot.mapper.SurveyMapper;

@Component
@Service
public class SurveyInsertService {
	@Autowired
	SurveyMapper surveyMapper;

	public void surveyInsert(SurveyCommand surveyCommand, HttpSession session) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getUserId();	//userId를 받아와 문항의 작성자를 알 수 있도록한다.
		QuestionDTO qdto = new QuestionDTO();										
		qdto.setQuestionTitle(surveyCommand.getQuestion());			//surveyCommand에 받아온 질문값(name = question)을 저장하고 QuestionDTO에 담는다
		qdto.setUserId(userId);										//session으로 받아온 userId를 QuestionDTO에 담는다
		surveyMapper.questionInsert(qdto);							//qdto에 담은 값을 mapper를 이용하여 사용등록한다.
		
		// questionNum 저장되고나서 questionNum을 받아오기위해 쿼리문 실핼	
		int questionNum = surveyMapper.questionNum(userId);			
		String options[] = null;									//답변문항들의 초기값 null값으로 지정
		if (surveyCommand.getOptions() != null) {					//command로 받아온 options의 값들이 null이 아니면 
			options = surveyCommand.getOptions().split("`");		//options의 값들을 command에서 받아온 값들 사이 `로 구분한다.
		}
		
		int optionsNum = 1;							//답변문항의 초기값을 1로 둔다. 
		// 문항은 1개 이상
		if (options != null) {						//답변문항의 수가 null이 아니면
			//options의 개수만큼 for문을 사용하여 반복한다.
			for (String optionName : options) {		//command에 담아둔 답변문항들(options) -> optionName	
				OptionsDTO odto = new OptionsDTO();	
				odto.setQuestionNum(questionNum);	//위에서 받아온 질문의 번호를 odto에 담는다.
				odto.setOptionName(optionName);		
				odto.setOptionNum(optionsNum++);	//optionName에 저장되면 optionNum을 1씩 증가시킨다.
				odto.setUserId(userId);				//session에서 받아온 userId를 odto에 담는다. 
				surveyMapper.optionInsert(odto);	//mapper를 이용해 답변문항을 db와 연결한다.
			}
		}
	}
}

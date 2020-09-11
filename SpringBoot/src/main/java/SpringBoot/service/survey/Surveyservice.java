package SpringBoot.service.survey;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.domain.QuestionOptionDTO;
import SpringBoot.mapper.SurveyMapper;

@Service
@Component
public class Surveyservice {
	@Autowired
	SurveyMapper surveyMapper;
	
	public void execute(Model model)throws Exception {
		List<QuestionOptionDTO> lists = new ArrayList<QuestionOptionDTO>(); //질문과 대답목록을 불러오는 DTO
		int questionCount = surveyMapper.questionCount();	//출력되는 question에 붙는 번호
		for(int i = 1; i <= questionCount ; i++) {			
			QuestionOptionDTO dto = surveyMapper.surveySelectAll(i);	//mapper를 이용해 dto에 모든 질문을 담는다.
			lists.add(dto);												//dto를 list에 저장
		}
		model.addAttribute("list", lists);
	}

}

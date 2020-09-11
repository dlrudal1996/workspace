package SpringBoot.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import SpringBoot.domain.OptionsDTO;
import SpringBoot.domain.QuestionDTO;
import SpringBoot.domain.QuestionOptionDTO;

@Component
@Repository
public interface SurveyMapper {

	public void questionInsert(QuestionDTO qdto) throws Exception;
	public Integer questionNum(String userId)throws Exception;
	public void optionInsert(OptionsDTO odto) throws Exception;
	public int questionCount() throws Exception;
	public QuestionOptionDTO surveySelectAll(int i) throws Exception;

}

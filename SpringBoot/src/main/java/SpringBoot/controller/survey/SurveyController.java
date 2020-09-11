package SpringBoot.controller.survey;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import SpringBoot.command.SurveyCommand;
import SpringBoot.service.survey.SurveyInsertService;
import SpringBoot.service.survey.Surveyservice;

@Controller
@RequestMapping("survey")
public class SurveyController {
	@Autowired
	SurveyInsertService surveyInsertService;
	@Autowired
	Surveyservice surveyService;
	
	@RequestMapping("surveyForm")
	public String surveyForm() {
		return "thymeleaf/survey/surveyForm";
	}
	@RequestMapping("surveyInsert")
	public String surveyInsert(SurveyCommand surveyCommand, HttpSession session)throws Exception {
		surveyInsertService.surveyInsert(surveyCommand, session);
		return "redirect:/survey/surveyForm";
	}
	@RequestMapping("survey")
	public String survey(Model model)throws Exception {
		surveyService.execute(model);
		return "thymeleaf/survey/survey";
	}
}

package SpringBoot.controller.libBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import SpringBoot.command.LibraryBoardCommand;
import SpringBoot.service.libBoard.LibBoardDetailService;
import SpringBoot.service.libBoard.LibBoardListService;
import SpringBoot.service.libBoard.LibBoardService;

@Controller
@RequestMapping("lib")
public class LibraryController {
	@Autowired
	LibBoardService libBoardService;
	@Autowired
	LibBoardListService libBoardListService;
	@Autowired
	LibBoardDetailService libBoardDetailService;
//	@Autowired
//	FileDownLoad fileDownLoad;
	
	@ModelAttribute
	LibraryBoardCommand setLibraryBoardCommand() {
		return new LibraryBoardCommand();
	}
	
	@RequestMapping(value="libBoardList")
	public String libBoardList(@RequestParam(value="page", defaultValue = "1")Integer page, Model model)throws Exception {
		libBoardListService.libBoardList(page, model);
		return "lib_board/libBoardList";
	}
	
	@RequestMapping(value="libBoardForm", method = RequestMethod.GET)
	public String libBoardForm() {
		return "thymeleaf/lib_board/libBoardForm";
	}
	
	@RequestMapping(value="libBoardWrite" , method = RequestMethod.POST)
	public String libraryWrite(@Validated LibraryBoardCommand libraryBoardCommand, BindingResult result, HttpServletRequest request)throws Exception {
		if(result.hasErrors()) {
			System.out.println("에러발생");
			return "thymeleaf/lib_board/libBoardForm";
		}
		String location = libBoardService.writePro(libraryBoardCommand, request);
		return location;
	}
	//a href="libBoardDetail/${dto.boardNum }
	@RequestMapping("libBoardDetail/{boardNum}")
	public String libBoardDetail(@PathVariable(value="boardNum")String boardNum, Model model, HttpSession session)throws Exception {
		libBoardDetailService.libBoardDetail(boardNum, model, session);
		return "thymeleaf/lib_board/libBoardDetail";
	}
	
	@RequestMapping("fileDown")
	public String filDownLoad(@RequestParam(value="file")String fileName, HttpServletResponse response, HttpServletRequest request)throws Exception {
//		fileDownLoad.fileDownLoad(fileName,response,request);
		return "thymeleaf/lib_Board/lib_board_view";
	}

}

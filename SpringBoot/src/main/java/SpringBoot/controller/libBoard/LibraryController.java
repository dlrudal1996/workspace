package SpringBoot.controller.libBoard;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SpringBoot.command.LibraryBoardCommand;
import SpringBoot.service.libBoard.LibBoardService;

@Controller
@RequestMapping("lib")
public class LibraryController {
	@Autowired
	LibBoardService libBoardService;
	
	@ModelAttribute
	LibraryBoardCommand setLibraryBoardCommand() {
		return new LibraryBoardCommand();
	}
	
	@RequestMapping(value="libBoardList")
	public String libBoardList() {
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

}

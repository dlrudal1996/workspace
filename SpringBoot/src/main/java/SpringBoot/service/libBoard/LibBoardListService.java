package SpringBoot.service.libBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.controller.PageAction;
import SpringBoot.domain.LibraryBoardDTO;
import SpringBoot.domain.StartEndPageDTO;
import SpringBoot.mapper.LibBoardMapper;

@Service
@Component
public class LibBoardListService {
	@Autowired
	LibBoardMapper libBoardMapper;
	
	public void libBoardList(Integer page, Model model)throws Exception {
		int limit = 10;
		int limitPage = 10;
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
//		LibraryBoardDTO dto = new LibraryBoardDTO();
//		dto.setStartEndPageDTO(new StartEndPageDTO(startRow, endRow));
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(startRow, endRow, null, null);
		List<LibraryBoardDTO> lists = libBoardMapper.getLibBoardList(startEndPageDTO);
		
		int count = libBoardMapper.getLibraryCount();
		model.addAttribute("count", count);
		model.addAttribute("lists", lists);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "lib?");
		
	}

}

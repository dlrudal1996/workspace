package SpringBoot.service.libBoard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import SpringBoot.domain.FileName;
import SpringBoot.domain.LibraryBoardDTO;
import SpringBoot.domain.StartEndPageDTO;
import SpringBoot.mapper.LibBoardMapper;

@Component
@Service
public class LibBoardDetailService {
	@Autowired
	LibBoardMapper libBoardMapper;
	
	public void libBoardDetail(String boardNum, Model model, HttpSession session)throws Exception{
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(1L,1L,null,boardNum);
		LibraryBoardDTO dto = libBoardMapper.getLibBoardList(startEndPageDTO).get(0);
		
		// `를 구분자가 있 는 내용을 split()을 이용하여 배열로 변환
		String [] oriFile = dto.getOriginalFileName().split("`");
		String [] strFile = dto.getStoreFileName().split("`");
		String [] fileSize = dto.getFileSize().split("`");
		
		List<FileName> fileList = new ArrayList<FileName>();
		int i = 0;
		for(String file : oriFile ) {
			FileName fileName = new FileName(file, strFile[i],fileSize[i] );
			fileList.add(fileName);
			i++;
		}

		model.addAttribute("fileList", fileList);
		model.addAttribute("dto", dto);
	}

}

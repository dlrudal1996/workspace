package SpringBoot.service.libBoard;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import SpringBoot.command.AuthInfo;
import SpringBoot.command.LibraryBoardCommand;
import SpringBoot.domain.LibraryBoardDTO;
import SpringBoot.mapper.LibBoardMapper;

@Component
@Service
public class LibBoardService {
	@Autowired
	LibBoardMapper libBoardMapper;
	
	public String writePro(LibraryBoardCommand libraryBoardCommand, HttpServletRequest request)throws Exception{
		String location = "";
		LibraryBoardDTO dto = new LibraryBoardDTO();
		dto.setBoardName(libraryBoardCommand.getBoardName());
		dto.setBoardPass(libraryBoardCommand.getBoardPass());
		dto.setBoardSubject(libraryBoardCommand.getBoardSubject());
		dto.setBoardContent(libraryBoardCommand.getBoardContent());
		
		HttpSession session = request.getSession();
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		dto.setUserId(authInfo.getUserId());
		dto.setIpAddr(request.getRemoteAddr());
		
		// 파일 정보를 입력하기 위한 변수
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal ="";
		String PATH = "/static/lib_board/upload";
		String filePath = request.getServletContext().getRealPath(PATH);
		
		for(MultipartFile mf : libraryBoardCommand.getReport()) {
			String original = mf.getOriginalFilename(); // 전송된 파일명
			String originalFileExtension = // 전송된 파일명으로 부터 확장자만 자라옴
					original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "") + originalFileExtension; // 임의의 파일명 + 확장자
			String fileSize = Long.toString(mf.getSize());
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			// 파일을저장하기 위해서 파일 객체 생성
			File file = new File(filePath + "/" + store);
			try {
				mf.transferTo(file);
			}catch(Exception e) {
				location = "thymeleaf/lib_Board/libBoardForm";
				e.printStackTrace();
			}
		}
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		dto.setFileSize(fileSizeTotal);
		libBoardMapper.libraryInsert(dto);
		location = "redirect:/lib/libBoardList";
		return location;
	}
}

package SpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import SpringBoot.domain.LibraryBoardDTO;
import SpringBoot.domain.StartEndPageDTO;

@Component
@Repository(value="SpringBoot.mapper.LibBoardMapper")
public interface LibBoardMapper {
	public void libraryInsert(LibraryBoardDTO dto) throws Exception;
	public List<LibraryBoardDTO> getLibBoardList(StartEndPageDTO startEndPageDTO)throws Exception;
	public Integer getLibraryCount() throws Exception;
	public Integer libBoardDetail(StartEndPageDTO startEndPageDTO) throws Exception;
}

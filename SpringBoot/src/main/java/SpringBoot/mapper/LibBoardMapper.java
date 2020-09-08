package SpringBoot.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import SpringBoot.domain.LibraryBoardDTO;

@Component
@Repository
public interface LibBoardMapper {
	public void libraryInsert(LibraryBoardDTO dto) throws Exception;
}

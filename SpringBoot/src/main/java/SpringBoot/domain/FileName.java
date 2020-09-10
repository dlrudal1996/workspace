package SpringBoot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class FileName {
	String originalfileName;
	String storeFileName;
	String fileSize;
}

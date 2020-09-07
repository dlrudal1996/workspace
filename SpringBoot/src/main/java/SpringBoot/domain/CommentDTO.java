package SpringBoot.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	private Long commentNo;
	private String cuserId;
	private Timestamp regDate;
	private String commentSubject;
	private String commentContent;
	
//	private StartEndPageDTO startEndPageDTO;
}

package siteCafeManagement.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 
	사용자 : 아이디, 비밀번호
	게시글 : 제목, 사용자 정보, 내용, 작성시간
		ㄴ 게시글리스트.txt  
	- 사용자 (4) : 나영
	    - 게시글 등록
	    - 게시글 조회
	    - 게시글 수정
	    - 게시글 삭제
*/
@Data
@AllArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 5804120004791065508L;
	int id, pw;
}

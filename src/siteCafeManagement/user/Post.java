package siteCafeManagement.user;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
	게시글 : 제목, 사용자 정보, 내용, 작성시간
	ㄴ 게시글리스트.txt  
*/
@Data
@AllArgsConstructor
public class Post implements Serializable{

	private static final long serialVersionUID = -5085627289207180741L;
	User user;
	String title, content;
	Date date;
	@Override
	public String toString() {

		return  "제목 : " + title + "\n" +
				"작성자 : " + user.id + "\n" +
				"작성일 : " + date + "\n" +
				"내용 : " + content;
	}
	
	
}

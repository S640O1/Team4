package siteCafeManagement.user;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
	게시글 : 게시판, 카테고리 제목, 사용자 정보, 내용, 작성시간
	ㄴ 게시글리스트.txt  
	게시글의 경우 중복허용
*/
@Data
@AllArgsConstructor
public class Post implements Serializable{

	private static final long serialVersionUID = -5085627289207180741L;
	User user;
	String title, content, category, board;
	Date date;
	@Override
	public String toString() {

		return  "카테고리 : " + category + "\n" +
				"게시판 : " + board + "\n" +
				"제목 : " + title + "\n" +
				"작성자 : " + user.id + "\n" +
				"작성일 : " + date + "\n" +
				"내용 : " + content;
	}
	
	//일자 제목 작성자
	public String simpleToString() {
		//제목 길이지정
		//유저랑 날짜 형식지정
		return "["+ category + board + "]" + title + user.id + date;
		
	}
	

	
	
}

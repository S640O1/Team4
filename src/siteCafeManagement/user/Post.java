package siteCafeManagement.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
	게시글 : 게시판, 카테고리 제목, 사용자 정보, 내용, 작성시간
	ㄴ 게시글리스트.txt  
	게시글의 경우 중복허용
*/
@Data
public class Post implements Serializable{

	private static final long serialVersionUID = -5085627289207180741L;
	User user;
	String title, content, category, board;
//	Date date;
	LocalDate date;
	
	
	
	@Override
	public String toString() {

		return  "카테고리 : " + category + "\n" +
				"게시판 : " + board + "\n" +
				"제목 : " + title + "\n" +
				"작성자 : " + user.nickName + "\n" +
				"작성일 : " + date + "\n" +
				"내용 : " + content;
	}
	
	//일자 제목 작성자
	public String simpleToString() {
		//제목 길이지정
		//유저랑 날짜 형식지정
		String dateFormat = setDateFormat(date);
		
		return "["+ category + board + "]" + title + user.nickName + dateFormat;
		
	}

	public Post(User user, String title, String content, String category, String board, LocalDate date) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
		this.category = category;
		this.board = board;
		this.date = date;
	}
	
	/**
	 * 현재 시간을 시간 출력 포맷을 지정해서 반환하는 메소드
	 */
	public String setDateFormat(LocalDate date) {
		// 출력 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // 출력 포맷 적용
        String dateFormat = date.format(formatter);
		return dateFormat;
	}
	
	
	

	
	
}

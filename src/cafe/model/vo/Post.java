package cafe.model.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
	
	//게시글 번호, 게시판 번호
	private int p_num, p_b_num;
	//제목, 작성자, 내용
	private String p_title, p_u_id, p_content;
	//작성시간
	private Date p_date;
	//카테고리, 게시판 이름
	String p_b_title, p_c_title;


	public Post(int p_b_num, String p_title, String p_u_id, String p_content, Date p_date) {
		super();
		this.p_b_num = p_b_num;
		this.p_title = p_title;
		this.p_u_id = p_u_id;
		this.p_content = p_content;
		this.p_date = p_date;
	}
	
	//수정 시 사용되는 생성자
	public Post(int p_num, int p_b_num, String p_title, String p_content) {
		super();
		this.p_num = p_num;
		this.p_b_num = p_b_num;
		this.p_title = p_title;
		this.p_content = p_content;
	}
	

	@Override
	public String toString() {
		
		String dateFormat = setDateFormat(p_date);

		return  "[카테고리] " + p_c_title + "\n" +
				"[게 시 판] " + p_b_title + "\n" +
				"[제    목] " + p_title + "\n" +
				"[작 성 자] " + p_u_id + "\n" +
				"[작 성 일] " + dateFormat + "\n" +
				"[내    용]\n" + p_content;
	}
	
	//일자 제목 작성자
	public String simpleToString() {
		String pNumFormat = String.format("%4d", p_num);
		String boardTitleFormat =  String.format("%6s",boardTitleEllipsis(p_b_title)); 
		String titleFormat = String.format("%-20s",titleEllipsis(p_title));
		String dateFormat = setDateFormat(p_date);
		String userIdFormat = String.format("%8s", p_u_id);

		return pNumFormat + "번 :: ["+ boardTitleFormat + "] " + titleFormat + " : " + userIdFormat + " : " + dateFormat;	
	}
	
	/** 현재 시간을 시간 출력 포맷을 지정해서 반환하는 메소드*/
	public String setDateFormat(Date date) {
		// 출력 포맷 정의
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
       // 출력 포맷 적용
		return format.format(date);
	}

	//제목 길이 지정
	private String titleEllipsis(String p_title) {
		String printTitle = null;
		int limit = 20;
		StringBuffer sb = new StringBuffer(p_title);
		if(sb.length()>limit) {
			sb.setLength(limit);
			sb.append("...");
		}
		printTitle = sb.toString();
		
		return printTitle;
	}
	
	//게시판 제목 길이 지정
	private String boardTitleEllipsis(String p_b_title) {
		String printBoardTitle = null;
		int limit = 6;
		StringBuffer sb = new StringBuffer(p_b_title);
		if(sb.length()>limit) {
			sb.setLength(limit);
			sb.append("...");
		}
		printBoardTitle = sb.toString();
		
		return printBoardTitle;
	}

	public Post(int p_num) {
		super();
		this.p_num = p_num;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return p_num == other.p_num;
	}



	@Override
	public int hashCode() {
		return Objects.hash(p_num);
	}
	


	
	
	
	
	
}

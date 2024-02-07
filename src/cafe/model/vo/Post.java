package cafe.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Post {
	
	//게시글 번호, 게시판 번호
	private int p_num, p_b_num;
	//제목, 작성자, 내용
	private String p_title, p_u_id, p_content;
	//작성시간
	private Date p_date;
	
	
	
	
	public Post(int p_num, int p_b_num, String p_title, String p_u_id, String p_content, Date p_date) {
		super();
		this.p_num = p_num;
		this.p_b_num = p_b_num;
		this.p_title = p_title;
		this.p_u_id = p_u_id;
		this.p_content = p_content;
		this.p_date = p_date;
	}




	public Post(int p_b_num, String p_title, String p_u_id, String p_content, Date p_date) {
		super();
		this.p_b_num = p_b_num;
		this.p_title = p_title;
		this.p_u_id = p_u_id;
		this.p_content = p_content;
		this.p_date = p_date;
	}
	
	
	
	
	
}

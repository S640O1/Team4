package cafe.model.vo;

import lombok.Data;

@Data
public class Category {
	
	private int c_num;	// 카테고리 번호
	private String c_title; // 카테고리 제목

	public Category(int c_num, String c_title) {
		this.c_num = c_num;
		this.c_title = c_title;
	}
}

package cafe.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
	
	private int c_num;	// 카테고리 번호
	private String c_title; // 카테고리 제목

	public Category(String c_title) {
		this.c_title = c_title;
	}

	@Override
	public String toString() {
		return "Category [c_title=" + c_title + "]";
	}
	
	public String toNumString() {
		return c_num + "[c_title=" + c_title + "]";
	}
	
	
}

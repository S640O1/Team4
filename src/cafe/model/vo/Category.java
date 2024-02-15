package cafe.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
	
	private int c_num;	// 카테고리 번호
	private String c_title; // 카테고리 제목

	public Category(int c_num) {
		this.c_num = c_num;
	}

	
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return c_num == other.c_num;
	}


	@Override
	public int hashCode() {
		return Objects.hash(c_num);
	}
	
	
}

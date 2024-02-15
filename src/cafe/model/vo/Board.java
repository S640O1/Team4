package cafe.model.vo;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Board {

	private int b_num;		// 게시판 번호 AI
	private String b_title; 	// 게시판 제목
	private int b_c_num; 	// 카테고리 번호
	
	// 게시판 번호 같을 때 등록 X
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return b_num == other.b_num;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(b_num);
	}
	
	public Board(int b_num) {
		this.b_num = b_num;
	}

	public Board(int b_c_num, String b_title) {
		this.b_c_num = b_c_num;
		this.b_title = b_title;
	}

	public String simpleString() { // b_num 없는 버전
		return "Board [b_num=" + b_num + ", b_title=" + b_title + ", b_c_num=" + b_c_num + "]";
	}

	@Override
	public String toString() {  // b_num 있는 버전
		return "Board [b_num=" + b_num + ", b_title=" + b_title + ", b_c_num=" + b_c_num + "]";
	}
	

	
	
}

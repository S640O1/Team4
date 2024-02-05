package siteCafeManagement.manager.board;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import siteCafeManagement.membership.Membership;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Board implements Serializable {

	private static final long serialVersionUID = -8940116248764171941L;
	
	Membership membership;
	int boardNum;			// 게시판 번호 (AUTO_INCREMENT)
	String boardTitle;		// 게시판 제목
		
	public String toString() {
		return boardNum + ". " + boardTitle;
	}


	@Override
	public int hashCode() {
		return Objects.hash(boardNum);
	}
	
	@Override
	public boolean equals(Object obj) {		// 게시판 번호는 같지 않도록 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return boardNum == other.boardNum;
	}
	
}

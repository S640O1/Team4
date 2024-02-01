package siteCafeManagement.manager.board;

import java.io.Serializable;

import siteCafeManagement.membership.Membership;

public class Board implements Serializable {

	private static final long serialVersionUID = -8940116248764171941L;
	
	Membership membership;
	int boardNum;			// 게시판 번호
	String boardTitle;		// 게시판 제목
	
	@Override
	public String toString() {
		return "boardNum =" + boardNum + ", boardTitle =" + boardTitle;
	}
	
}

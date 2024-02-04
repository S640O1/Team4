package siteCafeManagement.service;

import java.util.List;

import siteCafeManagement.manager.board.Board;

public interface FileService {
		
	// 게시판 파일 정보 불러오기 : 심아진
	List<Board> bLoad(String boardFileName);

	// 게시판 파일 정보 저장하기 : 심아진
	boolean bSave(String boardFileName, List<Board>bList);
}

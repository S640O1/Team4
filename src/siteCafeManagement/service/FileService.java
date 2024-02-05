package siteCafeManagement.service;

import java.util.List;

import siteCafeManagement.manager.board.Board;
import siteCafeManagement.post.Post;
import university.Student;

public interface FileService {
		
	// 게시판 파일 정보 불러오기 : 심아진
	List<Board> boardLoad(String boardFileName);

	/** 게시글 파일정보 불러오기 : 손나영*/
	List<Post> postLoad(String postFileName);

	// 게시판 파일 정보 저장하기 : 심아진
	boolean boardSave(String boardFileName, List<Board>bList);

	/** 게시글 파일정보 저장하기 : 손나영*/
	boolean postSave(String postFileName, List<Post> postList);
}

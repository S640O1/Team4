package siteCafeManagement.service;

import java.util.List;

import siteCafeManagement.post.Post;
import university.Student;

public interface FileService {
	
	
	/** 게시글 파일정보 저장하기 : 손나영*/
	boolean postSave(String postFileName, List<Post> postList);
	
	/** 게시글 파일정보 불러오기 : 손나영*/
	List<Post> postLoad(String postFileName);

}

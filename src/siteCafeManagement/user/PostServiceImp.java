package siteCafeManagement.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import siteCafeManagement.MainProgram;


/*
	- 사용자 (4) : 나영
	- 게시글 등록
	- 게시글 조회
	- 게시글 수정
	- 게시글 삭제
 */
public class PostServiceImp implements PostService{
	public static Scanner scan = MainProgram.scan;
	public static List<Post> postList = MainProgram.postList;
	public static List<Post> postUserList = new ArrayList<Post>();
	//카테고리 리스트
	//게시판리스트
	static User user = MainProgram.user;
	
	
	@Override
	public void addPostService() {
		//카테고리 리스트 출력
		//카테고리 선택
		
		//게시판리스트 출력
		//게시판 선택
		
		//제목, 내용 입력받기
		
		//post 객체 생성
		
		//postList에 객체 추가
		
	}

	@Override
	public void printPostService() {
		//게시글 목록이 비어있다면
			//return;
		//게시글 목록이 비어있지않다면
			//출력~
	}

	@Override
	public void setPostService() {
		//본인이 작성한 게시글 목록 출력
		
		//수정할 게시글 선택(무한루프, break사용)
		
		//수정할 내용 입력
		
		//새로운 객체 생선
		
		//기존 내역 삭제 deletePost() 사용?
		
		//새로운 객체 추가 addPost() 사용?
		
	}

	@Override
	public void deletePostService() {
		//본인이 작성한 게시글 목록 출력
		
		//삭제할 게시글 선택(무한루프, break사용)
		
		//해당 게시글을 삭제하시겠습니까?
			//Yy 
				//삭제
	}

	/** postList에 post를 추가하는 메소드
	 * 매개변수 : post 객체
	 * */
	@Override
	public void addPost() {
		
		
	}

	/** postList 전체를 출력하는 메소드
	 * */
	@Override
	public void printPostList() {
		// TODO Auto-generated method stub
		
	}
	
	/** post한개를 조회하는 메소드*/
	@Override
	public void printPost() {
		// TODO Auto-generated method stub
		
	}
	
	/** postList에 post를 수정하는 메소드
	 * 매개변수 : post 객체
	 * */
	@Override
	public void setPost() {
		// TODO Auto-generated method stub
		
	}
	
	/** postList에 post를 삭제하는 메소드
	 * 매개변수 : post 객체
	 * */
	@Override
	public void deletePost() {
		// TODO Auto-generated method stub
		
	}
	
	
	//본인이 작성한 게시글 List가져오기


}

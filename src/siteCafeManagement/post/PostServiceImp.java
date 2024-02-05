package siteCafeManagement.post;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import siteCafeManagement.MainProgram;
import siteCafeManagement.membership.Membership;


/*
	- 사용자 (4) : 나영
	- 게시글 등록
	- 게시글 조회
	- 게시글 수정
	- 게시글 삭제
 */
public class PostServiceImp implements PostService{
	public static Scanner scan = MainProgram.scan;
	private static List<Post> postUserList = new ArrayList<Post>();
	//카테고리 리스트
	//게시판리스트
//	static Membership membership = MainProgram.membership;
	
	@Override
	public void addPostService(List<Post> postList) {
		//카테고리 리스트 출력
		//카테고리 선택
//		Category category;
		String category="";
		
		//게시판리스트 출력
		//게시판 선택
		String board="";
		
		//제목, 내용 입력받기
		System.out.print("제목을 입력하세요(1~20자) : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("제목을 입력하세요(1~20자) : ");
		String content = scan.nextLine();
		
		//날짜는 작성 시간을 불러온다
//		Date date= new Date();	//JAVA8 이전까지 사용하던 Date타입은 JAVA8이후부터 권장되지 않음
		//LocalDate : 한번 지정되면 변하지 않음
		LocalDate date = LocalDate.now();
		
		
		//post 객체 생성
		Post post = new Post(Membership, title, content,category, board, date);
		addPost(postList, post);
		
	}

	@Override
	public void printPostService(List<Post> postList) {
		//게시글 목록이 비어있다면
			//return;
		//게시글 목록이 비어있지않다면
			//출력~
	}

	@Override
	public void setPostService(List<Post> postList) {
		//본인이 작성한 게시글 목록 출력
		
		//수정할 게시글 선택(무한루프, break사용)
		int index;
		while(true) {
			System.out.print("수정할 게시글 선택 : ");
			index = scan.nextInt() - 1;
			if(index<0 || index>postList.size()-1) {
				break;
			}
		}
		//수정할 내용 입력
		System.out.print("수정할 제목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("수정할 내용 : ");
		scan.nextLine();
		String content = scan.nextLine();
		
		setPost(postList, index, title, content);
		
//		//새로운 객체 생선
//		Post newPost = new Post(user, title, content, postList.get(index).getCategory(), postList.get(index).getBoard(), postList.get(index).getDate());
//		//기존 내역 삭제 deletePost() 사용?
//		deletePost();
//		//새로운 객체 추가 addPost() 사용?
//		addPost(newPost);
	}

	@Override
	public void deletePostService(List<Post> postList) {
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
	public void addPost(List<Post> postList, Post post) {
		//만약 postList가 비어있다면 => 해당 조건이 필요한가(선처리가 되어있는가 확인 필요)
			//객체생성 
		//postList에 객체 추가
		postList.add(post);
	}

	/** postList 전체를 출력하는 메소드
	 * */
	@Override
	public void printPostList(List<Post> postList) {
		for(Post p : postList) {
			p.simpleToString();
		}
	}
	
	/** post한개를 조회하는 메소드*/
	@Override
	public void printPost(List<Post> postList) {
		for(Post p : postList) {
			p.toString();
		}
	}
	
	/** postList에 post를 수정하는 메소드
	 * 매개변수 : post 객체
	 * */
	@Override
	public void setPost(List<Post> postList, int index, String title, String content) {
		postList.get(index).setTitle(title);
		postList.get(index).setContent(content);
	}
	
	/** postList에 post를 삭제하는 메소드
	 * 매개변수 : post 객체
	 * */
	@Override
	public void deletePost(List<Post> postList) {
		
	}
	

	
	//본인이 작성한 게시글 List가져오기


}

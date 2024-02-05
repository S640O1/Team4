package siteCafeManagement.post;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import siteCafeManagement.MainProgram;
import siteCafeManagement.manager.board.Board;
import siteCafeManagement.membership.Membership;


/*
	- 사용자 (4) : 나영
	- 게시글 등록
	- 게시글 조회
	- 게시글 수정
	- 게시글 삭제
 */

/*
 	추가 및 수정해야 할 부분
 		- 카테고리와 게시판 리스트 출력부분 확인 후 맞춰서 수정
 		- post 멤버 내 membership을 아이디만 넣을지 아니면 전체 객체를 넣을지
 			(아이디 변경 불가능하니까 아이디만 넣는것도 나쁘지 않을것 같음(정보변경대비)
 */
public class PostServiceImp implements PostService{

	static Scanner scan = MainProgram.scan;

	//카테고리 리스트
	
	//게시판리스트
	
	//로그인 유저 정보
	static Membership membership = MainProgram.membership;
	
	/** 1. 게시글 등록 */
	@Override
	public void addPostService(List<Post> categoryList, List<Board> boardList, List<Post> postList) {
		//카테고리 리스트 출력
		//카테고리 선택
		int indexCategory = -1;
		System.out.print("카테고리를 선택하세요 : ");
		String category="";
//		String category=categoryList.get(indexCategory).getTitle();
		
		//게시판리스트 출력
		//게시판 선택
		int indexBoard = -1;
		String board="";
//		String board=boardList.get(indexBoard).getTitle;
		
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
		
		//게시글 번호 생성 (postList의 마지막 게시글의 postNum + 1 한 값)
		int postNum;
		if(postList.size()==0) {
			postNum = 1;
		}else {
			postNum = postList.get(postList.size()-1).getPostNum() + 1;
		}
		
		//post 객체 생성
		Post post = new Post(postNum, membership, title, content,category, board, date);

		addPost(postList, post);
	}
	
	/** 2. 게시글 조회 */
	@Override
	public void printPostService(List<Post> postList) {
		// 1. 전체 게시글 조회
		if(!printPostList(postList)) {
			System.out.println("조회할 게시글이 없습니다.");
		}
		
		// 2. 게시글 선택
		int index = -1;
		int postNum = -1;
		while(true) {
			System.out.print("조회할 게시글을 선택 : ");
			postNum = scan.nextInt() - 1;
			if(postList.contains(new Post(postNum))){
				index = postList.indexOf(new Post(postNum));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		//3. 게시물 조회
		printPost(postList, index);
	}

	/** 3. 게시글 수정 */
	@Override
	public void setPostService(List<Post> postList) {
		//해당 로그인 유저가 쓴 게시글 목록 출력
		if(printUserPostList(postList)) {
			System.out.println("내가 작성한 게시글이 없습니다.");
		}
		
		//수정할 게시글 번호 선택(무한루프, break사용)
		int index = -1;
		int postNum = -1;
		while(true) {
			System.out.print("수정할 게시글 선택 : ");
			postNum = scan.nextInt() - 1;
			if(postList.contains(new Post(postNum))){
				index = postList.indexOf(new Post(postNum));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		//수정할 내용 입력
		System.out.print("수정할 제목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("수정할 내용 : ");
		scan.nextLine();
		String content = scan.nextLine();
		
		//수정 메소드 실행
		setPost(postList, index, title, content);
	}

	/** 4. 게시글 삭제 */
	@Override
	public void deletePostService(List<Post> postList) {
		//해당 로그인 유저가 쓴 게시글 목록 출력
		if(printUserPostList(postList)) {
			System.out.println("내가 작성한 게시글이 없습니다.");
		}

		
		//삭제할 게시글 선택(무한루프, break사용)
		int index = -1;
		int postNum = -1;
		while(true) {
			System.out.print("삭제할 게시글 선택 : ");
			postNum = scan.nextInt() - 1;
			if(postList.contains(new Post(postNum))){
				index = postList.indexOf(new Post(postNum));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}

		while(true) {
			System.out.print("정말로 삭제하겠습니까?(Y/N) : ");
			char areYouSure = scan.next().charAt(0);
			if(areYouSure == 'y' || areYouSure == 'Y') {
				deletePost(postList, index);
				System.out.println("삭제되었습니다.");
				break;
			}
			else if(areYouSure == 'n' || areYouSure == 'N') {
				System.out.println("취소되었습니다.");
				break;
			}
			System.out.println("잘못된 문자입니다.");
		}
		
	}

	/** postList에 post 객체를 추가하는 메소드
	 * 매개변수 : post 객체
	 * */
	@Override
	public void addPost(List<Post> postList, Post post) {
		//postList에 객체 추가
		postList.add(post);
	}

	/** postList 전체를 출력하는 메소드 (심플)
	 * */
	@Override
	public boolean printPostList(List<Post> postList) {
		if(!postList.isEmpty()) {
			return false;
		}
		for(Post p : postList) {
			p.simpleToString();
		}
		return true;
	}
	
	/** post 한개를 조회하는 메소드*/
	@Override
	public void printPost(List<Post> postList, int index) {
		postList.get(index).toString();
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
	public void deletePost(List<Post> postList, int index) {
		postList.remove(index);
	}

	/** 본인이 작성한 게시글 List가져오기*/
	@Override
	public boolean printUserPostList(List<Post> postList) {	
		List<Post> myPostList = new ArrayList<Post>();
		for(int i=0; i<postList.size(); i++) {
			if(postList.get(i).getMembership().equals(membership)) {
				myPostList.add(postList.get(i));
			}
		}
		
		//만약 내가 작성한 글이 없다면
		if(myPostList.size() == 0) {
			return false;
		}
		
		for(Post p : myPostList) {
			System.out.println(p.simpleToString());
		}
		
		return true;
		
	}
	

	


}

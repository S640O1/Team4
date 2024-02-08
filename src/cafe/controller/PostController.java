package cafe.controller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import cafe.model.vo.Post;
import cafe.model.vo.User;
import cafe.service.PostService;
import cafe.service.PostServiceImp;
import cafe.service.PrintService;
import cafe.service.PrintServiceImp;


public class PostController {
	private Scanner scan;
	private PostService postService;
	private PrintService printService = new PrintServiceImp();
	User user;
	
	private static final int EXIT_POST = 5;
	
	public PostController(Scanner scan, User user) {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		this.user = user;
		postService = new PostServiceImp();
	}
	
	public void run() {
		int menu;
		do {
			printService.printPostMenu();
			menu = scan.nextInt();
			runMenu(menu);
		}while(menu != EXIT_POST);		
	}

	private void runMenu(int menu) {
		switch(menu) {
		case 1 : //게시글 등록
			addPostService();
			break;
		case 2 : //게시글 조회
			printPostService();
			break;
		case 3 : //게시글 수정
			setPostService();
			break;
		case 4 : //게시글 삭제
			deletePostService();
			break;
		case 5 :	//뒤로가기
			break;
		default : throw new InputMismatchException();
		}
	}

	/** 4. 게시글 삭제*/
	private void deletePostService() {
		
	}

	/** 3. 게시글 수정*/
	private void setPostService() {
		
	}
	
	/** 2. 게시글 조회*/
	private void printPostService() {
		
	}

	/** 1. 게시글 등록*/
	private void addPostService() {
		//정보 입력받기
		Post post = postInput();
		if(postService.insertPost(post)) {
			System.out.println("내역을 추가했습니다.");
		}else {
			System.out.println("내역을 추가하지 못했습니다.");
		}
	}
	
		/** 1 - 1. 게시글 정보 입력받기*/
	private Post postInput() {
		//아이디 
//		String p_u_id = user.getU_id();
		String p_u_id = "qwerty123";
		
		//카테고리와 게시판 출력
		
		//게시판 선택 : while
		System.out.print("게시판을 선택하세요 : ");
		int p_b_num = scan.nextInt();
		
		// + 정규표현식
		//제목, 내용 입력받기 : while
		System.out.print("제목을 입력하세요(1~20자) : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("내용을 입력하세요 : ");
		String content = scan.nextLine();
		
		//날짜 받아오기
		Date date = new Date();	//날짜, 시간 다 받아오기
		System.out.println("시간을 받아왔습니다.");
				
		Post post = new Post(p_b_num, title, p_u_id, content, date);
		System.out.println(post.toString());
		return post;
	}


}

















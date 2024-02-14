package cafe.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
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
	private UserController userController = new UserController(scan); 
	
	//게시판 리스트 불러오기
	//ArrayList<Board> bList = boardService.get~();
	
	User user;
	
	private static final int EXIT_POST = 6;
	private static final int EXIT_SELECT_POST = 3;
	
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

	public void runMenu(int menu) {
		switch(menu) {
		case 1 : //게시글 등록
			addPostService();
			break;
		case 2 : //게시글 조회
			viewPostService();
			break;
		case 3 : //게시글 수정
			setPostService();
			break;
		case 4 : //게시글 삭제
			deletePostService();
			break;
		case 5 :	//뒤로가기
			break;
		case 6 :	//로그아웃
			userController.logOut();
			break;
		default : throw new InputMismatchException();
		}
	}

	/** 4. 게시글 삭제*/
	private void deletePostService() {
		//내가 작성한 글 조회
		ArrayList<Post> myPostList = postService.getMyPostList("qwerty123");
		if(!printPostList(myPostList)) {
			System.out.println("작성한 게시글이 없습니다.");
			return;
		}
		
		//삭제할 글 선택
		int p_num, index;
		while(true) {
			System.out.print("삭제할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(myPostList.contains(new Post(p_num))) {
				index = myPostList.indexOf(new Post(p_num));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		if(postService.deletePost(p_num)) {
			System.out.println("내역을 삭제했습니다.");
		}else {
			System.out.println("내역을 삭제하지 못했습니다.");
		}
	}

	/** 3. 게시글 수정*/
	private void setPostService() {
		//내가 작성한 글 조회
		ArrayList<Post> myPostList = postService.getMyPostList("qwerty123");
		if(!printPostList(myPostList)) {
			System.out.println("작성한 게시글이 없습니다.");
			return;
		}
		 
		//수정할 글 선택
		int p_num, index;
		while(true) {
			System.out.print("수정할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(myPostList.contains(new Post(p_num))) {
				index = myPostList.indexOf(new Post(p_num));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		//수정할 값 입력받기
		Post newPost = postSetInput(myPostList.get(index));
		newPost.setP_b_num(p_num);
		if(postService.updatePost(newPost)) {
			System.out.println("내역을 수정하였습니다.");			
		}else {
			System.out.println("내역을 수정하지 못했습니다.");
		}
		
	}
	
		/** (1) 수정사항 입력받기*/
	private Post postSetInput(Post post) {
		//아이디 
//		String p_u_id = user.getU_id();
		String p_u_id = "qwerty123";
		
		//카테고리와 게시판 출력
		
		//게시판 선택 : while //조건문 : 없는 값이라면~
		System.out.print("게시판을 선택하세요 : ");
		int p_b_num = scan.nextInt();
		
		// + 정규표현식
		//제목, 내용 입력받기 : while문으로 정규표현식 체크
		System.out.print("제목을 입력하세요(1~20자) : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("내용을 입력하세요 : ");
		String content = scan.nextLine();
				
		Post newPost = new Post(p_b_num, title, p_u_id, content, post.getP_date());
		System.out.println(newPost.toString());
		return newPost;
	}

	/** 2. 게시글 조회*/
	private void viewPostService() {
		//조회 메뉴
		int selectMenu;
		do {
			printService.printViewPostMenu();
			selectMenu = scan.nextInt();
			runViewPostMenu(selectMenu);
		}while(selectMenu != EXIT_SELECT_POST);		
	}
	
	/** 2. 게시글 조회 선택메뉴 실행*/
	private void runViewPostMenu(int selectMenu) {
		switch(selectMenu) {
		case 1 : //전체 글 조회(최신순)
			ViewPostList();
			break;
		case 2 : //내가 쓴 글 조회
			ViewMyPost();
			break;
		case 3 : //뒤로가기
			break;
		default : throw new InputMismatchException();
		}
	}
	
	/** 2-2. 내가 쓴 글 조회*/
	private void ViewMyPost() {
		ArrayList<Post> myPostList = postService.getMyPostList(user.getU_id());
		if(!printPostList(myPostList)) {
			System.out.println("작성한 게시글이 없습니다.");
			return;
		}
	}

		/** 2-1. 전체 글 조회*/
	private void ViewPostList() {
		//카테고리 가져오기
			//선택하기
		//게시판 가져오기
			//선택하기
		
		//전체 글 가져오기 
		ArrayList<Post> postList = new ArrayList<Post>();
		postList = postService.getPostList();
		if(!printPostList(postList)) {
			System.out.println("조회할 게시글이 없습니다.");
			return;
		}
		
		//조회할 게시글을 선택하세요.
		int p_num;
		while(true) {
			System.out.print("조회할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(postList.contains(new Post(p_num))) {
				Post post = postService.getPost(p_num);
				if(!printPost(post)) {
					System.out.println("조회할 게시글이 없습니다.");
				}
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
	}


		/** (2) post 한개를 출력하는 메소드(상세조회)*/	
		private boolean printPost(Post post) {
			if(post == null) {
				return false;
			}
			System.out.println(post.toString());
			return true;
		}

		/** (1) postList 전체를 출력하는 메소드(심플)*/	
	private boolean printPostList(List<Post> postList) {
		if(postList.isEmpty()) {
			return false;
		}
		for(Post p : postList) {
			System.out.println(p.simpleToString());
		}
		return true;
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
		
		//게시판 선택 : while //조건문 : 없는 값이라면~
		System.out.print("게시판을 선택하세요 : ");
		int p_b_num = scan.nextInt();
		
		// + 정규표현식
		//제목, 내용 입력받기 : while문으로 정규표현식 체크
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

















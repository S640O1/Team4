package cafe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import cafe.model.vo.Board;
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
	
	// Controller 불러오기
	private UserController userController = new UserController(scan); 
	private CategoryController categoryController = new CategoryController(scan);
	private BoardController boardController = new BoardController(scan);

	
	private static User user;
	
	private static final int EXIT_POST = 4;
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
		int menu = 0;
		do {
			System.out.println();
			printService.printPostMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
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
		case 3 : //내 게시글 관리자
			myPostService();
			break;
		case 4 :	//로그아웃
			userController.logOut();
			break;
		default : throw new InputMismatchException();
		}
	}

	/** 3. 내 게시글 관리자*/
	private void myPostService() {
		int menu = 0;
		do {
			System.out.println();
			printService.MyPostMenu();
			try {
				menu = scan.nextInt();
				runMyPostMenu(menu);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT_POST);	
	}

	private void runMyPostMenu(int menu) {
		switch(menu) {
		case 1 : // 내 게시글 조회
			viewMyPost();
			break;
		case 2 : //게시글 수정
			setPostService();
			break;
		case 3 : //게시글 삭제
			deletePostService();
			break;
		case 4 : //뒤로가기
			break;
		default : throw new InputMismatchException();
		}
		
	}

	/** 4. 게시글 삭제*/
	private void deletePostService() {
		//내가 작성한 글 조회
		ArrayList<Post> myPostList = postService.getMyPostList(user.getU_id());
		if(!printPostList(myPostList)) {
			System.out.println("작성한 게시글이 없습니다.");
			return;
		}
		
		//삭제할 글 선택
		int p_num;
		while(true) {
			System.out.print("삭제할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(myPostList.contains(new Post(p_num))) {
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
		ArrayList<Post> myPostList = postService.getMyPostList(user.getU_id());
		if(!printPostList(myPostList)) {
			System.out.println("작성한 게시글이 없습니다.");
			return;
		}
		 
		//수정할 글 선택
		int p_num;
		while(true) {
			System.out.print("수정할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(myPostList.contains(new Post(p_num))) {
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		//수정할 값 입력받기
		Post setPost = postSetInput(p_num);
		if(postService.updatePost(setPost)) {
			System.out.println("내역을 수정하였습니다.");			
		}else {
			System.out.println("내역을 수정하지 못했습니다.");
		}
		
	}
	
		/** (1) 수정사항 입력받기*/
	private Post postSetInput(int p_num) {		
		//카테고리와 게시판 출력
		if(!boardController.printBoardListBoolean()) {
			return null;
		}
		
		//게시판 선택
		int p_b_num = -1;
		while(true) {
			System.out.print("게시판을 선택하세요 : ");
			p_b_num = scan.nextInt();
			if(boardController.containsBoardServiece(p_b_num)) {
				break;	
			}
			System.out.println("잘못된 게시판 번호입니다.");
		}
		
		//제목, 내용 입력받기 : while문으로 체크
		String title = null;
		scan.nextLine();
		while(true) {
			System.out.print("제목을 입력하세요(1~50자) : ");
			title = scan.nextLine();
			if(title.length()<=50 && title.length()>0) {
				break;
			}
			System.out.println("제목은 1~50자리만 가능합니다.");
		}
		String content = null;
		while(true) {
			System.out.print("내용을 입력하세요 : ");
			content = scan.nextLine();
			if(!(content == null)) {
				break;
			}			
			System.out.println("내용을 입력하세요");				
		}
		
				
		Post setPost = new Post(p_num, p_b_num, title, content);
		return setPost;
	}

	/** 2. 게시글 조회*/
	private void viewPostService() {
		//조회 메뉴
		int selectMenu;
		do {
			System.out.println();
			printService.printViewPostMenu();
			selectMenu = scan.nextInt();
			runViewPostMenu(selectMenu);
		}while(selectMenu != EXIT_SELECT_POST);		
	}
	
	/** 2. 게시글 조회 선택메뉴 실행*/
	private void runViewPostMenu(int selectMenu) {
		switch(selectMenu) {
		case 1 : //전체 글 조회(최신순)
			viewPostList();
			break;
		case 2 : //선택 조회 (게시판 선택)
			viewSelectPostList();
			break;
		case 3 : //뒤로가기
			break;
		default : throw new InputMismatchException();
		}
	}
	
	

	/** 2-3. 내가 쓴 글 조회*/
	private void viewMyPost() {
		ArrayList<Post> myPostList = postService.getMyPostList(user.getU_id());
		if(!printPostList(myPostList)) {
			System.out.println("작성한 게시글이 없습니다.");
			return;
		}
		
		int p_num;
		while(true) {
			System.out.print("조회할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(myPostList.contains(new Post(p_num))) {
				Post post = postService.getPost(p_num);
				if(!printPost(post)) {
					System.out.println("조회할 게시글이 없습니다.");
				}
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
	}
	
	/** 2-2. 선택 글 조회*/
	private void viewSelectPostList() {
		//카테고리와 게시판 출력
		if(!boardController.printBoardListBoolean()) {
			return;
		}
		
		//게시판 선택
		int p_b_num = -1;
		while(true) {
			System.out.print("게시판을 선택하세요 : ");
			p_b_num = scan.nextInt();
			if(boardController.containsBoardServiece(p_b_num)) {
				break;	
			}
			System.out.println("잘못된 게시판 번호입니다.");
		}
		
		//입력받은 게시판의 게시글 목록 가져오기
		ArrayList<Post> boardPostList = new ArrayList<Post>();
		boardPostList = postService.getBoardPostList(p_b_num);
		
		//출력
		if(!printPostList(boardPostList)) {
			System.out.println("조회할 게시글이 없습니다.");
			return;
		}
		
		int p_num;
		while(true) {
			System.out.print("조회할 게시글 번호를 선택하세요 : ");
			p_num = scan.nextInt();
			if(boardPostList.contains(new Post(p_num))) {
				Post post = postService.getPost(p_num);
				if(!printPost(post)) {
					System.out.println("조회할 게시글이 없습니다.");
				}
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
	
	}

		/** 2-1. 전체 글 조회*/
	private void viewPostList() {
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
			System.out.println(rH(110));
			System.out.println(post.toString());
			System.out.println(rH(110));
			return true;
		}

		/** (1) postList 전체를 출력하는 메소드(심플)*/	
	private boolean printPostList(List<Post> postList) {
		if(postList.isEmpty()) {
			return false;
		}
		System.out.println(rH(110));
		System.out.println(rS(2) + "번호" + rS(10) 
							+ "게시판" + rS(25)
							+ "제목" + rS(22)
							+ "작성자" + rS(10)
							+ "작성시간");
		System.out.println(rH(110));
		for(Post p : postList) {
			System.out.println(p.simpleToString());
		}
		System.out.println(rH(110));
		return true;
	}
	
	//returnSpace
	private String rS(int n) {
		String str="";
		for(int i=0; i<n; i++) {
			str += " ";
		}
		return str;
	}
	
	//returnHyphen
	private String rH(int n) {
		String str="";
		for(int i=0; i<n; i++) {
			str += "-";
		}
		return str;
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
		String p_u_id = user.getU_id();
		
		//카테고리와 게시판 출력
		if(!boardController.printBoardListBoolean()) {
			return null;
		}
		
		//게시판 선택
		int p_b_num = -1;
		while(true) {
			System.out.print("게시판을 선택하세요 : ");
			p_b_num = scan.nextInt();
			if(boardController.containsBoardServiece(p_b_num)) {
				break;	
			}
			System.out.println("잘못된 게시판 번호입니다.");
		}
		
		//제목, 내용 입력받기 : while문으로 체크
		
		String title = null;
		scan.nextLine();
		while(true) {
			System.out.print("제목을 입력하세요(1~50자) : ");
			title = scan.nextLine();
			if(title.length()<=50 && title.length()>0) {
				break;
			}
			System.out.println("제목은 1~50자리만 가능합니다.");
		}
		String content = null;
		while(true) {
			System.out.print("내용을 입력하세요 : ");
			content = scan.nextLine();
			if(!(content == null)) {
				break;
			}			
			System.out.println("내용을 입력하세요");				
		}
		
		//날짜 받아오기
		Date date = new Date();	//날짜, 시간 다 받아오기
		
		Post post = new Post(p_b_num, title, p_u_id, content, date);
		return post;
	}

}

















package siteCafeManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;
import siteCafeManagement.membership.MembershipImp;
import siteCafeManagement.service.FileService;
import siteCafeManagement.service.FileServiceImp;
import siteCafeManagement.service.PrintService;
import siteCafeManagement.service.PrintServiceImp;
import siteCafeManagement.user.Post;
import siteCafeManagement.user.PostService;
import siteCafeManagement.user.PostServiceImp;
import siteCafeManagement.user.User;



public class MainProgram implements Program{
	public static Scanner scan = new Scanner(System.in);

	//EXIT
	static final int EXIT = 4;
	private static final int EXIT_MEMBERSHIP = 4;
	private static final int EXIT_MANAGE = 3;

	//서비스
	private PrintService printService = new PrintServiceImp(); 	//print
	private FileService fileService = new FileServiceImp();		//file(load, save)
	private MembershipImp membershipImp = new MembershipImp();	//membership
	private PostServiceImp postService = new PostServiceImp();	//user
	
	//파일명
	static String userFileName = "src/siteCafeManagement/userList.txt";
	static String postFileName = "src/siteCafeManagement/postList.txt";
	
	//File List
	static List<User> userList = new ArrayList<User>();		//유저정보리스트
	public static List<Post> postList = new ArrayList<Post>();		//게시글정보리스트
	//카테고리 리스트
	//게시판 리스트
	
	//로그인 한 유저정보
	public static User user;

	@Override
	public void run() {
		int menu = 0;
		//load(fileName); 구현 예정
		do {
			System.out.println();
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
		//save(fileName); 구현 예정
	}

	@Override
	public void printMenu() {
		System.out.println("[카페 관리 프로그램]");
		System.out.println("1. 회원 관리");
		System.out.println("2. 관리자 관리");
		System.out.println("3. 사용자 관리");
		System.out.println("4. 종료");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1 : 
			membershipMenu();
			break;
		case 2 : 
			managerMenu();
			break;
		case 3 : 
			userMenu();
			break;
		case 4 : System.out.println("프로그램을 종료합니다.");
			break;
		default : throw new InputMismatchException();
		}
	}

	//회원 관리 메뉴
	private void membershipMenu() {
		int menu = 0;
		do {
			System.out.println();
			printService.printMembership();
			try {
				menu = scan.nextInt();
				runMembership(menu);				
			} catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT_MEMBERSHIP);
	}

	private void runMembership(int menu) {
		switch(menu) {
		case 1 : membershipImp.logIn();
			break;
		case 2 : membershipImp.logOut();
			break;
		case 3 : membershipImp.join();
			break;
		case 4 : 
			break;
		default : throw new InputMismatchException();
		}
	}

	//관리자 관리 메뉴
	private void managerMenu() {
		int menu = 0;
		do {
			System.out.println();
			printService.printManager();
			try {
				menu = scan.nextInt();
				runManage(menu);				
			} catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT_MANAGE);
	}
	
	private void runManage(int menu) {
		switch(menu) {
		case 1 :
			break;
		case 2 : 
			System.out.println("카테고리 관리 구현 예정");
			categoryManager();
			break;
		case 3 : // 뒤로가기
			break;
		default : throw new InputMismatchException();
		}
	}

	private void categoryManager() {
		int menu = 0;
		
		System.out.println();
		printService.printCategoryManager();
		
		try {
			menu = scan.nextInt();
			runCategory(menu);
		} catch(InputMismatchException e) {
			System.out.println("잘못된 메뉴입니다.");
			scan.nextLine();
		}
		
	}

	private void runCategory(int menu) {
		switch(menu) {
		case 1 :
			System.out.println("카테고리 등록 구현 예정");
			break;
		case 2 :
			System.out.println("카테고리 수정 구현 예정");
			break;
		case 3 :
			System.out.println("카테고리 삭제 구현 예정");
			break;
		case 4 :	// 뒤로가기
			break;
		default : throw new InputMismatchException();			
		}
	}

	//사용자 관리 메뉴
	//사용자 : 손나영
	private void userMenu() {
		int menu = 0;
		
			System.out.println();
			printService.printUser();
			try {
				menu = scan.nextInt();
				runUser(menu);				
			} catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
			
	}
	
	private void runUser(int menu) {
		switch(menu) {
		case 1 : //게시글 등록
			postService.addPostService();
			break;
		case 2 : //게시글 조회
			postService.printPostService();
			break;
		case 3 : //게시글 수정
			postService.setPostService();
			break;
		case 4 : //게시글 삭제
			postService.deletePostService();
			break;
		case 5 :	//뒤로가기
			
			break;
		default : throw new InputMismatchException();
		}
	}

}

package siteCafeManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

import program.Program;
import siteCafeManagement.membership.MembershipImp;
import siteCafeManagement.service.FileService;
import siteCafeManagement.service.FileServiceImp;
import siteCafeManagement.service.PrintService;
import siteCafeManagement.service.PrintServiceImp;
import siteCafeManagement.user.PostService;
import siteCafeManagement.user.PostServiceImp;



public class MainProgram implements Program{
	public static Scanner scan = new Scanner(System.in);

	//EXIT
	static final int EXIT = 4;
	private static final int EXIT_MEMBERSHIP = 4;
	private static final int EXIT_MANAGE = 0; //원하는 숫자 넣기(넣고 지우기)
	private static final int EXIT_USER = 5;
	
	//서비스
	private PrintService printService = new PrintServiceImp(); 	//print
	private FileService fileService = new FileServiceImp();
	private MembershipImp membershipImp = new MembershipImp();	//membership
	private PostServiceImp postService = new PostServiceImp();	//user
	
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
			break;
		case 3 : 
			break;
		default : throw new InputMismatchException();
		}
	}

	//사용자 : 손나영
	private void userMenu() {
		int menu = 0;
		do {
			System.out.println();
			printService.printUser();
			try {
				menu = scan.nextInt();
				runUser(menu);				
			} catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT_USER);
	}
	
	private void runUser(int menu) {
		switch(menu) {
		case 1 : //게시글 등록
			postService.addPost();
			break;
		case 2 : //게시글 조회
			postService.psintPost();
			break;
		case 3 : //게시글 수정
			postService.setPost();
			break;
		case 4 : //게시글 삭제
			postService.deletePost();
			break;
		case 5 :	//뒤로가기
			break;
		default : throw new InputMismatchException();
		}
	}

}

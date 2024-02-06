package cafe.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import cafe.controller.BoardController;
import cafe.controller.CategoryController;
import cafe.controller.PostController;
import cafe.controller.UserController;

public class Main {

	private static final int EXIT = 3;
	private static UserController userController;
	private static CategoryController categoryController;
	private static BoardController boardController;
	private static PostController postController;
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int menu = 0;
		userController = new UserController(scan);
		
		do {
			System.out.println();
			printPreLogInMenu();
			try {
				menu = scan.nextInt();
				runPreLogInMenu(menu);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
	}

	private static void printPreLogInMenu() {
		System.out.println("[KH Cafe]");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 나가기");
		System.out.print("메뉴 선택 : ");
		
	}

	private static void runPreLogInMenu(int menu) {
		switch(menu) {
		case 1 : userController.logIn();
			break;
		case 2 : userController.join();
			break;
		case 3 : System.out.println("카페를 나갑니다.");
			break;
		default : System.out.println("없는 메뉴입니다.");
		}
	}

}

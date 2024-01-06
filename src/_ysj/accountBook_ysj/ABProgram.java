package _ysj.accountBook_ysj;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ABProgram implements Program{
	
	Scanner sc = new Scanner(System.in);
	static final int EXIT = 6;
	static String fileName = "src/accountBook/accountBookList.txt";
	ABManager ab = new ABManager();

	@Override
	public void run() {
		int menu = 0;
		
//		load(fileName);
		do {
			printMenu();
			try {
				menu = sc.nextInt();
				runMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				sc.nextLine();
			}
		} while (menu != EXIT);
//		save(fileName);
	}

	@Override
	public void printMenu() {
		System.out.println("------가계부------");
		System.out.println("1. 가계부 입력");
		System.out.println("2. 가계부 조회");
		System.out.println("3. 가계부 수정");
		System.out.println("4. 가계부 삭제");
		System.out.println("5. 현재 잔액 조회");
		System.out.println("6. 종료");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1 :
			ab.insertMoney();
			break;
		case 2 :
			ab.printMoney();	
			break;
		case 3 :
			ab.updateMoney();		
			break;
		case 4 :
			ab.deleteMoney();		
			break;
		case 5 :					
			ab.currentMoney();	
			break;
		case 6 : System.out.println("프로그램을 종료합니다.");
			break;
		default :
			throw new InputMismatchException();
		}
	}
	
}

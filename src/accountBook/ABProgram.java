package accountBook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import word.Word;

public class ABProgram implements Program{
	
	Scanner scan = new Scanner(System.in);
	static final int EXIT = 6;
	static String fileName = "src/accountBook/accountBookList.txt";
	private List<AccountBook> list = new ArrayList<AccountBook>();

	@Override
	public void run() {
		int menu = 0;
		
//		load(fileName);
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != EXIT);
//		save(fileName);
	}

	@Override
	public void printMenu() {
		System.out.println("-------가계부--------");
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
			insertMoney();
			break;
		case 2 :
			printMoney();	
			break;
		case 3 :
			updateMoney();		
			break;
		case 4 :
			deleteMoney();		
			break;
		case 5 :					
			currentMoney();	
			break;
		case 6 :					//프로그램 종료
			break;
		default :
			throw new InputMismatchException();
		}
	}

	private void insertMoney() {
		// TODO Auto-generated method stub
		
	}

	private void printMoney() {
		// TODO Auto-generated method stub
		
	}

	private void updateMoney() {
		// TODO Auto-generated method stub
		
	}

	private void deleteMoney() {
		// TODO Auto-generated method stub
		
	}

	private void currentMoney() {
		// TODO Auto-generated method stub
		
	}
	
}

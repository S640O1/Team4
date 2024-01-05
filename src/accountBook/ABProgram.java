package accountBook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import word.Word;

public class ABProgram implements Program{
	
	Scanner sc = new Scanner(System.in);
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
		case 6 : System.out.println("프로그램을 종료합니다.");
			break;
		default :
			throw new InputMismatchException();
		}
	}

	//1. 가계부 입력 - 수입, 지출 항목 입력
	private void insertMoney() {
		System.out.println("[가계부 입력]");
		System.out.println("1. 수입");
		System.out.println("2. 지출");
		System.out.print("메뉴 선택 : ");
		int menu = sc.nextInt();
		switch(menu) {
		case 1 : 
			System.out.println("[수입]");
			System.out.println("년도 : ");
			int year = sc.nextInt();
			System.out.println("월 : ");
			int month = sc.nextInt();
			System.out.println("일 : ");
			int day = sc.nextInt();
			System.out.println("입금 금액 : ");
			int plusMoney = sc.nextInt();
			System.out.println("내역 : ");
			String memo = sc.nextLine();
			
			AccountBook plus = new AccountBook(null, plusMoney, memo);
			
			break;
			
		case 2 : 
			System.out.println("[지출]");
			System.out.println("년도 : ");
			year = sc.nextInt();
			System.out.println("월 : ");
			month = sc.nextInt();
			System.out.println("일 : ");
			day = sc.nextInt();
			System.out.println("지출 금액 : ");
			int useMoney = sc.nextInt();
			System.out.println("내역 : ");
			memo = sc.nextLine();
			
			AccountBook use = new AccountBook(null, useMoney, memo);
			
			break;
			
		default : System.out.println("없는 메뉴입니다.");
		}
	}

	//2. 가계부 조회 - 하루, 주간, 월간, 연간
	private void printMoney() {
		
	}

	//3. 가계부 수정 1)수입/지출 여부 2)년 월 일 3)사용금액 4)잔액 5)내역 6)전체수정
	private void updateMoney() {
		
	}

	//4. 가계부 삭제
	private void deleteMoney() {
		
	}

	//5. 현재 잔액 조회
	private void currentMoney() {
		
	}
	
}

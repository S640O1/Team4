package _saj.accountBook_saj;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.text.*;

import word.Word;

public class ABProgram implements Program{
	
	Scanner scan = new Scanner(System.in);
	static final int EXIT = 6;
	static String fileName = "src/accountBook/accountBookList.txt";
	private List<AccountBook> list = new ArrayList<AccountBook>();
	private ABManager am = new ABManager();

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
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		// 수입 지출 항목 입력
		System.out.println("-------가계부 입력 -------");
		System.out.println("1. 수입");
		System.out.println("2. 지출");
		System.out.println("메뉴 선택 : ");
		
		try {
			int menu = scan.nextInt();
			scan.nextLine();
			
			switch (menu) {
			case 1 :		// 수입을 선택했을 때 // 날짜 출력시 예쁘게 보이도록 format 해야함
				System.out.println("날짜 (2024-01-01) : ");
				while (scan.hasNextLine()) {
					try {
						date = format1.parse(scan.nextLine());
						
						System.out.println("입금 금액 : ");
						int inMoney = scan.nextInt();
						scan.nextLine();
						
						System.out.println("내역 : ");
						String memo = scan.nextLine();
						
						System.out.println("날짜 : " + date + " 입금 금액 : " + inMoney + " 내역 : " + memo );
						
					} catch (Exception e) {
						System.out.println("날짜를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
					}
				}
				
				break;
			case 2 :		// 지출을 선택했을 때  // 날짜 출력시 예쁘게 보이도록 format 해야함
				System.out.println("날짜 (2024-01-01) : ");
				while (scan.hasNextLine()) {
					try {
						date = format1.parse(scan.nextLine());
						
						System.out.println("지출 금액 : ");
						int inMoney = scan.nextInt();
						scan.nextLine();
						
						System.out.println("내역 : ");
						String memo = scan.nextLine();
						
						System.out.println("날짜 : " + date + " 지출 금액 : " + inMoney + " 내역 : " + memo );
						
					} catch (Exception e) {
						System.out.println("날짜를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
					}
				}
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}

	private void printMoney() {
		
	}

	private void updateMoney() {
		
	}

	private void deleteMoney() {
		
	}

	private void currentMoney() {
		
	}
	
}

package _saj.accountBook_saj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import accountBook.Item;
import accountBook.service.AccountBookService;
import accountBook.service.AccountBookServiceImp;
import accountBook.service.FileService;
import accountBook.service.FileServiceImp;
import accountBook.service.PrintService;
import accountBook.service.PrintServiceImp;
import program.Program;

public class ABProgram implements Program{
	
	static String fileName = "src/accountBook/accountBookList.txt";
	private Scanner scan = new Scanner(System.in);
	
	//서비스 불러오기
	private AccountBookService accountBookService = new AccountBookServiceImp();
	private FileService fileService = new FileServiceImp();
	private PrintService printService = new PrintServiceImp();

	private List<Item> list = new ArrayList<Item>(); 	//수입 지출 내역
	
	
	
	//반복종료 번호
	static final int EXIT = 6;

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
		printService.printMainMenu();
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
		case 6 :				//프로그램 종료
			System.out.println("프로그램을 종료합니다.");
			break;
		default :
			throw new InputMismatchException();
		}
	}
	
	/** 가계부 입력 : 심아진 */
	private void insertMoney() {
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		int money = 0, inMoney = 0, outMoney = 0, totalInMoney = 0, totalOutMoney = 0;
		
		
		System.out.println("날짜 (yyyy-MM-dd) : ");
		scan.nextLine();
		
		while (scan.hasNextLine()) {
			try {
				date = format1.parse(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("날짜를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
			}
		}
		
		System.out.println("금액 : ");
		money = scan.nextInt();
		scan.nextLine();
		
		System.out.println("수입(1)/지출(2) : ");
		int classify = scan.nextInt();
		
		if (classify == 1) {
			inMoney = money;
			totalInMoney += inMoney;
			// System.out.println(inMoney);
			// System.out.println(totalInMoney);
		} else if (classify == 2) {
			outMoney = money;
			totalOutMoney += outMoney;
			// System.out.println(outMoney);
			// System.out.println(totalOutMoney);
		}
			
		System.out.println("내역 : ");
		scan.nextLine();
		String memo = scan.nextLine();
		
		System.out.println("날짜 : " + format1.format(date) + " 금액 : " + money + " 내역 : " + memo );
		System.out.println("등록이 완료되었습니다.");
	}		
	
	/** 가계부 조회 :  */
	private void printMoney() {
		
	}

	/** 가계부 수정 :  */
	private void updateMoney() {
		// TODO Auto-generated method stub
		
	}

	/** 가계부 삭제 : */
	private void deleteMoney() {
		// TODO Auto-generated method stub
		
	}

	
	/** 현재 잔액 조회 :  */
	private void currentMoney() {
		accountBookService.printCurrentMoney();
		
	}
	
}

package accountBook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
			insertMoney();	//
			break;
		case 2 :
			printMoney();	//
			break;
		case 3 :
			updateMoney();	//수정 : 나영	
			break;
		case 4 :	
			deleteMoney();	
			break;
		case 5 :					
			currentMoney();	
			break;
		case 6 : System.out.println("프로그램을 종료합니다.");
			break;
		default : throw new InputMismatchException();
		}
	}

	/** 가계부 입력 : 심아진 */
	private void insertMoney() {
		// TODO Auto-generated method stub
		
	}

	/** 가계부 조회 :  */
	private void printMoney() {
		// TODO Auto-generated method stub
		
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

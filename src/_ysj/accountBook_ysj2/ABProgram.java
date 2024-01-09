package _ysj.accountBook_ysj2;

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
	private Scanner sc = new Scanner(System.in);
	
	//서비스 불러오기
	private FileService fileService = new FileServiceImp();
	private PrintService printService = new PrintServiceImp();

	private List<AccountBook> list = new ArrayList<AccountBook>(); 	//수입 지출 내역
	private AccountBook accountBook = new AccountBook();
	
	//반복종료 번호
	static final int EXIT = 6;
	
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

	/** 4. 가계부 삭제 : 양선진 */
		private void deleteMoney() {
		//순서대로 배열 나열해서 보기
		for(AccountBook tmp : list) {
			tmp.print();
		}
		//삭제할 번호 받기
		System.out.print("삭제할 번호 : ");
		//받은 num의 -1을 해야 인덱스 번호
		int index = sc.nextInt() - 1;
		
		//삭제할 번호가 없으면(인덱스가 0보다 작거나, 리스트 사이즈보다 클때)없다고 출력 후 return;
		if(index < 0 || index >= list.size()) {
			System.out.println("없는 번호입니다.");
			return;
		}
		//있으면 정말로 삭제하겠습니까? 문구 출력 후 y/n
		System.out.println("정말로 삭제하겠습니까?(y/n) : ");
		char areYouSure = sc.next().charAt(0);
		if(areYouSure == 'y') {
			//리스트의 index 배열 삭제
			list.remove(index);
			return;
		} else { //y외 다른 문자면 취소
			System.out.println("취소되었습니다.");
		}
	}

	
	/** 현재 잔액 조회 :  */
	private void currentMoney() {
		//accountBookService.printCurrentMoney();
		
	}
	
}

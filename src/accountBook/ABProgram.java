package accountBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	List<Item> list;
	
	//반복종료 번호
	static final int EXIT = 6;
	static final int UPDATE_EXIT = 6;
	
	@Override
	public void run() {
		int menu = 0;
		list = fileService.load(fileName); //수입 지출 내역
		
		if(list == null) {
			list = new ArrayList<Item>();
		}

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
		
		if(fileService.save(fileName, list)) {
			System.out.println("가계부를 저장했습니다."); 
		}else {
			System.out.println("가계부저장에 실패했습니다.");
		}
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

	/** 1. 가계부 입력 : 심아진 */
	private void insertMoney() {
		/* 강사 피드백
		 * - addAB(list)를 이용하여 리스트에 새 가계 내역을 입력받아 리스트에 저장하고
		 * - 성공했다면 여기에서 save를 이용하여 저장하는 것이 적절함.
		 * - 프로그램이 종료할 때 save 하거나, 수정할 때도 save를 하는 것이 좋음.
		 * - A서비스가 다른 서비스에서 호출되지 않게 작성하는 것이 좋음.
		 * - 입력을 여기서 받고 서비스에게는 정보만 전달해서 일을 시키는 게 좋음*/
		if(accountBookService.addAB(list, fileName)){
			System.out.println("가계부 등록이 완료되었습니다.");
		}
	}

	/** 2. 가계부 조회 : 경재*/
	private void printMoney() {
		/* 강사 피드백
		 * - printAB 메서드가 가계부 내용을 출력하는 기능이기 때문에 가계부 등록해주세요을
		 *   여기가 아닌 printAB 메서드에 넣는게 적절함.*/
		if(!accountBookService.printAB(list)) {
			System.out.println("가계부를 등록해주세요.");
		}
	}

	/** 3. 가계부 수정 : 손나영 */
	private void updateMoney() {
		if(!accountBookService.printAB(list)) {
			System.out.println("가계부를 등록해주세요.");
			return;
		}
		
		int index=-1;
		//수정할 항목 받아오기
		try {
			System.out.print("어떤 항목을 수정하시겠습니까? : "); 
			index = scan.nextInt()-1;
		}catch (InputMismatchException e){
			System.out.println("잘못된 메뉴입니다.");
			scan.nextLine();
		}
		
		//index가 잘못된 경우
		if(!accountBookService.indexError(index, list)) {
			System.out.println("없는 항목입니다.");
			return;			
		}
		
		//메뉴 선택
		int menu = 0;
		do {
			printUpdateMenu();
			try {
				menu = scan.nextInt();
				runUpdateMenu(menu, index);
				break;
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != UPDATE_EXIT);
	}

		/** (1) 가계부 수정 : 메뉴출력*/
	private void printUpdateMenu() {
		printService.printUpdateMenu();
	}

		/** (2) 가계부 수정 : 메뉴실행*/
	private void runUpdateMenu(int menu, int index) {
		switch(menu) {
			/* 강사 피드백
			 * - 수정에 실패한 경우 수정에 실패했다고 출력 후 하단에 수정을 완료했습니다라고 출력될텐데
			 *   의도한 건지?*/
			case 1 :	//수입, 지출 여부 변경
				if(!accountBookService.runUpateInOut(index, list)) {
					System.out.println("수정에 실패하였습니다.");
					return;
				};
				break;
			case 2 :	//일자수정
				if(!accountBookService.runUpateInDate(index, list)) {
					System.out.println("수정에 실패하였습니다.");
					return;
				};
				break;
			case 3 :	//금액수정
				if(!accountBookService.runUpateInMoney(index, list)) {
					System.out.println("수정에 실패하였습니다.");
					return;
				};
				break;
			case 4 :	//내역수정				
				if(!accountBookService.runUpateInMemo(index, list)) {
					System.out.println("수정에 실패하였습니다.");
					return;
				};
				break;
			case 5 : //전체수정
				if(!accountBookService.runUpateInAll(index, list)) {;	
				System.out.println("수정에 실패하였습니다.");
				return;
				}
				break;
			case 6 : System.out.println("뒤로가기");
				break;
			default : throw new InputMismatchException();
		}
		System.out.println("수정을 완료했습니다.");
		fileService.save(fileName, list);
	}

	/** 4. 가계부 삭제 : 양선진*/
	private void deleteMoney() {
		if(!accountBookService.deleteAB(list, fileName)) {
			System.out.println("삭제에 실패했습니다.");
		}

	}
	
	/** 5. 현재 잔액 조회 : 경재*/
	private void currentMoney() {
		if(!accountBookService.printCurrentMoney(list)) {
			System.out.println("가계부를 등록해주세요.");
		};
		
	}
	
}

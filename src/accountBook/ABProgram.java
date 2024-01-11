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
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	
	//서비스 불러오기
	private AccountBookService accountBookService = new AccountBookServiceImp();
	private FileService fileService = new FileServiceImp();
	private PrintService printService = new PrintServiceImp();

	private List<Item> list = new ArrayList<Item>(); 	//수입 지출 내역
	
	
	
	//반복종료 번호
	static final int EXIT = 6;
	static final int UPDATE_EXIT = 7;
	

	@Override
	public void run() {
		int menu = 0;
		
		List<Item> list = fileService.load(fileName);
		
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
			System.out.println("저장"); 
			
		};
		

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
		if(!accountBookService.addAB(list, fileName)) {
			System.out.println("입력에 실패했습니다.");
		}
		
	}

	/** 가계부 조회 : 경재*/
	private void printMoney() {
		if(!accountBookService.printAB(list)) {
			System.out.println("조회에 실패했습니다.");
		}
	}


	/** 가계부 수정 : 손나영 */
	private void updateMoney() {
		//list가 비어있으면 
//		if(!accountBookService.isList()) {
//			System.out.println("작성된 가계부가 없습니다.");
//			return;
//		}
//		
		//전체목록 보여줌
		if(!accountBookService.printAB(list)) {
			System.out.println("조회에 실패했습니다.");
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
//		if(!accountBookService.indexError(index)) {
//			System.out.println("없는 항목입니다.");
//			return;			
//		}
		
		//메뉴 선택
		int menu = 0;
		do {
			printUpdateMenu();
			try {
				menu = scan.nextInt();
				runUpdateMenu(menu, index);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != UPDATE_EXIT);
		
	}

	/** 가계부 수정 : 메뉴출력*/
	private void printUpdateMenu() {
		printService.printUpdateMenu();
	}

	/** 가계부 수정 : 메뉴실행*/
	private void runUpdateMenu(int menu, int index) {
		switch(menu) {
			case 1 :	//수입, 지출 여부 변경
				runUpateInOut(index);
				break;
			case 2 :	//일자수정
				runUpateInDate(index);
				break;
			case 3 :	//금액수정
				runUpateInMoney(index);
				break;
			case 4 :	//잔액 수정
				runUpateInTotalMoney(index);
				break;
			case 5 :	//내역수정				
				runUpateInMemo(index);
				break;
			case 6 : //전체수정
				runUpateInAll(index);	
				break;
			case 7 : System.out.println("뒤로가기");
				break;
			default : throw new InputMismatchException();
		}
		System.out.println("수정을 완료했습니다.");
	}

	/** 가계부 수정 6. 전체수정*/
	private void runUpateInAll(int index) {
		int money = 0, totalMoney = 0, classify;
		Date date = null;
		boolean outMoney = false, inMoney = false;
		String memo = null;
		
		
		//전체 수정 항목 받아오기
		try {
			System.out.print("입금(1)/지출(2) : ");
			classify = scan.nextInt();
			if(classify == 1) {
				inMoney = true;
				outMoney = false;
			}else if(classify == 2) {
				inMoney = false;
				outMoney = true;
			}else {
				System.out.println("잘못된 번호입니다.");
			}
			System.out.print("일자 : ");
			try {
				date = format1.parse(scan.nextLine());
			} catch (ParseException e) {
				System.out.println("잘못된 입력입니다.");
			}
			System.out.print("금액 : ");
			money = scan.nextInt();
			System.out.print("잔액 : ");
			totalMoney = scan.nextInt();
			System.out.print("내역");
			scan.nextLine();
			memo = scan.nextLine();	
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}
		if(!accountBookService.setAB(index, money, totalMoney, date, inMoney, outMoney, memo)) {
			System.out.println("수정에 실패했습니다."); 
		}

	}

	/** 가계부 수정 5. 내역수정*/
	private void runUpateInMemo(int index) {
		String memo="";
		
		try {		
		// 내역받기
		System.out.print("내역");
		scan.nextLine();
		memo = scan.nextLine();	
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}
		
		list.get(index).setMemo(memo);
	}

	/** 가계부 수정 4. 잔액수정*/
	private void runUpateInTotalMoney(int index) {
		int totalMoney=0;
		try {		
			System.out.print("잔액 : ");
			totalMoney = scan.nextInt();
			}
		catch(InputMismatchException e) {
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
		}		
		list.get(index).setTotalMoney(totalMoney);
		
	}

	/** 가계부 수정 3. 금액수정*/
	private void runUpateInMoney(int index) {
		int money =0;
		try {		
			System.out.print("금액 : ");
			money = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		list.get(index).setMoney(money);
	}

	/** 가계부 수정 2. 일자수정*/
	private void runUpateInDate(int index) {
		Date date=null;
		try {		
			System.out.print("일자 : ");
			try {
				date = format1.parse(scan.nextLine());
			} catch (ParseException e) {
				System.out.println("잘못된 입력입니다.");
			}
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}	
		list.get(index).setDate(date);
	}

	/** 가계부 수정 1. 입금/지출 항목수정*/
	private void runUpateInOut(int index) {
		//총액도 같이 변경
		if(list.get(index).isInMoney() && !list.get(index).isOutMoney()) {
			list.get(index).setInMoney(false);
			list.get(index).setOutMoney(true);
		}else if(!list.get(index).isInMoney() && list.get(index).isOutMoney()) {
			list.get(index).setInMoney(true);
			list.get(index).setOutMoney(false);

		}
	}

	/** 가계부 삭제 : 양선진*/
	private void deleteMoney() {
		//순서대로 배열 나열해서 보기
		if(!accountBookService.printAB(list)) {
			System.out.println("조회에 실패했습니다.");
		}
		
		//삭제할 번호 받기
		System.out.print("삭제할 번호 : ");
		//받은 num의 -1을 해야 인덱스 번호
		int index = scan.nextInt() - 1;
		
		//삭제할 번호가 없으면(인덱스가 0보다 작거나, 리스트 사이즈보다 클때)없다고 출력 후 return;
		if(index < 0 || index >= list.size()) {
			System.out.println("없는 번호입니다.");
			return;
		}
		//있으면 정말로 삭제하겠습니까? 문구 출력 후 y/n
		System.out.println("정말로 삭제하겠습니까?(y/n) : ");
		char areYouSure = scan.next().charAt(0);
		if(areYouSure == 'y') {
			//리스트의 index 배열 삭제
			list.remove(index);
			System.out.println("삭제되었습니다.");
			return;
		} else { //y외 다른 문자면 취소
			System.out.println("취소되었습니다.");
			
		}
	}

	
	/** 현재 잔액 조회 : 경재*/
	private void currentMoney() {
		if(!accountBookService.printCurrentMoney(list)) {
			System.out.println("조회에 실패했습니다.");
		};
		
	}
	
}

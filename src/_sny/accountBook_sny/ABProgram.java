package _sny.accountBook_sny;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import word.Word;

public class ABProgram implements Program{
	Scanner scan = new Scanner(System.in);
	static final int EXIT = 6;

	private ABManager abm = new ABManager();
	private Socket socket;

	
	ObjectOutputStream oos;
	ObjectInputStream ois;

	/** 0. run */
	@Override
	public void run() {	
		String ip = "192.168.30.191";
		int port = 5001;
		//서버와 연결
		connectServer(ip, port);
		//서버에서 가계부 정보를 불러옴
		load();
		
		int menu = 0;

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
		save();
	}

	/** 1. 서버와 연결 */
	private void connectServer(String ip, int port) {
		// TODO Auto-generated method stub
		
	}
	
	/** 2. 서버에서 가계부 정보를 불러옴*/
	private void load() {
		abm.load();
	}
	
	/** 3. 메뉴 출력*/
	@Override
	public void printMenu() {
		System.out.println("-------가계부-------");
		System.out.println("1. 가계부 입력");
		System.out.println("2. 가계부 조회");
		System.out.println("3. 가계부 수정");
		System.out.println("4. 가계부 삭제");
		System.out.println("5. 현재 잔액 조회");
		System.out.println("6. 종료");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	/** 4. 메뉴 실행*/
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
	
	/** 4-1. 가계부 입력*/
	private void insertMoney() {
		int menu = 0;

		do {
			printInsertMenu();
			try {
				menu = scan.nextInt();
				runInsertMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != 3);
		
	}

	/** 4-1-2. 가계부 입력 : 메뉴 실행*/
	private void runInsertMenu(int menu) {
		abm.save2();
		int classify, totalMoney, useMoney;
		String dateString, memo;
		AccountBook ab;
		
		switch(menu) {
		case 1 :	
			classify =0; //입금
			//입금내역 입력
			System.out.print("날짜(yyyy-MM-dd) : ");
			dateString = scan.next();
			System.out.print("입금금액 : ");
			useMoney = scan.nextInt();
			System.out.print("내역 : ");
			scan.nextLine();
			memo = scan.nextLine();
			
			//잔액
			if(abm.getList().size() <= 0) {	//만약 등록된 가계부가 없으면 
				totalMoney = useMoney;		// totalMoney는 useMoney와 같다
			}else{		//만약 등록된 가계부가 있다면
				//sort()한 가계부의 최신 내역의 totalMoney + useMoney가 totalMoney가 된다
				int index = abm.getList().size()-1;
				totalMoney = abm.getList().get(index).getTotalMoney() + useMoney;
			}
			
			//입금 내역 정보 추가
			ab = new AccountBook(useMoney, totalMoney, classify, dateString, memo);
			
			abm.insertMoney(ab);
			
			System.out.println("가계부를 입력했습니다.");
	
	
			break;
		case 2 :	//지출내역 입력
			classify = 1;	//지출

			System.out.print("날짜(yyyy-MM-dd) : ");
			dateString = scan.next();
			System.out.print("지출금액 : ");
			useMoney = scan.nextInt();
			System.out.print("내역 : ");
			scan.nextLine();
			memo = scan.nextLine();
			
			if(abm.getList().size() <= 0) {	//만약 등록된 가계부가 없으면 
				totalMoney = -useMoney;		// totalMoney는 useMoney와 같다
			}else {		//만약 등록된 가계부가 있다면
				//sort()한 가계부의 최신 내역의 totalMoney - useMoney가 totalMoney가 된다
				int index = abm.getList().size()-1;
				totalMoney = abm.getList().get(index).getTotalMoney() - useMoney;
			}
			
			//입금 내역 정보 추가
			ab = new AccountBook(useMoney, totalMoney, classify, dateString, memo);
			
			abm.insertMoney(ab);
			
			System.out.println("가계부를 입력했습니다.");
			break;
		case 3 :
			run();
			break;
		default :
			throw new InputMismatchException();
		}
		
		save();
		
		run();
	}

	/** 4-1-1. 가계부 입력 : 메뉴 출력*/
	private void printInsertMenu() {
		System.out.println("----가계부 입력-----");
		System.out.println("1. 입금");
		System.out.println("2. 지출");
		System.out.println("3. 뒤로가기");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	/** 4-2. 가계부 조회*/
	private void printMoney() {
		
		if(abm.getList().size() <= 0) {
			System.out.println("등록된 가계부가 없습니다.");
			return;
		}
		int menu = 0;

		do {
			printPrintMenu();
			try {
				menu = scan.nextInt();
				runPrintMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != 6);
		
	}

	/** 4-2. 가계부 조회 : 메뉴 실행*/
	private void runPrintMenu(int menu) {
		switch(menu) {
		case 1 :	//하루 조회
			break;
		case 2 :	//주간 조회
			break;	
		case 3 :	//월간 조회
			break;
		case 4 :	//연간조회
			break;
		case 5 :	//전체 조회
			abm.printAccountBook();
			break;
		case 6 :
			run();
			break;
		default :
			throw new InputMismatchException();
		}
		run();
	}

	/** 4-2-1. 가계부 조회 : 메뉴 출력*/
	private void printPrintMenu() {
		System.out.println("----가계부 조회-----");
		System.out.println("1. 하루 조회");
		System.out.println("2. 주간 조회");
		System.out.println("3. 월간 조회");
		System.out.println("4. 연간 조회");
		System.out.println("5. 전체 조회");
		System.out.println("6. 뒤로가기");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	/** 4-3. 가계부 수정*/
	private void updateMoney() {		
		System.out.println("----가계부 수정-----");
		if(abm.getList().size() <= 0) {
			System.out.println("등록된 가계부가 없습니다.");
			return;
		}
		abm.printAccountBook();		//전체 목록 보여줌
		
		
		
		System.out.print("어떤 항목을 수정하시겠습니까? : ");
		int num = scan.nextInt() -1 ; 

		if(num<0 || num > (abm.getList().size()-1)) {
			System.out.println("잘못된 순번입니다.");
			return;
		}
		
		int menu = 0;

		do {
			printUpdateMenu();
			try {
				menu = scan.nextInt();
				runUpdateMenu(menu, num);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != 7);
		
		save();
		
	}

	/** 4-3-2. 가계부 수정 : 메뉴실행*/
	private void runUpdateMenu(int menu, int num) {
		int index = num-1;
		
		switch(menu) {
		case 1 :	//수입 지출 여부 전환
			if(abm.getList().get(index).getClassify() == 0) {
				abm.getList().get(index).setClassify(1);
				System.out.println("지출 항목으로 변경되었습니다.");
			}else if(abm.getList().get(index).getClassify() == 1) {
				abm.getList().get(index).setClassify(0);
				System.out.println("수입 항목으로 변경되었습니다.");
			}
			break;
		case 2 :	//년월일 변경
			System.out.print("날짜(yyyy-MM-dd) : ");
			String dateString = scan.next();
			
			abm.getList().get(index).setDateString(dateString);
			System.out.println("날짜가 변경되었습니다.");
			break;	
		case 3 :	//사용금액변경
			System.out.print("금액 : ");
			int useMoney = scan.nextInt();
			
			abm.getList().get(index).setUseMoney(useMoney);
			System.out.println("금액이 변경되었습니다.");
			break;
		case 4 :	//잔액변경
			System.out.print("금액 : ");
			int totalMoney = scan.nextInt();
			
			abm.getList().get(index).setTotalMoney(totalMoney);
			System.out.println("잔액이 변경되었습니다.");
			break;
		case 5 :	//내역변경
			System.out.print("내역 : ");
			scan.nextLine();
			String memo = scan.nextLine();
			
			abm.getList().get(index).setMemo(memo);
			System.out.println("내역이 변경되었습니다.");
			break;
		case 6 :	//전체수정
			totalUpdate(num);
			break;
		case 7 :	//뒤로가기
			run();
			break;
		default :
			throw new InputMismatchException();
		}
		
	}

	/** 4-3-2-6. 가계부 수정 : 6. 전체수정*/
	private void totalUpdate(int num) {
		int menu = 0;

		do {
			printTotalUpdateMenu();
			try {
				menu = scan.nextInt();
				runTotalUpdateMenu(menu, num);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != 3);

	}

	/** 4-3-2-6-2. 가계부 수정 : 6. 전체수정 메뉴실행*/
	private void runTotalUpdateMenu(int menu, int num) {
		int index = num-1;
		int classify, totalMoney, useMoney;
		String dateString, memo;
		AccountBook ab;
		
		switch(menu) {
		case 1 :	
			classify =0; //입금
			//입금내역 입력
			System.out.print("날짜(yyyy-MM-dd) : ");
			dateString = scan.next();
			System.out.print("입금금액 : ");
			useMoney = scan.nextInt();
			System.out.print("잔액 : ");
			totalMoney = scan.nextInt();
			System.out.print("내역 : ");
			scan.nextLine();
			memo = scan.nextLine();
			
			//입금 내역 정보 추가
			ab = new AccountBook(useMoney, totalMoney, classify, dateString, memo);
			
			abm.updateAB(num, ab);
			System.out.println("가계부를 수정했습니다.");
			
			break;
		case 2 :	//지출내역 입력
			classify = 1;	//지출

			System.out.print("날짜(yyyy-MM-dd) : ");
			dateString = scan.next();
			System.out.print("지출금액 : ");
			useMoney = scan.nextInt();
			System.out.print("잔액 : ");
			totalMoney = scan.nextInt();
			System.out.print("내역 : ");
			scan.nextLine();
			memo = scan.nextLine();
			
			//입금 내역 정보 추가
			ab = new AccountBook(useMoney, totalMoney, classify, dateString, memo);
			
			abm.updateAB(num, ab);
			System.out.println("가계부를 수정했습니다.");
			break;
		case 3 :
			run();
			break;
		default :
			throw new InputMismatchException();
		}
			
		sendUpdataAB(abm.getList().get(index));
		
	}

	/** 4-3-2-6-2. (1)*/
	private void sendUpdataAB(AccountBook ab) {
		try {
			oos.writeUTF("UPDATE");
			oos.flush();
			oos.writeObject(ab);
			oos.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 4-3-2-6-1. 가계부 수정 : 6. 전체수정 메뉴출력*/
	private void printTotalUpdateMenu() {
		System.out.println("1. 입금");
		System.out.println("2. 지출");
		System.out.println("3. 뒤로가기");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	/** 4-3-1. 가계부 수정 : 메뉴 출력*/
	private void printUpdateMenu() {
		System.out.println("----가계부 수정-----");
		System.out.println("1. 수입/지출 내역 변경");
		System.out.println("2. 일자 변경");
		System.out.println("3. 금액 변경");
		System.out.println("4. 잔액 변경");
		System.out.println("5. 내역 변경");
		System.out.println("6. 전체 수정");
		System.out.println("7. 뒤로가기");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
		
	}

	/** 4-4. 가계부 삭제*/
	private void deleteMoney() {
		System.out.println("----가계부 삭제-----");
		if(abm.getList().size() <= 0) {
			System.out.println("등록된 가계부가 없습니다.");
			return;
		}
		
		abm.printAccountBook();		//전체 목록 보여줌
		
		System.out.println("어떤 항목을 삭제하시겠습니까? : ");
		int num = scan.nextInt();
		
		if(num<0 || num > (abm.getList().size()-1)) {
			System.out.println("잘못된 순번입니다.");
			return;
		}
		
		int index = num -1;
		String ClassifyS ="";
		
		//삭제할 항목 보여주기
		if(abm.getList().get(index).getClassify()==0) {
			ClassifyS ="수입";
		}else if(abm.getList().get(index).getClassify() ==1) {
			ClassifyS = "지출";
		}
		
		System.out.println("  " + num + "       "
						+ ClassifyS + "    " +abm.getList().get(index).getDateString() + "      "
						+ abm.getList().get(index).getUseMoney() 
						+ "      " + abm.getList().get(index).getTotalMoney() 
						+ "    " + abm.getList().get(index).getMemo());
		
		System.out.println("해당 항목을 정말 삭제하시겠습니까?(네(1) 아니요(2))");
		int intention = scan.nextInt();
		
		try {
			if(intention == 1) {
				abm.getList().remove(index);
				System.out.println("단어가 삭제 되었습니다.");
			}else if(intention == 2) {
				run();
			}else {
				System.out.println("잘못된 번호입니다.");
			}
		}catch (InputMismatchException e) {
			System.out.println("잘못된 메뉴입니다.");
			scan.nextLine();//잘못 입력된 값을 문자열로 가져와서 버림
		}
		save();		
	}
	
	/** 4-5. 현재 잔액 조회*/
	private void currentMoney() {
		int index = abm.getList().size() -1;
		
		if(abm.getList().size() <= 0) {
			System.out.println("등록된 가계부가 없습니다.");
			return;
		}
		
		System.out.println("현재 잔액은 " + abm.getList().get(index).getTotalMoney() + "입니다.");
	}
	
	/** 5. 저장하기*/
	private void save() {
		abm.save();
	}

	
}

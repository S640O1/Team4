package _ysj.accountBook_ysj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ABProgram implements Program{
	
	Scanner sc = new Scanner(System.in);
	private List<AccountBook> list = new ArrayList<AccountBook>();
	ABManager ab = new ABManager();
	static final int EXIT = 6;
	static String fileName = "src/_ysj/accountBook_ysj/accountBookList.txt";

	@Override
	public void run() {
		int menu = 0;
		
		load(fileName);
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
		save(fileName);
		}

	private void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			list = (List<AccountBook>)ois.readObject();
			System.out.println("가계부를 불러왔습니다.");
		} catch (IOException e) {
			System.out.println("불러오기를 실패했습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("없는 클래스입니다.");
		}
	}

	private void save(String fileName) {
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(list);
			System.out.println("저장했습니다.");
		} catch (IOException e) {
			System.out.println("저장에 실패했습니다.");
		}
		
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
			ab.insertAccountBook();
			break;
		case 2 :
			ab.printAccountBook();	
			break;
		case 3 :
			ab.updateAccountBook();		
			break;
		case 4 :
			ab.deleteAccountBook();		
			break;
		case 5 :					
			ab.currentAccountBook();	
			break;
		case 6 : System.out.println("프로그램을 종료합니다.");
			break;
		default :
			throw new InputMismatchException();
		}
	}
	
}

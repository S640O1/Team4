package word_sgj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import word_sgj.Homework;

public class HomeworkEx1 {

	static Scanner scan = new Scanner(System.in);
	private List<Homework> list = new ArrayList<Homework>();
	
	public static void main(String[] args) {
		/* 영어 단어장을 관리하는 프로그램을 작성하세요.
		 * 기한은 1/5까지 github에 업로드 후 강사에게 공유(단톡방)
		 * 
		 * - 한 단어에 뜻이 여러개 있을 수 있음
		 * - 단어, 품사, 뜻을 관리
		 * - 단어 추가
		 * - 단어 수정
		 * - 단어 삭제
		 * - 뜻 추가
		 * - 뜻 수정
		 * - 뜻 삭제
		 * - 단어 조회
		 * 
		 * - 기타 추가 기능을 구현해도 됨
		 * - 등록된 단어장에서 랜덤으로 문제가 나오고 맞추는 기능
		 * - 오답 노트를 관리하는 기능
		 * - 많이 조회한 단어를 확인하는 기능
		 */
		
		
		//반복문
		int menu;
		do {
			//메뉴출력
			printMenu(); // 메서드 만들어서 선언
			
			
		
			//메뉴 선택
			menu = scan.nextInt();
			
			//선택한 기능 실행
			runMenu(menu);
			
			}while(menu != 3);//프로그램 종료는 3번
	

		
	}

	// 아래는 그동안했었던 방식 / 다른 방식 day15 student 참고하여 진행
	// interface 만들어서 Overriding
	// implements 파일명
	
	private static void runMenu(int menu) {
		switch(menu) {
		case 1:
			
			break;
			
		case 2:
			wordsManager();		
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	
	/** 단어 관리를 위한 메서드입니다.
	 *  단어 추가, 수정, 삭제 기능이 있습니다.
	 *  뜻 추가, 수정, 삭제 기능이 있습니다.
	 */
	private static void wordsManager() {
		//단어 관리 메뉴를 출력
		printWordsMenu();
		//단어 관리 메뉴를 선택
		int menu = scan.nextInt();		
		//단어 관리 메뉴를 실행
		runWordsMenu(menu);		
	}

	private static void printWordsMenu() {
		System.out.println("----단어 관리----");
		System.out.println("1. 단어 추가");
		System.out.println("2. 단어 수정");
		System.out.println("3. 단어 삭제");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}

	private static void runWordsMenu(int menu) {
		switch(menu){
		case 1:
			//단어를 입력
			insertWords();
			
			//입력받은 정보로 인스턴스를 생성 wd
			Homework wd = new Homework(words, wordClass, meaning);
			
			//wd가 리스트에 있는지 확인, 없으면 추가
			
			//있으면 이미 등록 됐다고 알람
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			System.out.println("잘못 입력하였습니다.");
		}
	}

	private static void insertWords() {
		//단어, 품사, 뜻을 입력
		System.out.print("단어 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		String words = scan.nextLine(); 
		
		System.out.print("품사 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		String wordClass = scan.nextLine(); 
		
		System.out.print("뜻 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		String meaning = scan.nextLine(); 
		
		
		//입력받은 정보로 인스턴스를 생성 std
		Homework wd = new Homework(words, wordClass, meaning);
		
		if(!List.contains(wd)) {
			list.add(wd);
			System.out.println("단어를 등록했습니다.");
			return;
		}
		//있으면 이미 등록됐다고 알림
		System.out.println("이미 등록된 단어입니다.");
	}

	private static void printMenu() {
		System.out.println("----영어 단어장----");
		System.out.println("1. 단어 검색");
		System.out.println("2. 단어 관리");
		System.out.println("3. 프로그램 종료");
		System.out.println("--------------");
		System.out.print("메뉴 선택 : ");
	}

}

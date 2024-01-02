package word_sgj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HomeworkEx1Program implements HProgram {
	
	private Scanner scan = new Scanner(System.in);
	private final int EXIT = 3;
	private List<Homework> list = new ArrayList<Homework>();

	
	@Override
	public void run() {
		//반복
		int menu = 0;
		do {
			//메뉴 출력
			printMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				
				//메뉴 실행
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
	}

	
	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			
			break;
		case 2:
			wordsManager();
			break;
		case 3:
			System.out.println("프로그램 종료.");
			break;
		default:
			throw new InputMismatchException();
		}
	}
	/** 단어 관리를 위한 메서드입니다.
	 *  단어 추가, 수정, 삭제 기능이 있습니다.
	 *  뜻 추가, 수정, 삭제 기능이 있습니다.
	 */
	private void wordsManager() {
		//단어 관리 메뉴를 출력
		printWordsMenu();
		//단어 관리 메뉴를 선택
		int menu = scan.nextInt();
		//단어 관리 메뉴를 실행
		runWordsMenu(menu);
	}


	private void printWordsMenu() {
		System.out.println("----단어 관리----");
		System.out.println("1. 단어 추가");
		System.out.println("2. 단어 수정");
		System.out.println("3. 단어 삭제");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}


	private void runWordsMenu(int menu) {
		switch(menu) {
		case 1:
			insertWords();
			break;
		case 2:
			updateWords();
			break;
		case 3:
			break;
		default:
			throw new InputMismatchException();
		}
	}


	private void updateWords() {
		//기존 단어 정보를 입력
		System.out.print("단어 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		String words = scan.nextLine(); 
		
		System.out.print("품사 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		String wordClass = scan.nextLine(); 
		
		System.out.print("뜻 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		String meaning = scan.nextLine();  
		
		//단어정보 인스턴스를 생성
		Homework wd = new Homework(words, wordClass, meaning);
		
		//기존 단어정보 인스턴스를 가져옴
		int index = list.indexOf(wd);
		
		//없으면 없다고 하고 종료
		if(index == -1) {
			System.out.println("등록되지 않은 단어입니다.");
			return;
		}
		
		wd = list.get(index);//기존 단어 정보 
		
		
		//수정할 학년, 반, 번호, 이름을 입력
		System.out.print("수정할 단어 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		words = scan.nextLine(); 
		
		System.out.print("수정할 품사 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		wordClass = scan.nextLine(); 
		
		System.out.print("수정할 뜻 : ");
		scan.nextLine(); //아래 nextlint썼는데 엔터 처리를 위하여 필요함
		meaning = scan.nextLine(); 
		
		//수정할 단어정보 인스턴스를 생성
		Homework newWd = new Homework(words, wordClass, meaning);
		
		//수정할 단어 정보가 등록됐는지 확인해서 있으면 알림 후 종료
		if(list.contains(newWd)) {
			System.out.println("이미 등록된 단어 정보입니다.");
			return;
		}
		
		//수정할 단어 인스턴스에 기존 정보들을 업데이트하고
		newWd.setWords(wd.getWords());
		newWd.setWordClass(wd.getWordClass());
		newWd.setMeaning(wd.getMeaning());
		
		//기존 단어 정보를 삭제
		list.remove(index);
		
		//수정할 단어 정보를 리스트에 추가
		list.add(newWd);
		
		System.out.println("단어 정보가 수정되었습니다.");
	}


	private void insertWords() {
		//이미 등록된 단어도 중복 가능
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
		//입력받은 정보로 인스턴스(wd 라고 하겠음)를 생성
		//wd가 리스트에 있는지 확인해서 없으면 추가
		
		//입력받은 정보로 인스턴스를 생성 std
		Homework wd = new Homework(words, wordClass, meaning);
		
		//wd가 리스트에 있는지 확인해서 없으면 추가
		if(!list.contains(wd)) {
			list.add(wd);
			System.out.println("단어를 등록했습니다.");
			return;
		}
		//있으면 이미 등록됐다고 알림
		System.out.println("이미 등록된 단어입니다.");
	}


	@Override
	public void printMenu() {
		System.out.println("----영어 단어장----");
		System.out.println("1. 단어 검색");
		System.out.println("2. 단어 관리");
		System.out.println("3. 프로그램 종료");
		System.out.println("--------------");
		System.out.print("메뉴 선택 : ");
	}

}

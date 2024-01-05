package _ysj.word_ysj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;


public class WordProgram implements Program {

	private static Scanner sc = new Scanner(System.in);
	private static List<Word> list = new ArrayList<Word>();
	
	int menu = 0;
	@Override
	public void run() {
		do {
		printMenu();
		try {
		menu = sc.nextInt();
		runMenu(menu);
		} catch(InputMismatchException e) {
			System.out.println("없는 메뉴입니다.");
		}
		}while(menu != 5);
	}
	
	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1 : insertWord();
			break;
			
		case 2 : updateWord();
			break;
			
		case 3 : deleteWord();
			break;
			
		case 4 : searchWord();
			break;
			
		case 5 : System.out.println("프로그램을 종료합니다.");
			break;
			
		default : throw new InputMismatchException();
		}
		
	}
	
	//4. 단어 조회
	//**단어가 등록되지 않을 시 "등록된 단어가 없습니다." 라는 메세지 출력
	private void searchWord() {
		//단어 리스트 조회
		for(Word tmp : list) {
			tmp.print();
		}
		//단어 입력
		System.out.print("검색할 단어 입력 : ");
		sc.nextLine();
		String word = sc.nextLine();
		Word tmp = new Word(word, "");
		//같은 단어가 없으면 등록된 단어가 없다는 메세지 출력 후 종료(return)
		if(!list.contains(tmp)) {
			System.out.println("등록된 단어가 없습니다.");
		}
		//같은 단어가 있으면 출력 후 종료 -> **안되는 이유?
		if(list.contains(tmp)) {
			//list.print();
		}
	}

	//3. 단어 삭제
	private void deleteWord() {
		System.out.print("단어 입력 : ");
		sc.nextLine();
		String word = sc.nextLine();
		System.out.print("뜻 입력 : ");
		String mean = sc.nextLine();
		
		//배열에 없는 단어일때
		Word tmp = new Word(word, mean);
		int index = list.indexOf(tmp);
		if(index == -1) {
			System.out.println("등록되지 않은 단어입니다.");
			return;
		}
		
		//배열에 있는 단어일때 삭제
		if(list.remove(tmp)) {
			System.out.println("단어가 삭제되었습니다.");
		}
	}

	//2. 단어 수정
	private void updateWord() {
		//기존 단어, 뜻(tmp) 입력
		System.out.print("단어 입력 : ");
		sc.nextLine();
		String word = sc.nextLine();
		System.out.print("뜻 입력 : ");
		String mean = sc.nextLine();
		Word tmp = new Word(word, mean);
		
		//tmp 인스턴스의 index
		int index = list.indexOf(tmp);
		if(index == -1) {
			System.out.println("등록되지 않은 단어입니다.");
			return;
		}
		
		//수정할 단어, 뜻 입력
		System.out.print("수정할 단어 입력 : ");
		word = sc.nextLine();
		System.out.print("수정할 뜻 입력 : ");
		mean = sc.nextLine();
		Word newWord = new Word(word, mean);
		
		//수정할 단어 정보가 등록됬는지 확인해서 있는 단어이면 알림 후 종료
		if(list.contains(newWord)) {
			System.out.println("이미 등록된 단어라서 수정할 수 없습니다.");
			return;
		}
		
		//기존 단어, 뜻(tmp) 삭제 후 + 새 단어, 뜻(newWord) 추가
		list.remove(tmp);
		list.add(newWord);
		
		System.out.println("단어가 수정되었습니다.");
		System.out.println(list);	//@Data로 인한 @toString으로 나옴
	}

	//1. 단어 추가
	private void insertWord() {
		System.out.print("단어 입력 : ");
		sc.nextLine();
		String word = sc.nextLine();
		System.out.print("뜻 입력 : ");
		String mean = sc.nextLine();
		
		//없는 단어, 뜻이면 추가
		Word tmp = new Word(word, mean);
		if(!list.contains(tmp)) {
			list.add(tmp);
			System.out.println("단어 추가 완료");
			return;
		}
		System.out.println("이미 등록된 단어 입니다.");
	}

	@Override
	public void printMenu() {
		System.out.println("----메뉴----");
		System.out.println("1. 단어 추가");
		System.out.println("2. 단어 수정");
		System.out.println("3. 단어 삭제");
		System.out.println("4. 단어 조회");
		System.out.println("5. 종료");
		System.out.println("-----------");
		System.out.print("메뉴 선택 : ");
	}
}

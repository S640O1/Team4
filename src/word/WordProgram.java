package word;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import word_saj.Program;
import word_saj.Word;

public class WordProgram implements Program{
	
	private Scanner scan = new Scanner(System.in);
	static final int EXIT = 5;
	private List<Word> list = new ArrayList<Word>();
	private List<String> mean = new ArrayList<String>();
	
	@Override
	public void printMenu() {

		System.out.println("---------------");
		System.out.println("메뉴");
		System.out.println("1. 단어 등록");
		System.out.println("2. 수정 관리");
		System.out.println("3. 단어 삭제");
		System.out.println("4. 단어 조회");
		System.out.println("5. 종료");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
		
	}
	
	@Override
	public void run() {

		int menu = 0;
		String fileName = "src/word/wordList.txt";
		load(fileName);
		
		do {
			// 메뉴 출력
			System.out.println();
			printMenu();
			
			//메뉴 입력
			try {
				menu = scan.nextInt();
				System.out.println();
				
				// 메뉴 실행
				runMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
			
		} while (menu != EXIT);
		
		save(fileName);
		
	}

	

	private void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<Word>)ois.readObject();
			System.out.println("단어장을 불러왔습니다.");

		}catch (Exception e) {
			System.out.println("불러오기에 실패했습니다.");
		}
		
	}

	private void save(String fileName) {
		//게시글을 파일에 저장
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
		}
			catch (IOException e) {
				System.out.println("저장에 실패했습니다.");
			}
		
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1 :
			insertManager();		// 등록은 단어, 뜻, 품사를 한번에 입력해야 등록되도록 구현
			break;
		case 2 :
			updateManager();		// 수정은 단어, 뜻, 품사를 각각 수정 가능하도록 구현
			break;
		case 3 :
			deleteManager();		// 삭제는 단어, 뜻, 품사를 각각 수정 가능하도록 구현
			break;
		case 4 :
			printManager();			// 조회는 단어, 뜻, 품사가 한번에 보이도록 구현
			break;
		case 5 :
			break;
		default :
			throw new InputMismatchException();
		}
	}

	

	private void insertManager() {
		
		// 등록할 단어, 품사, 뜻을 입력 받음
		System.out.print("등록할 단어를 입력하세요 : ");
		String word = scan.next();
		
		
		System.out.print("등록할 품사를 입력하세요 : ");
		String speechOfPart = scan.next();
		
		String tmp = "";
	

		do {
			System.out.print("등록할 뜻을 입력하세요(추가 뜻이 없을 시 1) : ");
			
			//메뉴 입력
			try {
				tmp = scan.next();
				if(!tmp.equals("1")) {
					mean.add(tmp);					
				}
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
			
		} while (!tmp.equals("1"));
		
		

		// 입력받은 정보로 인스턴스를 생성 wds
		Word wds = new Word(word, speechOfPart, mean);
		
		// wds가 리스트에 있는지 없는지 확인 후 없으면 추가 a.equals(b)를 이용
		if (!list.contains(wds)) {			// 품사나 뜻은 같아도 되는데, 단어는 중복 등록 X  -> 코드가 안 먹음 해결 해야함
			list.add(wds);
			System.out.println("단어를 등록했습니다."); 
			return;
		}
		
		//이미 있으면 있다고 알림
		System.out.println("이미 등록된 단어입니다.");
	}
	
	
	private void updateManager() {
		// 기존 단어 입력
		System.out.print("단어를 입력하세요 : ");
		String word = scan.next();
		
		String speechOfPart = "";
		ArrayList<String> mean = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getWord().equals(word)) {
				
				System.out.println("단어 : " + list.get(i).getWord() + ", 뜻 : " + list.get(i).getMean() + ", 품사 : " + list.get(i).getSpeechOfPart());
				speechOfPart=list.get(i).getSpeechOfPart();
				mean = (ArrayList<String>) list.get(i).getMean();
				mean.clear();
				break;
			}
		}
		
		System.out.println("===== 수정 =====");
		System.out.println("1. 단어 수정");
		System.out.println("2. 뜻 수정");
		System.out.println("3. 품사 수정");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
		
		// 메뉴선택
		int menu = scan.nextInt();
					
		
		Word wds = new Word(word,speechOfPart, mean);
		// 기존 단어와 일치하는 단어 인스턴스를 가져옴
		int index = list.indexOf(wds);
		
		// 없으면 없다고 하고 종료
		if (index == -1) {
			System.out.println("등록되지 않은 단어입니다.");
			return;
		}
					
		switch(menu) {
		case 1 :	
			System.out.println("수정할 단어 : ");
			word = scan.next();
			
			list.get(index).setWord(word);
			
			break;
		case 2 :
			String tmp = "";

			do {
				System.out.print("수정할 뜻을 입력하세요(추가 뜻이 없을 시 1) : ");
				
				//메뉴 입력
				try {
					tmp = scan.next();
					if(!tmp.equals("1")) {
						mean.add(tmp);					
					}
				} catch (InputMismatchException e){
					System.out.println("잘못된 메뉴입니다.");
					scan.nextLine();
				}
				
			} while (!tmp.equals("1"));
			
			
			list.get(index).setMean(mean);
			
			break;
		case 3 :
			System.out.println("수정할 품사 : ");
			speechOfPart = scan.next();
			
			list.get(index).setSpeechOfPart(speechOfPart);
			break;
		default :
			throw new InputMismatchException();
		}
		System.out.println("수정이 완료되었습니다.");

	}

	private void deleteManager() {
		// 삭제할 단어, 뜻, 품사 입력
		System.out.print("단어를 입력하세요 : ");
		String word = scan.next();
		
		// 단어 인스턴스 생성
		Word wds = new Word(word, "", mean);
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getWord().equals(word)) {
				list.remove(i);
				System.out.println("단어가 삭제 되었습니다.");
				break;
			}
		}
		

		System.out.println("등록되지 않은 단어입니다.");

		
	}
	
	private void printManager() {
		System.out.println("========== 등록된 단어 ==========");
		for (Word wds : list) {
			System.out.println("단어 : " + wds.getWord() + ", 뜻 : " + wds.getMean() + ", 품사 : " + wds.getSpeechOfPart());
		}
	}
}
	
	

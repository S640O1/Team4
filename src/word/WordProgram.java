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

public class WordProgram implements Program{
	
	private Scanner scan = new Scanner(System.in);
	static final int EXIT = 5;
	static String fileName = "src/word/wordList.txt";
	private List<Word> list = new ArrayList<Word>();
	
	
	/** 0. 메인 */
	@Override
	public void run() {
		
		int menu = 0;
		
		load(fileName);
		
		do {
			// 메뉴 출력
			printMenu();
			
			//메뉴 입력
			try {
				menu = scan.nextInt();

				// 메뉴 실행
				runMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
			
		} while (menu != EXIT);
		
		save(fileName);
		
	}
	
	
	/** 0. 메인 메뉴 출력 */
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
	
	/** 1. 불러오기 */
	private void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<Word>)ois.readObject();
			System.out.println("단어장을 불러왔습니다.");

		}catch (Exception e) {
			System.out.println("불러오기에 실패했습니다.");
		}
		
	}
	/** 1. 저장하기 */
	private void save(String fileName) {
		//게시글을 파일에 저장
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
			System.out.println("저장을 성공했습니다.");
		}
		catch (IOException e) {
			System.out.println("저장에 실패했습니다.");
		}
		
	}

	/** 1. 메인 메뉴 실행 */
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

	/** 1-1. 단어 등록 */
	private void insertManager() {

		// 등록할 단어, 품사, 뜻을 입력 받음
		System.out.print("등록할 단어를 입력하세요 : ");
		String word = scan.next();
		
		for(int i=0; i<list.size(); i++) {
			//만약 동일한 단어가 있다면
			if(list.get(i).getWord().equals(word)) {
				//이미 있으면 있다고 알림
				System.out.println("이미 등록된 단어입니다.");
				return;
				}
		}
		
		// 없으면 품사와 뜻을 등록
		System.out.print("등록할 품사를 입력하세요 : ");
		String speechOfPart = scan.next();
		
		String tmp = "";
	
		List<String> mean = new ArrayList<String>();
		

		
		do {
			System.out.print("등록할 뜻을 입력하세요(추가 뜻이 없을 시 1) : ");
			
			//메뉴 입력
			try {
				tmp = scan.next();
				
				//만약 동일한 뜻이 포함되어있다면
				if(mean.contains(tmp)) {
					//이미 있으면 있다고 알림
					System.out.println("이미 등록된 뜻입니다.");
				}
				//동일 뜻이 포함되지 않고, "1"이 아니라면
				else if(!tmp.equals("1")) {
					//뜻을 mean에 추가
					mean.add(tmp);					
				}
				
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
			
		} while (!tmp.equals("1"));
		
		
		// 입력받은 정보로 인스턴스를 생성 wds
		Word wds = new Word(word, speechOfPart, mean);
		
		//단어 등록
		list.add(wds);
		System.out.println("단어를 등록했습니다."); 
	}
	
	/** 1-2. 수정 */
	private void updateManager() {
		// 기존 단어 입력
		System.out.print("단어를 입력하세요 : ");
		String word = scan.next();
		
		int index = -1;
		
		for(int i=0; i<list.size(); i++) {
			//만약 동일한 단어가 있다면
			if(list.get(i).getWord().equals(word)) {
				//해당 인덱스 값을 저장
				index = i;
				break;
				}
		}
		// 없으면 없다고 하고 종료
		if(index == -1) {
			System.out.println("등록되지 않은 단어입니다.");	
			return;
		}
		

		System.out.println("단어 : " + list.get(index).getWord() + ", 뜻 : " + list.get(index).getMean() + ", 품사 : " + list.get(index).getSpeechOfPart());
		String speechOfPart=""; 
		List<String> mean = new ArrayList<String>();
		//의미 지우기
		mean.clear();

		
		System.out.println("===== 수정 =====");
		System.out.println("1. 단어 수정");
		System.out.println("2. 뜻 수정");
		System.out.println("3. 품사 수정");
		System.out.println("4. 뒤로가기");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
		
		// 메뉴선택
		int menu = scan.nextInt();
					
					
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
					//만약 동일한 뜻이 포함되어있다면
					if(mean.contains(tmp)) {
						//이미 있으면 있다고 알림
						System.out.println("이미 등록된 뜻입니다.");
					}
					//동일 뜻이 포함되지 않고, "1"이 아니라면
					else if(!tmp.equals("1")) {
						//뜻을 mean에 추가
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
		case 4:
			updateManager();
			break;
		default :
			
			throw new InputMismatchException();
		}
		System.out.println("수정이 완료되었습니다.");

	}

	/** 1-3. 삭제 */
	private void deleteManager() {
		// 삭제할 단어 입력
		System.out.print("단어를 입력하세요 : ");
		String word = scan.next();
		
		List<String> mean = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getWord().equals(word)) {
				list.remove(i);
				System.out.println("단어가 삭제 되었습니다.");
				break;
			}
		}
		

		System.out.println("등록되지 않은 단어입니다.");
		
	}
	
	/** 1-4. 조회 */
	private void printManager() {
		System.out.println("===== 조회 =====");
		System.out.println("1. 단어 조회");
		System.out.println("2. 전체 조회");
		System.out.println("3. 뒤로가기");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
		
		// 메뉴선택
		int menu = scan.nextInt();
					
		switch(menu) {
		case 1 :
			System.out.print("조회할 단어 : ");
			String word= scan.next();
			
			for(int i=0; i<list.size(); i++) {
				//만약 조회할 단어와 같다면
				if(list.get(i).getWord().equals(word)) {
					//조회 출력
					System.out.println("단어 : " +  list.get(i).getWord()+ ", 뜻 : " + list.get(i).getMean() + ", 품사 : " + list.get(i).getSpeechOfPart());
					break;
				}
			}
			System.out.println("없는 단어입니다.");
			break;
		case 2 :
			System.out.println("========== 등록된 단어 ==========");
			for (Word wds : list) {
				System.out.println("단어 : " + wds.getWord() + ", 뜻 : " + wds.getMean() + ", 품사 : " + wds.getSpeechOfPart());
			}
			break;
		case 3 :
			run();
			break;
		default :
			throw new InputMismatchException();
		}
		
		

	}
}
	
	

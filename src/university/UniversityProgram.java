package university;

import java.util.InputMismatchException;
import java.util.Scanner;

import program.Program;
import university.service.FileService;
import university.service.FileServiceImp;
import university.service.LectureService;
import university.service.LectureServiceImp;
import university.service.PrintService;
import university.service.PrintServiceImp;
import university.service.ProfessorService;
import university.service.ProfessorServiceImp;

public class UniversityProgram implements Program {
	private Scanner scan = new Scanner(System.in);
	static String fileName = "src/teamProject1/university/universityList.txt";
	
	//메뉴 종료 상수
	static final int EXIT = 7;
	
	//서비스 목록
	private PrintService printService = new PrintServiceImp();
	private FileService fileService = new FileServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	
	//대학교 정보
	private University university;
	
	@Override
	public void run() {
		int menu = 0;
		
		university = fileService.load(fileName);
		
		if(university == null) {
			
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
		//저장하기
		if(fileService.save(fileName, university)) {
			System.out.println("저장이 완료되었습니다.");
		}else {
			System.out.println("저장에 실패했습니다.");
		}
	}

	@Override
	public void printMenu() {
		printService.printMainMenu();
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1: 
			System.out.println("학과 관리 서비스 예정");
		break;
		case 2: 
			System.out.println("교수 관리 서비스 예정");
			break;
		case 3: 
			System.out.println("학생 관리 서비스 예정");
			break;
		case 4: 
			LectureManager();
			System.out.println("강의 관리 서비스 예정");
			break;
		case 5: 
			System.out.println("수강 관리 서비스 예정");
			break;
		case 6: 
			System.out.println("조회 서비스 예정");
			break;
		case 7:
			System.out.println("프로그램을 종료합니다.");
			break;
		default : 
			throw new InputMismatchException();
		}
	}

	/** 4. 강의 관리*/
	private void LectureManager() {
		
		
	}

}

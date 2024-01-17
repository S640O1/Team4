package university;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
	public static Scanner scan = new Scanner(System.in);
	static String professorFileName = "src/university/professorList.txt";
	static String lectureFileName = "src/university/lectureList.txt";
	static String studentFileName = "src/university/studentList.txt";
	static String departmentFileName = "src/university/departmentList.txt";
	
	//메뉴 종료 상수
	static final int EXIT = 7;
	static final int LECTURE_EXIT =4;
	
	//서비스 목록
	private PrintService printService = new PrintServiceImp();
	private FileService fileService = new FileServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	
	//대학교 정보
	private List<Lecture> lList = new ArrayList<Lecture>();
	
	@Override
	public void run() {
		int menu = 0;
		
		List<Lecture> tmp = fileService.lLoad(lectureFileName);
		if (!(tmp == null)) {
			lList.addAll(tmp);
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
		//저장하기*4
//		if(fileService.save()) {
//			System.out.println("저장이 완료되었습니다.");
//		}else {
//			System.out.println("저장에 실패했습니다.");
//		}
	}

	@Override
	public void printMenu() {
		printService.printMainMenu();
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1: 
			departmentManage();
			System.out.println("학과 관리 서비스 예정");
		break;
		case 2: 
			professorManage();
			System.out.println("교수 관리 서비스 예정");
			break;
		case 3: 
			studentManage();
			System.out.println("학생 관리 서비스 예정");
			break;
		case 4: 
			LectureManager();

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

	/** 1. 학과 관리 : 신경재 */
	private void departmentManage() {
		
	}

	/** 2. 교수 관리 : 손나영 */
	private void professorManage() {
		
	}

	/** 3. 학생 관리 : 양선진 */
	private void studentManage() {
		
	}

	/** 4. 강의 관리 : 심아진 */
	private void LectureManager() {
		
		int menu = 0;
		
		do {
			lecturePrintMenu();
			
			try {
				menu = scan.nextInt();
				runLectureMenu(menu);
				
				
			}catch (InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.22");
				scan.nextLine();
			}
			
		} while (menu != LECTURE_EXIT);
		
	}
	
	/** 4. 강의 관리 - 강의 관리 메뉴 출력 : 심아진*/
	private void lecturePrintMenu() {
		printService.printLectureMenu();
	}

	
	/** 4. 강의 관리 - 메뉴 실행 : 심아진*/
	private void runLectureMenu(int menu) {
		switch(menu) {
		case 1 :
			lList.addAll(lectureService.addLecture(lList, lectureFileName));
			fileService.lSave(lectureFileName, lList);
			
			break;
		case 2 :
			
			if (!lectureService.printLecture(lList, lectureFileName)) {
				
			}
			break;
		case 3 :
			System.out.println("강의 삭제");
			break;
		case 4 :
			System.out.println("뒤로가기");
			break;
		default :  throw new InputMismatchException();
		}

	}

}

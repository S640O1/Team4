package university;

import java.util.ArrayList;
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
import university.service.StudentService;
import university.service.StudentServiceImp;

public class UniversityProgram implements Program {
	private Scanner scan = new Scanner(System.in);
	static String fileName = "src/teamProject1/university/universityList.txt";
	
	//메뉴 종료 상수
	static final int EXIT = 7;
	private int STUDENT_EXIT = 4;	//뒤로 가기
	
	//서비스 목록
	private PrintService printService = new PrintServiceImp();
	private FileService fileService = new FileServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	private StudentService studentService = new StudentServiceImp();
	
	//대학교 정보
	ArrayList<Student> sList = new ArrayList<Student>();
	Student student;
	
	
	@Override
	public void run() {
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
		//저장하기
//		if(fileService.save(fileName, university)) {
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
			System.out.println("학과 관리 서비스 예정");
		break;
		case 2: 
			System.out.println("교수 관리 서비스 예정");
			break;
		case 3: 
			printStudentMenu();
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

	/** 3. 학생 메뉴 */
	private void printStudentMenu() {
		int menu = 0;
		do {
		printService.printStudentMenu();
		try {
		menu = scan.nextInt();
		runStudentMenu(menu);
		} catch (InputMismatchException e ) {
			System.out.println("없는 메뉴입니다.");
			scan.nextLine();
		}
		}while(menu != STUDENT_EXIT);
		
	}
	/** 런 학생 메뉴 */
	private void runStudentMenu(int menu) {
		switch(menu) {
		case 1 : 
			studentService.insertStudent(sList, student);
			break;
		case 2 : 
			studentService.updateStudent(sList, student);
			break;
		case 3 :
			studentService.deleteStudent(sList, student);
			break;
		case 4 : 
			break;
			
		default : throw new InputMismatchException();
		}
	}

	/** 4. 강의 관리*/
	private void LectureManager() {
		
		
	}

}

package university;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;
import university.service.DPService;
import university.service.DPServiceImp;
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
	public static Scanner scan = new Scanner(System.in);
	static String professorFileName = "src/university/professorList.txt";
	static String lectureFileName = "src/university/lectureList.txt";
	static String studentFileName = "src/university/studentList.txt";
	static String departmentFileName = "src/university/departmentList.txt";
	
	//메뉴 종료 상수
	static final int EXIT = 7;

	//서비스 목록
	private int STUDENT_EXIT = 4;	//뒤로 가기
	static final int LECTURE_EXIT =4;
	static final int DEPARTMENT_EXIT = 5;  // DEPARTMENT_EXIT 상수 추가
	static final int PROFESSOR_EXIT = 4;
	static final int SETPROFESSOR_EXIT = 6;

  //서비스 목록
	private PrintService printService = new PrintServiceImp();
	private FileService fileService = new FileServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	private StudentService studentService = new StudentServiceImp();
	
	//대학교 정보
	private DPService dpService = new DPServiceImp();  // DPService 인스턴스 추가
	
	//대학교 정보
	private List<Lecture> lList = new ArrayList<Lecture>();
	private List<Department> dList = new ArrayList<Department>();
	private List<Student> sList = new ArrayList<Student>();
	public static List<Professor> pList;
	Student student;
	
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


		//저장하기*
		isSave();
		
	}

	//불러오기 및 불러오기 판별
	private void isLoad() {
		//교수 정보 불러오기
		pList = fileService.pLoad(professorFileName);
		//강의 정보 불러오기
		List<Lecture> tmp = fileService.lLoad(lectureFileName);
		if (!(tmp == null)) {
			lList.addAll(tmp);
		}
		
	}


	//저장 및 저장판별
	private void isSave() {
		if(fileService.dpSave(departmentFileName, dList)) {
			System.out.println("학과 정보 저장이 완료되었습니다.");
		}else {
			System.out.println("학과 정보 저장에 실패했습니다.");
		}
		if(fileService.pSave(professorFileName)) {
			System.out.println("교수 정보 저장이 완료되었습니다.");
		}else {
			System.out.println("교수 정보 저장에 실패했습니다.");
		}
		if(fileService.sSave(studentFileName, sList)) {
			System.out.println("학생 정보 저장이 완료되었습니다.");
		}else {
			System.out.println("학생 정보 저장에 실패했습니다.");
		}
		if(fileService.lSave(lectureFileName, lList)) {
			System.out.println("강의 정보 저장이 완료되었습니다.");
		}else {
			System.out.println("강의 정보 저장에 실패했습니다.");
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
			departmentManage();
			break;
		case 2: 
			professorManage();
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
        int departmentMenu = 0;

        do {
            printService.printDPMenu(); // 학과 관리 메뉴 출력

            try {
                departmentMenu = scan.nextInt();
                runDPMenu(departmentMenu); // runDPMenu 호출
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴입니다.");
                scan.nextLine();
            }

        } while (departmentMenu != DEPARTMENT_EXIT);
    }

    // 메서드 추가: 학과 관리 서브 메뉴
    private void runDPMenu(int departmentMenu) {  // 매개변수 추가
        switch (departmentMenu) {
            case 1:
                // 학과 목록 조회
            	dpService.printDepartments(dList);
                break;
            case 2:
                // 학과 등록
                dpService.addDepartment(dList);
                fileService.dpSave(departmentFileName, dList);
                break;
            case 3:
                // 학과 수정
                if (dpService.editDepartment(dList)) {
                    fileService.dpSave(departmentFileName, dList);
                }
                break;
            case 4:
                // 학과 삭제
                if (dpService.deleteDepartment(dList)) {
                    fileService.dpSave(departmentFileName, dList);
                }
                break;
            case 5:
                System.out.println("뒤로 가기");
                break;
            default:
                throw new InputMismatchException();
        }
    }

	/** 2. 교수 관리 : 손나영 */
	private void professorManage() {
		
	}

	/** 3. 학생 관리 : 양선진 */
	private void studentManage() {
		
	}
	
	
	/** 3. 학생 메뉴 : 양선진 */
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
			insertLecture();
			break;
			
		case 2 :
			updateLecture();
			break;
			
		case 3 :
			deleteLecture();
			System.out.println("강의 삭제");
			break;
		case 4 :
			System.out.println("뒤로가기");
			break;
		default :  throw new InputMismatchException();
		}

	}
	
	/** 4. 강의 등록 : 심아진*/
	private void insertLecture() {
		lList.addAll(lectureService.addLecture(lList, lectureFileName));
		fileService.lSave(lectureFileName, lList);
	}

	/** 4. 강의 수정 : 심아진*/
	private void updateLecture() {
		if (!lectureService.printLecture(lList, lectureFileName)) {
			
		}		
	}

	/** 4. 강의 삭제 : 심아진*/
	private void deleteLecture() {

		
	}

}

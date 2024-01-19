package university;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;
import university.service.DPService;
import university.service.DPServiceImp;
import university.service.EnrolmentService;
import university.service.EnrolmentServiceImp;
import university.service.FileService;
import university.service.FileServiceImp;
import university.service.LectureService;
import university.service.LectureServiceImp;
import university.service.PrintService;
import university.service.PrintServiceImp;
import university.service.ProfessorService;
import university.service.ProfessorServiceImp;
import university.service.ScoreService;
import university.service.ScoreServiceImp;
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
	private DPService dpService = new DPServiceImp();  // DPService 인스턴스 추가
	private ScoreService scoreService = new ScoreServiceImp();
	private EnrolmentService enrolmentService = new EnrolmentServiceImp();
	
	//대학교 정보
	private List<Lecture> lList = new ArrayList<Lecture>();
	private List<Department> dList = new ArrayList<Department>();
	private List<Student> sList = new ArrayList<Student>();
	public static List<Professor> pList = new ArrayList<Professor>();

	
	@Override
	public void run() {
		int menu = 0;
		
		isLoad();
		
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

		//저장하기*
		isSave();
		
	}

	//불러오기 및 불러오기 판별
	private void isLoad() {
		//학과 정보 불러오기
		List<Department> tmpD = fileService.dLoad(departmentFileName);
		if (!(tmpD == null)) {
			dList.addAll(tmpD);
		}
		//학생 정보 불러오기
		List<Student> tmpS = fileService.sLoad(studentFileName);
		if (!(tmpS == null)) {
			sList.addAll(tmpS);
		}
		//교수 정보 불러오기
		List<Professor> tmpP = fileService.pLoad(professorFileName);
		if (!(tmpP == null)) {
			pList.addAll(tmpP);
		}
		//강의 정보 불러오기
		List<Lecture> tmpL = fileService.lLoad(lectureFileName);
		if (!(tmpL == null)) {
			lList.addAll(tmpL);
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
			printStudentMenu();
			break;
		case 4: 
			LectureManager();
			break;
		case 5: 
			enrolmentManager();
			System.out.println("수강 관리 서비스 예정");
			break;
		case 6: 	//성적관리시스템
			scoreManager();
			break;
		case 7:
			printManager();
			System.out.println("조회 서비스 예정");
			break;
		case 8:
			System.out.println("프로그램을 종료합니다.");
			break;
		default : 
			throw new InputMismatchException();
		}
	}

	
	private void scoreManager() {
		int menu = 0;

        do {
            printService.printScoreMenu();

            try {
            	menu = scan.nextInt();
                runScoreMenu(menu); 
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴입니다.");
                scan.nextLine();
            }

        } while (menu != 3);
	}

	private void runScoreMenu(int menu) {
		switch(menu) {
		case 1:		//성적등록
//			scoreService.~~();
			
			//학생 목록을 보여줌
			//성적 등록할 학생을 선택
			int sIndex = scan.nextInt() -1;
			//해당 학생이 수강하고 있는 강의 리스트를 가져온다.(출력)
			List<Lecture> stdlList = sList.get(sIndex).getLecture();
			//성적 등록할 강의 선택
			int lIndex = scan.nextInt() -1;
//			stdlList.get(lIndex).s
			
			break;
		case 2: 	//성적수정
//			scoreService.~~();
			break;
		case 3: 
			break;
		default : 
			throw new InputMismatchException();
		}		
		
	}

	private void printManager() {
		int menu = 0;

        do {
            printService.printPrintMenu();

            try {
            	menu = scan.nextInt();
                runPrintMenu(menu); 
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴입니다.");
                scan.nextLine();
            }

        } while (menu != 3);
	}

	//각자 파트 조회기능 구현
	private void runPrintMenu(int menu) {
		switch(menu) {
		case 1:		//학과조회
			
			break;
		case 2: 	//학생조회
			
			break;
		case 3: 	//교수조회
			
			break;
		case 4: 	//강의조회
			
			break;
		case 5: 	//성적조회
//			성적 조회
//			 *     -		- 학생 성적 조회 : 
//			 *     				- 평균 학점 조회
//		 *     					- 전체 학점 조회(수강하고있는 강의목록과 그 강의 점수 출력) 

			break;
		case 6: 
			break;
		default : 
			throw new InputMismatchException();
		}		
		
	}

	/** 수강신청 관리 */
	private void enrolmentManager() {
		int menu = 0;

        do {
            printService.printEnrolmentMenu();

            try {
            	menu = scan.nextInt();
                runEnrolmentMenu(menu); 
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴입니다.");
                scan.nextLine();
            }

        } while (menu != 3);
	}

	/** 수강신청 관리 : 메뉴실행*/
	private void runEnrolmentMenu(int menu) {
		switch(menu) {
		case 1:	//수강신청, 손나영
//			enrolmentService.~~~();
			break;
		case 2: //수강 취소
//			enrolmentService.~~();
			break;
		case 3: 
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
	public void professorManage() {
		int menu = 0;
		do {
			printService.printProfessorMenu();
			try {
				menu = UniversityProgram.scan.nextInt();
				runProfessorMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				UniversityProgram.scan.nextLine();
			}
		} while (menu != PROFESSOR_EXIT);
	}

		/** 교수 : 메뉴실행 */
	private void runProfessorMenu(int menu) {
		switch(menu) {
		case 1: 	//교수등록
			addProfessor();
			//세이브 
			break;
		case 2: 	//교수수정
			setProfessor();
			break;	
		case 3: 	//교수삭제
			delseteProfessor();

			break;
		case 4: 
			break;
		default : 
			throw new InputMismatchException();
		}
	}

		/** 교수 : 1.교수 등록 */
	private void addProfessor() {
		//교수 정보 입력
		//이름, 교번, 성별, 전화번호, 학과
		
		System.out.print("학과 : ");
		UniversityProgram.scan.nextLine();
		String dpName = UniversityProgram.scan.nextLine();
		//만약 학과가 학과 리스트안에 없다면
		int index=-1;
		for(int i=0; i<dList.size(); i++) {
			if(dList.get(i).dpName.equals(dpName)) {
				index = i;
				break;
			}
		}
		if(index < 0 || index >= dList.size()) {
			System.out.println("존재하지 않은 학과입니다.");	
			return;
		}
		
		//있다면 학과 리스트에서 동일한 학과 정보를 저장
		Department department = dList.get(index);
		
		System.out.print("성함 : ");
		String name = UniversityProgram.scan.next();
		
		int num = 0, gender=0;
		boolean trueNG = true; 
		while(trueNG) {
			try {
				System.out.print("교번 : ");
				num = UniversityProgram.scan.nextInt();
				if(num > 0 ) {
					trueNG = false;
				}
				System.out.println("잘못된 교번입니다.");					
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				UniversityProgram.scan.nextLine();
			}
		}
		
		while(trueNG) {
			try {
				System.out.print("성별(남:1, 여:2) : ");
				gender = UniversityProgram.scan.nextInt();
				if(gender == 1 || gender == 2) {
					trueNG = false;
				}
				System.out.println("잘못된 성별입니다.");
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				UniversityProgram.scan.nextLine();
			}
		}
		
		System.out.print("전화번호 : ");
		UniversityProgram.scan.nextLine();
		String phoneNum  = UniversityProgram.scan.next();
		
		//만약 성별이 1과 2가 아닌 숫자라면 다시 입력
//		do{
//			gender = UniversityProgram.scan.nextInt();
//			System.out.println("잘못된 성별입니다.");
//		}while(!(gender==1) || !(gender==2));
		
		Professor professor = new Professor(num, gender, name, phoneNum, department, null);
		if(!professorService.addProfessor(professor)){
			System.out.println("이미 등록된 교수입니다.");
			return;
		}
		System.out.println("교수를 등록했습니다.");
		professor.toString();
	}
	
		/** 교수 : 2. 교수 수정 */
	private void setProfessor() {
		//교수 목록을 보여줌(조회)
		
		System.out.print("수정할 교수의 교번을 입력하세요 : ");
		int num = UniversityProgram.scan.nextInt();
		
		//해당 교번과 동일한 교수정보 인덱스를 찾아서 삭제진행
		int index = -1;
		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getNum()==num) {
				index = i;
				break;
			}
		}
		//교번이 잘못된 경우
		if(index<0 || index >= pList.size()) {
			System.out.println("잘못된 교번입니다.");
			return;
		}
		
		//수정 메뉴 선택
		int menu = 0;
		do {
			printService.printSetProfessorMenu();
			try {
				menu = UniversityProgram.scan.nextInt();
				runSetProfessorMenu(menu, index);
				break;
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				UniversityProgram.scan.nextLine();
			}
		} while (menu != SETPROFESSOR_EXIT);
	}
	
			/** 교수 : 2. 교수 수정 메뉴실행 */
	private void runSetProfessorMenu(int menu, int index) {
		switch(menu) {
		case 1: 	//교번
			professorService.setNum(index);
			System.out.println("교변을 수정했습니다.");
			break;
		case 2: 	//이름
			professorService.setName(index);
			System.out.println("이름을 수정했습니다.");
			break;	
		case 3: 	//학과
			professorService.setDepartment(index);
			System.out.println("학과를 수정했습니다.");
			break;
		case 4: 	//성별
			professorService.setGender(index);
			System.out.println("성별을 수정했습니다.");
			break;
		case 5:		//전화번호
			professorService.setPhoneNum(index);
			System.out.println("전화번호를 수정했습니다.");
			break; 
		case 6:
			break;
		default : 
			throw new InputMismatchException();
		}
		//세이브
	}
	


		/** 교수 : 3. 교수 삭제 */
	private void delseteProfessor() {
		//교수 목록을 보여줌(조회)


		System.out.print("삭제할 교수의 교번을 입력하세요 : ");
		int num = UniversityProgram.scan.nextInt();

//		해당 교번과 동일한 교수정보 인덱스를 찾아서 삭제진행
		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getNum()==num) {
				professorService.deleteProfessor(i);
				System.out.println("교수 정보를 삭제했습니다.");
				return;
			}
		}
		System.out.println("잘못된 교번입니다.");
		
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
			studentService.insertStudent(dList, sList);
			break;
		case 2 : 
			studentService.updateStudent(sList);
			break;
		case 3 :
			studentService.deleteStudent(sList);
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

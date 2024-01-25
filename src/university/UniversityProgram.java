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
	static final int EXIT = 8;
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
	private ScoreService scoreService = new ScoreServiceImp();
	private EnrolmentService enrolmentService = new EnrolmentServiceImp();
	
	//대학교 정보
	private List<Lecture> lList = new ArrayList<Lecture>();
	public static List<Department> dList = new ArrayList<Department>();
	private List<Student> sList = new ArrayList<Student>();
	public static List<Professor> pList = new ArrayList<Professor>();
	Student student;
	Lecture lecture;

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
			enrolmentManager();		//수강신청
			break;
		case 6: 	//성적관리시스템
			scoreManager();
			break;
		case 7:
			printManager();
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
			scoreService.addScore(sList, lList);
			
			break;
		case 2: 	
			break;
		case 3: 
			break;
		default : 
			throw new InputMismatchException();
		}		
		fileService.lSave(lectureFileName, lList);
		fileService.sSave(studentFileName, sList);
		fileService.pSave(professorFileName);
		
	}

	private void printManager() {
		int menu = 0;

        printService.printPrintMenu();

        try {
        	menu = scan.nextInt();
            runPrintMenu(menu); 
        } catch (InputMismatchException e) {
            System.out.println("잘못된 메뉴입니다.");
            scan.nextLine();
        }

	}

	private void runPrintMenu(int menu) {
		switch(menu) {
		case 1:		//학과조회
			dpService.printDepartments(dList);
			break;
		case 2: 	//학생조회
			studentService.printStudentList(sList);
			break;
		case 3: 	//교수조회
			if(!professorService.printProfessor()) {
				return;
			}
			break;
		case 4: 	//강의조회
			if (!lectureService.printLecture(lList)) {
				return;
			}
			break;
		case 5: 	//성적조회
			do {
			printService.printScoreSubMenu();
			menu = scan.nextInt();
			runViewScore(menu);
			}while(menu != 3);
			break;
		default : 
			throw new InputMismatchException();
		}
	}
	
	/** 성적 조회 */
	private void runViewScore(int menu) {
		switch(menu) {
		case 1 :	//평균학점
			scoreService.showStudentStandardScore(sList);
			break;
		case 2 : 	//각 강의별 성적 조회
			scoreService.showStudentLectureScore(sList);
			break;
		case 3 : //뒤로 가기
			break;
			}
	}

	/** 수강신청 관리  : 손나영*/
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
			enrolment();
			break;
		case 2: //수강 취소
			Withdrawal();
//			enrolmentService.~~();
			break;
		case 3: 
			break;
		default : 
			throw new InputMismatchException();
		}		
		fileService.lSave(lectureFileName, lList);
		fileService.sSave(studentFileName, sList);
		fileService.pSave(professorFileName);
	}
		/** 수강신청 : 1. 수강신청*/
	private void enrolment() {
		//학생 학번 입력(로그인처럼)
		boolean trueS =true;
		int id = -1;
		while(trueS) {
			try {
				System.out.print("학번을 입력하세요 : ");
				id = scan.nextInt();
				if(id > 0 ) {
					trueS = false;
				}else {
					System.out.println("잘못된 학번입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		int indexS  = -1;	//학생인덱스
		boolean isStudent = false;
		//학번이 동일한 학생을 찾는다.
		for(int i=0; i<sList.size(); i++) {
			if(sList.get(i).getStudentId()==id) {
				indexS = i;
				isStudent = true;
				break;
			}
		}
		if(!isStudent) {
			System.out.println("등록되지 않은 학생입니다.");
			return;
		}
		
		//등록된 학생이라면 강의 목록을 보여준다
		//강의 목록이 없다면 메소드를 종료한다
		if(!lectureService.printLecture(lList)) {
			return;
		}
		
		//강의 번호를 선택한다.
		boolean trueL =true;
		int indexL = -1;	//강의인덱스
		while(trueL) {
			try {
				System.out.print("수강할 강의를 선택하세요 : ");
				indexL = scan.nextInt() - 1;
				if(!(indexL < 0 || indexL >= lList.size())) {
					trueL = false;
				}else {
					System.out.println("없는 강의입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		if(!enrolmentService.applications(indexL, indexS, lList, sList)) {
			return;
		}
		System.out.println("수강신청이 완료되었습니다.");
	}

	/** 수강신청 : 2. 수강취소*/
	private void Withdrawal() {
		//학생 학번 입력(로그인처럼)
		boolean trueS =true;
		int id = -1;
		while(trueS) {
			try {
				System.out.println("학번을 입력하세요.");
				id = scan.nextInt();
				if(id > 0 ) {
					trueS = false;
				}else {
					System.out.println("잘못된 학번입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		int indexS  = -1;	//학생인덱스
		boolean isStudent = false;
		//학번이 동일한 학생을 찾는다.
		for(int i=0; i<sList.size(); i++) {
			if(sList.get(i).getStudentId()==id) {
				indexS = i;
				isStudent = true;
				break;
			}
		}
		if(!isStudent) {
			System.out.println("등록되지 않은 학생입니다.");
			return;
		}
		
		//해당학생이 수강하고 있는 강의 목록 출력
		
		//삭제하고 싶은 강의 목록 선택
		
		//강의 리스트에서 동일한 강의 인덱스 얻어오기(학수번호)
		
		//해당 강의 리스트에서 강의 삭제하기 

		//해당강의 학생 리스트에서도 삭제
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
				menu = scan.nextInt();
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
			fileService.pSave(professorFileName);
			break;
		case 2: 	//교수수정
			setProfessor();
			fileService.pSave(professorFileName);
			break;	
		case 3: 	//교수삭제
			delseteProfessor();
			fileService.pSave(professorFileName);
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
		scan.nextLine();
		String dpName = scan.nextLine();
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
		String name = scan.next();
		
		int num = 0, gender=0, phoneNumInt=0;
		boolean trueN = true, trueG = true, trueP = true; 
		while(trueN) {
			try {
				System.out.print("교번 : ");
				num = scan.nextInt();
				if(num > 0 ) {
					trueN = false;
				}else {
					System.out.println("잘못된 교번입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		while(trueG) {
			try {
				System.out.print("성별(남:1, 여:2) : ");
				gender = scan.nextInt();
				if(gender == 1 || gender == 2) {
					trueG = false;
				}else {
					System.out.println("잘못된 성별입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		String phonNum ="";
		while(trueP) {
			try {
				System.out.print("전화번호(하이픈('-')을 제외한 11자리를 입력하세요) : ");
				phoneNumInt  = scan.nextInt();
				phonNum =  "0"+Integer.toString(phoneNumInt);
				if(phonNum.length() == 11 ) {
					trueP = false;
				}else {
					System.out.println("잘못된 전화번호입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		
		
		
		Professor professor = new Professor(num, gender, name, phonNum, department, null);
		if(!professorService.addProfessor(professor)){
			System.out.println("이미 등록된 교수입니다.");
			return;
		}
		System.out.println("교수를 등록했습니다.");
		professorService.printProfessor();
	}
	
		/** 교수 : 2. 교수 수정 */
	private void setProfessor() {
		//교수 목록을 보여줌(조회)
		if(!professorService.printProfessor()) {
			return;
		}
		boolean trueN =true;
		int num = -1;
		while(trueN) {
			try {
				System.out.print("수정할 교수의 교번을 입력하세요 : ");
				num = scan.nextInt();
				if(num > 0 ) {
					trueN = false;
				}else {
					System.out.println("잘못된 교번입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}
		
		
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
				menu = scan.nextInt();
				runSetProfessorMenu(menu, index);
				break;
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
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
			professorService.setDepartment(index, dList);
			
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
	}
	


		/** 교수 : 3. 교수 삭제 */
	private void delseteProfessor() {
		//교수 목록을 보여줌(조회)
		if(!professorService.printProfessor()) {
			return;
		}

		boolean trueN =true;
		int num = -1;
		while(trueN) {
			try {
				System.out.print("삭제할 교수의 교번을 입력하세요 : ");
				num = scan.nextInt();
				if(num > 0 ) {
					trueN = false;
				}else {
					System.out.println("잘못된 교번입니다.");
				}
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
			}
		}

//		해당 교번과 동일한 교수정보 인덱스를 찾아서 삭제진행
		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getNum()==num) {
				professorService.deleteProfessor(i);
				System.out.println("교수 정보를 삭제했습니다.");
				return;
			}
		}
		System.out.println("등록되지 않은 교수입니다.");
		
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
		} catch (Exception e) {
			System.out.println("올바른 성별을 입력하세요.");
		}
		}while(menu != STUDENT_EXIT);
		
	}
	/** 런 학생 메뉴 
	 * @throws Exception */
	private void runStudentMenu(int menu) throws Exception {
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
		fileService.sSave(studentFileName, sList);
		
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
				System.out.println("잘못된 메뉴입니다.");
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
			break;
		case 4 :
			System.out.println("뒤로가기");
			break;
		default :  throw new InputMismatchException();
		}

	}
	
	/** 4. 강의 등록 : 심아진*/
	private void insertLecture() {
		lectureService.addLecture(lList, lectureFileName);
		fileService.lSave(lectureFileName, lList);
	}

	/** 4. 강의 수정 : 심아진*/
	private void updateLecture() {
		lectureService.setLecture(lList, lecture);
		fileService.lSave(lectureFileName, lList);
	
	}

	/** 4. 강의 삭제 : 심아진*/
	private void deleteLecture() {
		lectureService.deleteLecture(lList);
		fileService.lSave(lectureFileName, lList);
	}

}

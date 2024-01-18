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
	static final int PROFESSOR_EXIT = 4;
	static final int SETPROFESSOR_EXIT = 6;
	
	//서비스 목록
	private PrintService printService = new PrintServiceImp();
	private FileService fileService = new FileServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	
	//대학교 정보
	private List<Lecture> lList = new ArrayList<Lecture>();
	private List<Department> dList = new ArrayList<Department>();
	private List<Student> sList = new ArrayList<Student>();
	public static List<Professor> pList;
	
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
		List<Lecture> tmp = fileService.lLoad(lectureFileName);
		if (!(tmp == null)) {
			lList.addAll(tmp);
		}
		pList = fileService.pLoad(professorFileName);
		
	}


	//저장 및 저장판별
	private void isSave() {
		if(fileService.dSave(departmentFileName, dList)) {
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
//		if(.equals("dpName")) {
//			System.out.println("존재하지 않은 학과입니다.");			
//		}
		//있다면 학과 리스트에서 동일한 학과 정보를 저장
		Department department = new Department(0, dpName, null, pList);
		
		System.out.print("성함 : ");
		String name = UniversityProgram.scan.next();
		System.out.print("교번 : ");
		int num = UniversityProgram.scan.nextInt();
		System.out.print("전화번호 : ");
		String phoneNum = UniversityProgram.scan.next();
		System.out.print("성별(남:1, 여:2) : ");
		int gender = 0;
		//만약 성별이 1과 2가 아닌 숫자라면 다시 입력
		do{
			gender = UniversityProgram.scan.nextInt();
			System.out.println("잘못된 성별입니다.");
		}while(!(gender==1) || !(gender==2));
		
		Professor professor = new Professor(num, gender, name, phoneNum, department, null);
		if(!professorService.addProfessor(professor)){
			System.out.println("이미 등록된 교수입니다.");
			return;
		}
		System.out.println("교수를 등록했습니다.");
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

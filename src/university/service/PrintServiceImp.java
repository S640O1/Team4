package university.service;

public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {	// 메인 메뉴
		System.out.println("---------KH대학---------");
		System.out.println("1. 학과 관리");
		System.out.println("2. 교수 관리");
		System.out.println("3. 학생 관리");
		System.out.println("4. 강의 관리");
		System.out.println("5. 수강 관리");
		System.out.println("6. 조회");
		System.out.println("7. 종료");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
		
		
		
	}
	
	@Override
	public void printDPMenu() {	// 학과 출력 메뉴
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printProfessorMenu() {	// 교수 출력 메뉴
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printStudentMenu() {	 // 학생 출력 메뉴
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printLectureMenu() {	// 강의 출력 메뉴
		// TODO Auto-generated method stub
		
	}


}

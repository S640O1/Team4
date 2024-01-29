package university.service;

import java.util.List;

import university.Department;



public class PrintServiceImp implements PrintService {

    @Override
    public void printMainMenu() {
        System.out.println("---------KH대학---------");
        System.out.println("1. 학과 관리");
        System.out.println("2. 교수 관리");
        System.out.println("3. 학생 관리");
        System.out.println("4. 강의 관리");
        System.out.println("5. 수강 관리");
        System.out.println("6. 성적 관리");
        System.out.println("7. 조회");
        System.out.println("8. 종료");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");
    }


    @Override
    public void printDPMenu() {		// 학과 출력 메뉴
        System.out.println("---------학과 관리---------");
        System.out.println("1. 학과 목록 조회");
        System.out.println("2. 학과 등록");
        System.out.println("3. 학과 수정");
        System.out.println("4. 학과 삭제");
        System.out.println("5. 뒤로가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");
    }

  /** 교수 : 메뉴출력 */
	@Override
	public void printProfessorMenu() {	// 교수 출력 메뉴
		System.out.println("---------교수관리---------");
		System.out.println("1. 교수 등록");
		System.out.println("2. 교수 정보 수정");
		System.out.println("3. 교수 삭제");
		System.out.println("4. 종료");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}
	
	/** 교수 : 2. 교수 수정 메뉴출력 */
	@Override
	public void printSetProfessorMenu() {
		System.out.println("------교수 정보 수정------");
		System.out.println("1. 교번");
		System.out.println("2. 이름");
		System.out.println("3. 학과");
		System.out.println("4. 성별");
		System.out.println("5. 전화번호");
		System.out.println("6. 뒤로가기");
		System.out.println("---------------");
		System.out.print("수정할 항목을 선택하세요 : ");
	}

	@Override
	public void printStudentMenu() {	 // 학생 출력 메뉴
		System.out.println("---------학생 관리---------");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 수정");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 뒤로 가기");
        System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}


    @Override
    public void printLectureMenu() {
        System.out.println("---------강의 관리---------");
        System.out.println("1. 강의 등록");
        System.out.println("2. 강의 수정");
        System.out.println("3. 강의 삭제");
        System.out.println("4. 뒤로가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");
    }

    @Override
    public void printLectureUpdateMenu() {
        System.out.println("---------강의 수정---------");
        System.out.println("1. 강의 번호 수정");
        System.out.println("2. 강의명 수정");
        System.out.println("3. 담당 교수 번호 수정");
        System.out.println("4. 담당 교수 이름 수정");
        System.out.println("5. 최대 수강 인원 수정");
        System.out.println("6. 강의 시간 수정");
        System.out.println("7. 강의실 수정");
        System.out.println("8. 뒤로가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");
    }


	@Override
	public void printEnrolmentMenu() {
		System.out.println("-------수강 관리-------");
        System.out.println("1. 수강 신청");
        System.out.println("2. 수강 취소");
        System.out.println("3. 뒤로 가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");		
		
	}


	@Override
	public void printScoreMenu() {
		System.out.println("-------성적 관리-------");
        System.out.println("1. 성적 등록");
        System.out.println("2. 성적 수정");
        System.out.println("3. 뒤로가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");		
	}


	@Override
	public void printPrintMenu() {
		 System.out.println("---------조회---------");
        System.out.println("1. 학과 정보 조회");
        System.out.println("2. 학생 정보 조회");
        System.out.println("3. 교수 정보 조회");
        System.out.println("4. 강의 정보 조회");
        System.out.println("5. 성적 조회");
        System.out.println("6. 뒤로가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");
	}


	@Override
	public void printScoreSubMenu() {
		System.out.println("------성적 조회------");
        System.out.println("1. 학생 평균 학점 조회");
        System.out.println("2. 학생 각 강의별 학점 조회");
        System.out.println("3. 뒤로 가기");
        System.out.println("---------------");
        System.out.print("메뉴 선택 : ");
	}



}

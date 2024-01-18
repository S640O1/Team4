package university.service;

import java.util.List;

import university.Department;

public interface PrintService {
	void printMainMenu();
	void printDPMenu();
	//교수 관리 메뉴 출력
	void printProfessorMenu();
	//교수 수정 메뉴 출력
	void printSetProfessorMenu();
	void printStudentMenu();
	
	// 강의 관리 메뉴
	void printLectureMenu();
	
	// 강의 수정 메뉴
	void printLectureUpdateMenu();


	
}

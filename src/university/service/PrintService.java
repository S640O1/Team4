package university.service;

public interface PrintService {
	void printMainMenu();
	void printDPMenu();
	//교수 관리 메뉴 출력
	void printProfessorMenu();
	//교수 수정 메뉴 출력
	void printSetProfessorMenu();
	void printStudentMenu();
	void printLectureMenu();
	void printLectureUpdateMenu();
	
}

package siteCafeManagement.service;

public interface PrintService {
	
	// 메인 메뉴
	void printMainMenu();
	
	//회원 관리
	//printMembership
	void printMembership();

	//관리자 관리
	void printManager();
	
	// 카테고리 관리
	void printCategoryManager();

	// 게시판 관리
	void printBoardManager();
	
	// 게시판 수정 메뉴
	void printBoardUpdate();
	
	//사용자 관리
	void printUser();

}

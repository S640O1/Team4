package siteCafeManagement.service;

public interface PrintService {
	
	//회원 관리
	//printMembership
	void printMembership();

	//관리자 관리
	void printManager();
	
	// 카테고리 관리
	void printCategoryManager();

	// 게시판 관리
	void printBoardManager();
	
	//사용자 관리
	void printUser();

}

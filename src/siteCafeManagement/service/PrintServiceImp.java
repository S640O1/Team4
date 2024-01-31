package siteCafeManagement.service;

public class PrintServiceImp implements PrintService{

	/**
	 * 회원관리 메뉴
	 */
	@Override
	public void printMembership() {
			System.out.println("[회원 관리]");
			System.out.println("1. 로그인");
			System.out.println("2. 로그아웃");
			System.out.println("3. 회원가입");
			System.out.println("4. 뒤로가기");
			System.out.print("메뉴 선택 : ");
	}

	/**
	 * 관리자 메뉴
	 */
	@Override
	public void printManager() {
		System.out.println("[관리자 관리]");
		System.out.println("1. 게시판 관리");
		System.out.println("2. 카테고리 관리");
		System.out.println("3. 뒤로가기");
		System.out.print("메뉴 선택 : ");
		
	}
	
	public void printCategoryManager() {
		System.out.println("[카테고리 관리]");
		System.out.println("1. 카테고리 등록");
		System.out.println("2. 카테고리 수정");
		System.out.println("3. 카테고리 삭제");
		System.out.println("4. 뒤로가기");
		System.out.print("메뉴 선택 : ");
	}
	
	@Override
	public void printBoardManager() {
		System.out.println("[게시판 관리]");
		System.out.println("1. 게시판 등록");
		System.out.println("2. 게시판 수정");
		System.out.println("3. 게시판 삭제");
		System.out.println("4. 뒤로가기");
		System.out.print("메뉴 선택 : ");
	}
	
	

	/**
	 * 사용자 메뉴
	 */
	@Override
	public void printUser() {
		System.out.println("[게시글 관리]");
		System.out.println("--------------");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 조회");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.print("메뉴 선택 : ");
	}

	

}

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

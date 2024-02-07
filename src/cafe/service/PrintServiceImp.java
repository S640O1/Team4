package cafe.service;

public class PrintServiceImp implements PrintService {

	/** Post 메뉴 출력 */
	@Override
	public void printPostMenu() {
		System.out.println("[게시글 관리]");
		System.out.println("--------------");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 조회");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.print("메뉴 선택 : ");		
	}

}

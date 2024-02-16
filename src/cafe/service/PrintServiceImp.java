package cafe.service;

public class PrintServiceImp implements PrintService {
	
	@Override
	public void printBoardMenu() {
		System.out.println();
		System.out.println("╭───────────────╮");
		System.out.println("　 ꒰　게시판 관리 ꒱");
		System.out.println("╰───────────────╯");
		System.out.println("[1] 게시판 등록");
		System.out.println("[2] 게시판 조회");
		System.out.println("[3] 게시판 수정");
		System.out.println("[4] 게시판 삭제");
		System.out.println("[5] 뒤로 가기");
		System.out.print("[메뉴 선택] ");	
	}
	
	/** Post 메뉴 출력 */

	/** [ Post ] 메인 메뉴 출력 */
	@Override
	public void printPostMenu() {
		System.out.println("╔═════ °• 💻 •° ═════╗");
		System.out.println(" 　   ꒰ KH Cafe ꒱ 　　 ");
		System.out.println("╚═════ °• 💻 •° ═════╝");
		System.out.println("[1] 게시글 작성");
		System.out.println("[2] 게시글 조회");
		System.out.println("[3] 게시글 수정");
		System.out.println("[4] 게시글 삭제");		
		System.out.println("[5] 로그아웃");
		System.out.print("[메뉴 선택] ");	
	}
	

	/** [ Post ] 조회 메뉴 출력 */
	@Override
	public void printViewPostMenu() {
		System.out.println("╭───────────────╮");
		System.out.println("　 ꒰　게시글 조회 ꒱");
		System.out.println("╰───────────────╯");
		System.out.println("[1] 전체 게시글 조회");
		System.out.println("[2] 게시글 선택 조회");
		System.out.println("[3] 본인 게시글 조회");
		System.out.println("[4] 뒤로 가기");
		System.out.print("[메뉴 선택] ");	
	}

}

package _saj.accountBook_saj.service;

public class printServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("-------가계부--------");
		System.out.println("1. 가계부 입력");
		System.out.println("2. 가계부 조회");
		System.out.println("3. 가계부 수정");
		System.out.println("4. 가계부 삭제");
		System.out.println("5. 현재 잔액 조회");
		System.out.println("6. 종료");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}

}

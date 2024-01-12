package accountBook.service;

public class PrintServiceImp implements PrintService {

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

	@Override
	public void printUpdateMenu() {
		System.out.println("------수정 사항------");
		System.out.println("1. 수입/지출 여부 수정");
		System.out.println("2. 일자 수정");
		System.out.println("3. 금액 수정");
		System.out.println("4. 내역 수정");
		System.out.println("5. 전체 수정");
		System.out.println("6. 뒤로 가기");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : "); 		
	}

}

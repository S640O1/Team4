package accountBook.service;

public interface AccountBookService {
	//기능을 정한 뒤 업무 분담
	
	//가계부(리스트)에 내역을 추가하는 메소드 :  누구~
	boolean addItem(List<Item> list, Item item);
	
	//가계부(리스트)에 내역을 수정하는 메소드
	boolean setItem(List<Item> list, String title, int moeny);
}

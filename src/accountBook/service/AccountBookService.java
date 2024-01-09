package accountBook.service;

import java.util.List;

import accountBook.Item;



public interface AccountBookService {
	//기능을 정한 뒤 업무 분담
	
	//1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진
	boolean addAB(List<Item> list, Item ab);
	
	//2. 가계부(리스트)에 내역을 조회하는 메소드 : 신경재
	boolean printAB(List<Item> list, String title);

	//3. 가계부(리스트)에 내역을 수정하는 메소드 : 손나영
	boolean setAB(int index, int money, int totalMoney, int date,
			boolean inMoney,boolean outMoney, String memo);
	
	//4. 가계부(리스트)에 내역을 삭제하는 메소드 : 양선진
	boolean deleteAB(List<Item> list, String title);
	
	//5. 현재 잔액을 출력하는 메소드 : 신경재
	boolean printCurrentMoney();
	
	/** 가계부 존재여부 확인 메소드*/
	boolean isList();
	
	/** index 오류여부 확인 메소드*/
	boolean indexError(int index);
}

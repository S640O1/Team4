package accountBook.service;

import java.util.List;

import accountBook.Item;

public interface AccountBookService {
	//기능을 정한 뒤 업무 분담
	
	//1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진
	boolean addAB(List<Item> list, String fileName);
	
	//2. 가계부(리스트)에 내역을 조회하는 메소드 : 신경재
	boolean printAB(List<Item> list);

	//3. 가계부(리스트)에 내역을 수정하는 메소드 : 손나영
	boolean setAB(int index, Item ab, List<Item> list);
	
		/** 가계부 수정 1. 입금/지출 항목수정*/
	boolean runUpateInOut(int index, List<Item> list);
	
		/** 가계부 수정 2. 일자수정*/
	boolean runUpateInDate(int index, List<Item> list);

		/** 가계부 수정 3. 금액수정*/
	boolean runUpateInMoney(int index, List<Item> list);
	
		/** 가계부 수정 4. 잔액수정*/
	boolean runUpateInTotalMoney(int index, List<Item> list);
	
		/** 가계부 수정 5. 내역수정*/
	boolean runUpateInMemo(int index, List<Item> list);
	
		/** 가계부 수정 6. 전체수정*/
	boolean runUpateInAll(int index, List<Item> list);
	
	//4. 가계부(리스트)에 내역을 삭제하는 메소드 : 양선진
	boolean deleteAB(List<Item> list);
	
	//5. 현재 잔액을 출력하는 메소드 : 신경재
	boolean printCurrentMoney(List<Item> list);
	
	/** 가계부 존재여부 확인 메소드*/
	boolean isList(List<Item> list);
	
	/** index 오류여부 확인 메소드*/
	boolean indexError(int index, List<Item> list);
	
	/** 정렬 메소드 */
	void sort(List<Item> list);






}

package accountBook.service;

import java.util.List;

import accountBook.Item;



public class AccountBookServiceImp implements AccountBookService{
	
	private List<Item> list;

	/**1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진*/
	@Override
	public boolean addAB(List<Item> list, Item ab) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**2. 가계부(리스트)에 내역을 조회하는 메소드 :  신경재*/
	@Override
	public boolean printAB(List<Item> list, String title) {
		// TODO Auto-generated method stub
		return false;
	}
	

	/**3. 가계부(리스트)의 내역을 수정하는 메소드 :  손나영*/
	@Override
	public boolean setAB(int index, int money, int totalMoney, int date,
			boolean inMoney,boolean outMoney, String memo) {
		//수정
		list.set(index, new Item(money, totalMoney, date, inMoney, outMoney, memo));

		return false;
	}

	/**4. 가계부(리스트)에 내역을 삭제하는 메소드 :  양선진*/
	@Override
	public boolean deleteAB(List<Item> list, String title) {
		// TODO Auto-generated method stub
		return false;
	}

	/**5. 현재 잔액을 출력하는 메소드 :  신경재*/
	@Override
	public boolean printCurrentMoney() {
		// TODO Auto-generated method stub
		return false;
	}


	/** 가계부 존재여부 확인 메소드*/
	@Override
	public boolean isList() {
		//리스트가 비어있다면
		if(list==null) {
			return false;
		}
		return true;
	}

	/** index 오류여부 확인 메소드*/
	@Override
	public boolean indexError(int index) {
		//index가 잘못된 경우
		if(index < 0 || index >= list.size()) {
			return false;			
		}
		return true;
	}
	
	

}

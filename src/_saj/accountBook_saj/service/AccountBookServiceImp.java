package _saj.accountBook_saj.service;

import java.util.List;

import _saj.accountBook_saj.Item;

public class AccountBookServiceImp implements AccountBookService {

	
	/**1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진*/
	@Override
	public boolean addAB(List<Item> list, Item ab) {
		return false;
	}

	
	/**2. 가계부(리스트)에 내역을 조회하는 메소드 :  신경재*/
	@Override
	public boolean printAB(List<Item> list, String title) {
		return false;
	}

	/**3. 가계부(리스트)의 내역을 수정하는 메소드 :  손나영*/
	@Override
	public boolean setAB(List<Item> list, String title, int moeny) {
		

		return false;
	}

	/**4. 가계부(리스트)에 내역을 삭제하는 메소드 :  양선진*/
	@Override
	public boolean deleteAB(List<Item> list, String title) {
		return false;
	}

	/**5. 현재 잔액을 출력하는 메소드 :  신경재*/
	@Override
	public boolean printCurrentMoney() {
		return false;
	}

}

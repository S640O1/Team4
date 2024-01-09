package accountBook.service;

import java.util.List;

import accountBook.AccountBook;

public class AccountBookServiceImp implements AccountBookService{

	/**1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진*/
	@Override
	public boolean addAB(List<AccountBook> list, AccountBook ab) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**2. 가계부(리스트)에 내역을 조회하는 메소드 :  신경재*/
	@Override
	public boolean printAB(List<AccountBook> list, String title) {
		// TODO Auto-generated method stub
		return false;
	}

	/**3. 가계부(리스트)에 내역을 수정하는 메소드 :  손나영*/
	@Override
	public boolean setAB(List<AccountBook> list, String title, int moeny) {
		// TODO Auto-generated method stub
		return false;
	}

	/**4. 가계부(리스트)에 내역을 삭제하는 메소드 :  양선진*/
	@Override
	public boolean deleteAB(List<AccountBook> list, String title) {
		// TODO Auto-generated method stub
		return false;
	}

	/**5. 현재 잔액을 출력하는 메소드 :  신경재*/
	@Override
	public boolean printCurrentMoney() {
		// TODO Auto-generated method stub
		return false;
	}

}

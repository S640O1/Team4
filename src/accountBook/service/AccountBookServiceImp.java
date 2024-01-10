package accountBook.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import accountBook.Item;



public class AccountBookServiceImp implements AccountBookService{
	
	private List<Item> list;
	Scanner scan = new Scanner(System.in);

	private FileService fileService = new FileServiceImp();


	
	/**1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진*/
	@Override
	public boolean addAB(List<Item> list, String fileName) {
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		int money = 0, totalMoney;
		
		
		System.out.println("날짜 (yyyy-MM-dd) : ");
		
		while (scan.hasNextLine()) {
			try {
				date = format1.parse(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("날짜를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
			}
		}
		
		System.out.println("금액 : ");
		money = scan.nextInt();
		scan.nextLine();
		
		if (list.size() <= 0) {
			totalMoney = 0;
		} else {
			int index = list.size()-1;
			totalMoney = list.get(index).getTotalMoney();
		}
		
		System.out.println("수입(1)/지출(2) : ");
		int classify = scan.nextInt();
		boolean inMoney=false, outMoney=false;
		
		if (classify == 1) {
			inMoney=true;
			outMoney=false;
			totalMoney += money;
			
		} else if (classify == 2) {
			inMoney=false;
			outMoney=true;
			totalMoney -= money;
		}
			
		System.out.println("내역 : ");
		scan.nextLine();
		String memo = scan.nextLine();
		
		list.add(new Item(money, totalMoney, date, inMoney, outMoney, memo));
		
		System.out.println("날짜 : " + format1.format(date) + " 금액 : " + money + " 내역 : " + memo + "잔액 : " + totalMoney);
		System.out.println("등록이 완료되었습니다.");
		
		if(fileService.save(fileName, list)) {
			System.out.println("저장"); 
		};
		return true;
	}

	
	/**2. 가계부(리스트)에 내역을 조회하는 메소드 :  신경재*/
	@Override
	public boolean printAB(List<Item> list) {
		if (list.isEmpty()) {
	        System.out.println("가계부 기입 내역이 없습니다.");
	        return false;
		}
		System.out.println("전체 내역:");
	    for (int i = 0; i < list.size(); i++) {
	        Item item = list.get(i);
	        System.out.println(item.toString(i));
	    }
	    return true;
	}
	

	/**3. 가계부(리스트)의 내역을 수정하는 메소드 :  손나영*/
	@Override
	public boolean setAB(int index, int money, int totalMoney, Date date,
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

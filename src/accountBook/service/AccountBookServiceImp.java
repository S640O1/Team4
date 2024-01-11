package accountBook.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import accountBook.Item;



public class AccountBookServiceImp implements AccountBookService{
	
	Scanner scan = new Scanner(System.in);

	private FileService fileService = new FileServiceImp();

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	
	/**1. 가계부(리스트)에 내역을 추가하는 메소드 :  심아진*/
	@Override
	public boolean addAB(List<Item> list, String fileName) {
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		boolean inMoney=false, outMoney=false;
		int totalMoney;
		
		
		System.out.print("날짜 (yyyy-MM-dd) : ");
		while (scan.hasNextLine()) {
			try {
				date = format1.parse(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("날짜를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
				scan.nextLine();
			}
		}
		System.out.print("수입(1)/지출(2) : ");
		int classify = scan.nextInt();
		System.out.print("금액 : ");
		int money = scan.nextInt(); 
		System.out.print("내역 : ");
		scan.nextLine();
		String memo = scan.nextLine();
		
		if (list.size() <= 0) {
			totalMoney = 0;
		} else {
			int index = list.size()-1;
			totalMoney = list.get(index).getTotalMoney();
		}
		
		if (classify == 1) {
			inMoney=true;
			outMoney=false;
			totalMoney += money;
		} else if (classify == 2) {
			inMoney=false;
			outMoney=true;
			totalMoney -= money;
		}
			
		Item item = new Item(money, totalMoney, date, inMoney, outMoney, memo);
		list.add(item);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("  순번  수입/지출     일자         금액         잔액          내역");
		System.out.println("----------------------------------------------------------------------");

		 System.out.println(item.toString(list.indexOf(item)));
//		System.out.println("날짜 : " + format1.format(date) + " 금액 : " + money + " 내역 : " + memo + "잔액 : " + totalMoney);
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
		System.out.println("----------------------------------------------------------------------");
		System.out.println("  순번  수입/지출     일자         금액         잔액           내역");
		System.out.println("----------------------------------------------------------------------");
	
	    for (int i = 0; i < list.size(); i++) {
	        Item item = list.get(i);
	        System.out.println(item.toString(i));
	    }
	    return true;
	}

	/**3. 가계부(리스트)의 내역을 수정하는 메소드 :  손나영*/
	@Override
	public boolean setAB(int index, Item item, List<Item> list) {
		//수정
		list.set(index, item);
		return false;
	}
	
		/** 가계부 수정 1. 입금/지출 항목수정*/
	@Override
	public boolean runUpateInOut(int index, List<Item> list) {
		
		//총액도 같이 변경
		int totalMoney = list.get(index).getTotalMoney();
		int money = list.get(index).getMoney();
		
		//수입항목이라면
		if(list.get(index).isInMoney() && !list.get(index).isOutMoney()) {
			//지출항목으로
			list.get(index).setInMoney(false);
			list.get(index).setOutMoney(true);
			//총액변경
			totalMoney -= (money*2);
			list.get(index).setTotalMoney(totalMoney);
			return true;
		}else if(!list.get(index).isInMoney() && list.get(index).isOutMoney()) {
			list.get(index).setInMoney(true);
			list.get(index).setOutMoney(false);
			//총액변경
			totalMoney += (money*2);
			list.get(index).setTotalMoney(totalMoney);
			return true;
		}
		return false;
	}
	
		/** 가계부 수정 2. 일자수정*/
	@Override
	public boolean runUpateInDate(int index, List<Item> list) {
		Date date=null;
		try {		
			System.out.print("일자 : ");
			try {
				date = format1.parse(scan.nextLine());
			} catch (ParseException e) {
				System.out.println("잘못된 입력입니다.");
			}
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}	
		list.get(index).setDate(date);
		return true;
	}

		/** 가계부 수정 3. 금액수정*/
	@Override
	public boolean runUpateInMoney(int index, List<Item> list) {
		int money =0;
		try {		
			System.out.print("금액 : ");
			money = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		list.get(index).setMoney(money);
		return true;
	}

		/** 가계부 수정 4. 잔액수정*/
	@Override
	public boolean runUpateInTotalMoney(int index, List<Item> list) {
		int totalMoney=0;
		try {		
			System.out.print("잔액 : ");
			totalMoney = scan.nextInt();
			}
		catch(InputMismatchException e) {
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
		}		
		list.get(index).setTotalMoney(totalMoney);
		return true;
	}

		/** 가계부 수정 5. 내역수정*/
	@Override
	public boolean runUpateInMemo(int index, List<Item> list) {
		String memo="";
		
		try {		
		// 내역받기
		System.out.print("내역");
		scan.nextLine();
		memo = scan.nextLine();	
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}
		
		list.get(index).setMemo(memo);
		return true;
	}

		/** 가계부 수정 6. 전체수정*/
	@Override
	public boolean runUpateInAll(int index, List<Item> list) {
		int money = 0, totalMoney = 0, classify;
		Date date = null;
		boolean outMoney = false, inMoney = false;
		String memo = null;
		
		
		//전체 수정 항목 받아오기
		try {
			System.out.print("입금(1)/지출(2) : ");
			classify = scan.nextInt();
			if(classify == 1) {
				inMoney = true;
				outMoney = false;
			}else if(classify == 2) {
				inMoney = false;
				outMoney = true;
			}else {
				System.out.println("잘못된 번호입니다.");
			}
			System.out.print("일자 : ");
			try {
				date = format1.parse(scan.nextLine());
			} catch (ParseException e) {
				System.out.println("잘못된 입력입니다.");
			}
			System.out.print("금액 : ");
			money = scan.nextInt();
			System.out.print("잔액 : ");
			totalMoney = scan.nextInt();
			System.out.print("내역");
			scan.nextLine();
			memo = scan.nextLine();	
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}
		
		Item ab = new Item(money, totalMoney, date, inMoney, outMoney, memo);
		
		if(!setAB(index, ab, list)) {
			System.out.println("수정에 실패했습니다."); 
		}
		return false;
	}

	/**4. 가계부(리스트)에 내역을 삭제하는 메소드 :  양선진*/
	@Override
	public boolean deleteAB(List<Item> list) {

		//순서대로 배열 나열해서 보기
		if(!printAB(list)) {
			System.out.println("조회에 실패했습니다.");
		}
		
		//삭제할 번호 받기
		System.out.print("삭제할 번호 : ");
		//받은 num의 -1을 해야 인덱스 번호
		int index = scan.nextInt() - 1;
		
		//삭제할 번호가 없으면(인덱스가 0보다 작거나, 리스트 사이즈보다 클때)없다고 출력 후 return;
		if(index < 0 || index >= list.size()) {
			System.out.println("없는 번호입니다.");
			return false;
		}
		//있으면 정말로 삭제하겠습니까? 문구 출력 후 y/n
		System.out.println("정말로 삭제하겠습니까?(y/n) : ");
		char areYouSure = scan.next().charAt(0);
		if(areYouSure == 'y') {
			//리스트의 index 배열 삭제
			list.remove(index);
			System.out.println("삭제되었습니다.");
			return true;
		} else { //y외 다른 문자면 취소
			System.out.println("취소되었습니다.");
			return false;			
		}
	}

	/**5. 현재 잔액을 출력하는 메소드 :  신경재*/
	@Override
	public boolean printCurrentMoney(List<Item> list) {
		int index = list.size()-1;
		
		System.out.println("현재 잔액 : " + list.get(index).getTotalMoney());
		
		return true;
	}

	/** 가계부 존재여부 확인 메소드*/
	@Override
	public boolean isList(List<Item> list) {
		//리스트가 비어있다면
		if(list==null) {
			return false;
		}
		return true;
	}

	/** index 오류여부 확인 메소드*/
	@Override
	public boolean indexError(int index, List<Item> list) {
		//index가 잘못된 경우
		if(index < 0 || index >= list.size()) {
			return false;			
		}
		return true;
	}


	


	


	

}

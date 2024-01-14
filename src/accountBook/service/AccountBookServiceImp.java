package accountBook.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	/* 강사 피드백
	 * - 메서드내에서 무조건 true인데 boolean으로 하는 의미가? */
	@Override
	public boolean addAB(List<Item> list, String fileName) {
		
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		boolean inMoney=false, outMoney=false;
		int totalMoney=0;
		
		System.out.print("일자 (yyyy-MM-dd) : ");
		while (scan.hasNextLine()) {
			try {
				date = format1.parse(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("일자를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
				System.out.print("일자 (yyyy-MM-dd) : ");
			}
		}
		System.out.print("수입(1)/지출(2) : ");
		int classify = scan.nextInt();
		System.out.print("금액 : ");
		int money = scan.nextInt(); 
		System.out.print("내역 : ");
		scan.nextLine();
		String memo = scan.nextLine();
		
		int index = list.size();
		//만약 리스트가 비어있다면
		if(index <=0) {
			totalMoney=0;
		}else {
			totalMoney = list.get(index-1).getTotalMoney();			
		}
		/* 강사 피드백
		 * - 수입 지출 여부를 잘못 선택하면 알림을 출력하고, 처리해야 함*/
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
		recalculation(list);
		if(fileService.save(fileName, list)) {
			System.out.println("가계부를 저장했습니다."); 
		};
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("  순번  수입/지출     일자         금액         잔액          내역");
		System.out.println("----------------------------------------------------------------------");
		
		System.out.println(item.toString(list.indexOf(item)));
		
		return true;
	}

	/**2. 가계부(리스트)에 내역을 조회하는 메소드 :  신경재*/
	@Override
	public boolean printAB(List<Item> list) {
		if (list.isEmpty()) {
	        return false;
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println("  순번  수입/지출     일자         금액         잔액           내역");
		System.out.println("----------------------------------------------------------------------");
		recalculation(list);
	    for (int i = 0; i < list.size(); i++) {
	        Item item = list.get(i);
	        System.out.println(item.toString(i));
	    }
	    return true;
	}

	/**3. 가계부(리스트)의 내역을 수정하는 메소드 :  손나영*/
	@Override
	public boolean setAB(int index, Item item, List<Item> list) {
		/* 강사 피드백
		 * - index의 유효성 여부 체크 안함. 유효하지 않으면 실패하는데...*/
		//수정
		list.set(index, item);
		recalculation(list);
		return true;
	}
	
		/** 가계부 수정 1. 입금/지출 항목수정*/
	@Override
	public boolean runUpateInOut(int index, List<Item> list) {
		//수입항목이라면
		if(list.get(index).isInMoney() && !list.get(index).isOutMoney()) {
			//지출항목으로
			list.get(index).setInMoney(false);
			list.get(index).setOutMoney(true);
			//총액변경 및 정렬
			recalculation(list);
			return true;
		}else if(!list.get(index).isInMoney() && list.get(index).isOutMoney()) {
			list.get(index).setInMoney(true);
			list.get(index).setOutMoney(false);
			//총액변경 및 정렬
			recalculation(list);
			return true;
		}
		return false;
	}
	
		/** 가계부 수정 2. 일자수정*/
	@Override
	public boolean runUpateInDate(int index, List<Item> list) {
		Date date=null;
		System.out.print("일자 (yyyy-MM-dd) : ");
		while (scan.hasNextLine()) {
			try {
				date = format1.parse(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("일자를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
				System.out.print("일자 (yyyy-MM-dd) : ");
			}
		}
		list.get(index).setDate(date);
		recalculation(list);
		return true;
	}

		/** 가계부 수정 3. 금액수정*/
	@Override
	public boolean runUpateInMoney(int index, List<Item> list) {
		/* 강사 피드백
		 * - 음수 체크는??
		 * */
		int money =0;
		try {		
			System.out.print("금액 : ");
			money = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		list.get(index).setMoney(money);
		recalculation(list);
		return true;
	}

		/** 가계부 수정 5. 내역수정*/
	@Override
	public boolean runUpateInMemo(int index, List<Item> list) {
		String memo="";
		/* 강사 피드백
		 * - nextLine()쓰기전에 공백처리 안해도 되는지?*/
		try {		
		// 내역받기
		System.out.print("내역 : ");
		memo = scan.nextLine();	
		}catch(InputMismatchException e) {
			scan.nextLine();
			System.out.println("잘못된 입력입니다.");
		}
		
		list.get(index).setMemo(memo);
		return true;
	}

		/** 가계부 수정 6. 전체수정*/
	/* 강사 피드백
	 * - index가 유효하면 해당 index 번지를 삭제 후 전체를 다시 입력받아 추가하면 코드의 중복을 줄일 수 있음.*/
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
			scan.nextLine();
			System.out.print("일자 (yyyy-MM-dd) : ");
			while (scan.hasNextLine()) {
				try {
					date = format1.parse(scan.nextLine());
					break;
				} catch (Exception e) {
					System.out.println("일자를 yyyy-MM-dd의 형태로 다시 입력해주세요.");
					System.out.print("일자 (yyyy-MM-dd) : ");
				}
			}
			System.out.print("금액 : ");
			money = scan.nextInt();

			System.out.print("내역 : ");
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
		recalculation(list);
		return true;
	}

	/**4. 가계부(리스트)에 내역을 삭제하는 메소드 :  양선진*/
	@Override
	public boolean deleteAB(List<Item> list, String fileName) {

		//순서대로 배열 나열해서 보기
		if(!printAB(list)) {
			System.out.println("가계부를 등록해주세요.");
			return false;
		}
		
		//삭제할 번호 받기
		System.out.print("삭제할 번호 : ");
		//받은 num의 -1을 해야 인덱스 번호
		int index = scan.nextInt() - 1;
		
		//삭제할 번호가 없으면(인덱스가 0보다 작거나, 리스트 사이즈보다 클때)없다고 출력 후 return;
		/* 강사 피드백
		 * - indexError 메서드를 활용하면 좋음*/
		if(index < 0 || index >= list.size()) {
			System.out.println("없는 번호입니다.");
			return false;
		}
		//있으면 정말로 삭제하겠습니까? 문구 출력 후 y/n
		System.out.print("정말로 삭제하겠습니까?(y/n) : ");
		char areYouSure = scan.next().charAt(0);
		/* 강사 피드백
		 * - 대문자도 처리해주면 좋음*/
		if(areYouSure == 'y') {
			//리스트의 index 배열 삭제
			list.remove(index);
			recalculation(list);
			fileService.save(fileName, list);
			System.out.println("삭제되었습니다.");
			return true;
		}
			//n일시
			else if(areYouSure == 'n') { 
			System.out.println("취소되었습니다.");
			return false;			
		} 
			//다른 문자일시
			else {	
			System.out.println("잘못된 문자입니다.");
			return false;
		}
	}

	/**5. 현재 잔액을 출력하는 메소드 :  신경재*/
	@Override
	public boolean printCurrentMoney(List<Item> list) {
		if (list.isEmpty()) {
	        return false;
		}
		int index = list.size()-1;
		
		System.out.println("현재 잔액 : " + list.get(index).getTotalMoney()+"원");
		
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

	/** 정렬 메소드 */
	@Override
	public void sort(List<Item> list) {
		list.sort((s1,s2)->{
			return s1.getDate().compareTo(s2.getDate());
			//아래의 경우 문자열로 인식해서 12월을 1월보다 앞에 정렬함
			/*//년도가 같으면
			if (s1.getDate().getYear() != s2.getDate().getYear()){
				return s1.getDate().getYear() - s2.getDate().getYear();
			}
			//월이 다르면
			if(s1.getDate().getMonth() != s2.getDate().getMonth()) {
				return s1.getDate().getMonth() - s2.getDate().getMonth();
			}
			return s1.getDate().getDay() - s2.getDate().getDay();
			*/
		});
		
	}

	/** 잔액 계산 메소드 */
	@Override
	public void recalculation(List<Item> list) {
		int totalMoney=0;
		sort(list);
		
		if(list.size() <= 1) {
			return;
		}
		/* 강사 피드백
		 * - sort에서 정렬할 때 totalMoeny를 수입/지출에 맞게 money, -moeny로 설정하게 하면 
		 *   아래 if else문이 필요 없어짐. 
		 * */
		if(list.get(0).isInMoney() && !list.get(0).isOutMoney()) {
			list.get(0).setTotalMoney(list.get(0).getMoney());
		}else{
			totalMoney -= list.get(0).getMoney();
			list.get(0).setTotalMoney(-list.get(0).getMoney());
		}
		
		for(int i=1; i<list.size(); i++) {
			if(list.get(i).isInMoney() && !list.get(i).isOutMoney()) {
				totalMoney = list.get(i-1).getTotalMoney() + list.get(i).getMoney();
			}else {
				totalMoney = list.get(i-1).getTotalMoney() - list.get(i).getMoney();
			}
			list.get(i).setTotalMoney(totalMoney);
		}
			
		
	}


	


	


	

}

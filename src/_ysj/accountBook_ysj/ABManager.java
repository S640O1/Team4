package _ysj.accountBook_ysj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ABManager {
	private static List<AccountBook> list = new ArrayList<AccountBook>();
	private static List<AccountBook> income = new ArrayList<AccountBook>();
	private static List<AccountBook> expense = new ArrayList<AccountBook>();
	private static AccountBook ab = new AccountBook();
	private static Scanner sc = new Scanner(System.in);
	private static Date date = new Date();
	private static int num; //순서
	
		//1. 가계부 입력 - 수입, 지출 항목 입력
<<<<<<< Updated upstream
		void insertMoney() {
=======
		void insertAccountBook() {
>>>>>>> Stashed changes
			System.out.println("[가계부 입력]");
			System.out.println("1. 수입");
			System.out.println("2. 지출");
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1 : 
				String ymd = ab.simpleFormat.format(date);
				String classify = "수입";
				//순서 
				for(int num=0;num<list.size();num++) {
					if(list.get(num) == null) {
						System.out.print(++num);
					}	
				}
				System.out.print("입금 금액 : ");
				sc.nextLine();
				int money = sc.nextInt();
				System.out.print("내역 : ");
				sc.nextLine();
				String memo = sc.nextLine();
				
				AccountBook plus = new AccountBook(num, ymd, classify, money, memo);
				list.add(plus);
				break;
				
			case 2 : 
				ymd = ab.simpleFormat.format(date);
				classify = "지출";
				//순서
				for(int num=0;num<list.size();num++) {
					if(list.get(num) == null) {
						System.out.print(++num);
					}	
				}
				System.out.print("입금 금액 : ");
				sc.nextLine();
				money = sc.nextInt();
				System.out.print("내역 : ");
				sc.nextLine();
				memo = sc.nextLine();
				
				AccountBook use = new AccountBook(num, ymd, classify, money, memo);
				list.add(use);
				break;
				
			default : System.out.println("없는 메뉴입니다.");
			}
		}
<<<<<<< Updated upstream

		//2. 가계부 조회 - 하루, 주간, 월간, 연간
		//메뉴를 선택하면 수입, 지출, 전체 내역 다 보임.
		void printMoney() {
=======
		//*1. 가계부 입력 - 1. - 금액 입력시 문자를 잘못 입력했을때, 다시 1.로 돌아갈 때 잘못된 메뉴라고만 뜸.
		
		
		//2. 가계부 조회 - 하루, 주간, 월간, 연간
		//메뉴를 선택하면 수입, 지출, 전체 내역 다 보임.
		void printAccountBook() {
>>>>>>> Stashed changes
			System.out.println("[가계부 조회]");
			System.out.println("1. 하루");
			System.out.println("2. 주간");
			System.out.println("3. 월간");
			System.out.println("4. 연간");
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			
			switch(menu) {
				//하루
			case 1 : 
				System.out.println("조회 연도 : ");
				int year = sc.nextInt();
				System.out.println("조회 월 : ");
				int month = sc.nextInt();
				System.out.println("조회 일 : ");
				int day = sc.nextInt();
				
				
				//수입, 지출 구분해서 넣어주는 리스트
				income = new ArrayList<AccountBook>();
				expense = new ArrayList<AccountBook>();
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getClassify().equals("수입")) {
						income.add(list.get(i));
					} else if (list.get(i).getClassify().equals("지출")) {
						expense.add(list.get(i));
					}
				}
				
				//일간 수입, 지출, 전체 금액
				//수입 incomeList();
				List<AccountBook> incomeList = new ArrayList<AccountBook>();
				for(int i=0;i<income.size();i++) {
					int checkYear = income.get(i).getYearFormat().hashCode();
					int checkMonth = income.get(i).getMonthFormat().hashCode();
					int checkDay = income.get(i).getDayFormat().hashCode();
					if(checkYear==year && checkMonth==month && checkDay==day) {
						incomeList.add(income.get(i));
					}
				}
				for(AccountBook tmp : incomeList) {
					System.out.println(tmp);
				}
				
				//지출 expenseList();
				List<AccountBook> expenseList = new ArrayList<AccountBook>();
				for(int i=0;i<income.size();i++) {
					int checkYear = income.get(i).getYearFormat().hashCode();
					int checkMonth = income.get(i).getMonthFormat().hashCode();
					int checkDay = income.get(i).getDayFormat().hashCode();
					if(checkYear==year && checkMonth==month && checkDay==day) {
						incomeList.add(income.get(i));
					}
				}
				for(AccountBook tmp : expenseList) {
					tmp.print();
				}
				
				//전체 list();
				for(int i=0;i<list.size();i++) {
					int checkYear = list.get(i).getYearFormat().hashCode();
					int checkMonth = list.get(i).getMonthFormat().hashCode();
					int checkDay = list.get(i).getDayFormat().hashCode();
					if(checkYear==year && checkMonth==month && checkDay==day) {
						incomeList.add(list.get(i));
					}
				}
				for(AccountBook tmp : list) {
					tmp.print();
				}
				
				break;
				
				//주간
			case 2 : 
				break;
				
				//월간
			case 3 : 
				break;
				
				//연간
			case 4 : 
				break;
				
			default : System.out.println("없는 메뉴입니다.");
			}
			
		}
<<<<<<< Updated upstream

		//3. 가계부 수정 1)수입/지출 여부 2)년 월 일 3)사용금액 4)잔액 5)내역 6)전체수정
		void updateMoney() {
			
		}

		//4. 가계부 삭제
		void deleteMoney() {
			
		}

		//5. 현재 잔액 조회
		void currentMoney() {
			
=======
		
		

		//3. 가계부 수정 1)수입/지출 여부 2)년 월 일 3)사용금액 4)내역 5)전체수정
		void updateAccountBook() {
			System.out.println("[가계부 수정]");
			System.out.println("1. 수입/지출 여부");
			System.out.println("2. 연/월/일");
			System.out.println("3. 금액");	//money
			System.out.println("4. 내역");	//memo
			System.out.println("5. 전체 수정");
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1 : 
				updateClassify();
				break;
				
			case 2 : 
				updateYMD();
				break;
				
			case 3 : 
				updateMoney();
				break;
				
			case 4 : 
				updateMemo();
				break;
				
			case 5 : 
				updateList();
				break;
				
			default : System.out.println("없는 메뉴입니다.");
			}
			
		}

		//3-1. 수입/지출 여부
		private void updateClassify() {
			for(AccountBook tmp : list) {
				ab.print();
			}
			int menu;
			System.out.println("[수입/지출 여부]");
			System.out.println("1. 수입 -> 지출");
			System.out.println("2. 지출 -> 수입");
			menu = sc.nextInt();

			if(menu == 1) {
				
			}
		}

		//3-2. 연/월/일
		private void updateYMD() {
			
		}

		//3-3. 금액(money)
		private void updateMoney() {
			
		}

		//3-4. 내역(memo)
		private void updateMemo() {
			
		}

		//3-5. 전체 수정(list)
		private void updateList() {
			
		}

		
		
		//4. 가계부 삭제
		void deleteAccountBook() {
		
		}

		//5. 현재 잔액 조회
		void currentAccountBook() {
			for(AccountBook tmp : list) {
				ab.print();
			}
>>>>>>> Stashed changes
		}
}

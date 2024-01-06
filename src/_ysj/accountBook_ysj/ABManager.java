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
		void insertMoney() {
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

		//2. 가계부 조회 - 하루, 주간, 월간, 연간
		//메뉴를 선택하면 수입, 지출, 전체 내역 다 보임.
		void printMoney() {
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

		//3. 가계부 수정 1)수입/지출 여부 2)년 월 일 3)사용금액 4)잔액 5)내역 6)전체수정
		void updateMoney() {
			
		}

		//4. 가계부 삭제
		void deleteMoney() {
			
		}

		//5. 현재 잔액 조회
		void currentMoney() {
			
		}
}

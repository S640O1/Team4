package accountBook_sny;

public class AccountBookMain {

	/* 가계부 프로그램을 작성하세요.
	 * 기한 1/12. 팀 과제
	 * 
	 * 	- (년, 월, 일), 사용금액, 수입(0)/지출(1), 잔액, 내역
		 	- 기능
		 		1. 수입, 지출 항목 입력
		 			1. 수입
		 				날짜 (yyyy-MM-dd) : 
		 				입금금액 : 
		 				내역 : 
		 			2. 지출
		 				날짜 (yyyy-MM-dd) : 
		 				지출금액 : 
		 				내역 : 
					기록되었습니다.
		 			//sort();
		 		2. 조회
		 			- 하루 조회
		 				날짜 (yyyy-MM-dd) : 
			 				-수입
			 				-지출
			 				-전체
		 			- 주간 조회 (난이도 있음)
		 				날짜 (yyyy-MM-W) : 
		 				-수입
		 				-지출
		 				-전체
		 			- 월간 조회
		 				월 (yyyy-MM) :
			 				-수입
			 				-지출
			 				-전체
		 			- 연간 조회
		 				년(yyyy) : 
			 				-수입
			 				-지출
			 				-전체
		 				
		 		3. 데이터 수정	
		 			(전체 목록 보여줌)
		 			(순서) (수입or지출) (년 월 일) (사용금액) (잔액) (내역)
		 			
		 			( 어떤 항목을 수정하시겠습니까? : 1) (순서번호를 입력)
		
		 			1. 수입/지출 여부
		 				if(만약 수입(1)이라면){
		 					classify=2;
		 					"지출로 변경되었습니다."
		 				}else if(만약 지출(2)이라면){
		 					classify=1;
		 					"수입로 변경되었습니다."
	 					}
	 					
		 			2. 년 월 일
		 				날짜 (yyyy-MM-dd) : 
		 				"날짜가 변경되었습니다."
		 			3. 사용금액
		 				금액 : 
		 				"금액이 변경되었습니다."
		 			4. 잔액
		 				잔액 : 
		 				"잔액이 변경되었습니다."
		 			5. 내역
		 				내역 : 
		 				"내역이 변경되었습니다."
		 			6. 전체수정
		 				1. 수입
			 				날짜 (yyyy-MM-dd) : 
			 				입금금액 : 
			 				내역 : 
		 				2. 지출
			 				날짜 (yyyy-MM-dd) : 
			 				지출금액 : 
			 				내역 : 
		 			( 어떤 내용을 수정하시겠습니까? : )
		 			
		 			//sort();
		 		4. 데이터 삭제
		 			(전체 목록 보여줌)
		 			(순서) (수입/지출) (년 월 일) (사용금액) (잔액) (내역)
		 			
		 			( 어떤 항목을 삭제하시겠습니까? : 1) (순서번호를 입력)
		 			( 정말 삭제하시겠습니까?(네(1) 아니요(2)) : 1) //아니요를 누르면 run()으로 돌아감
		 			( 삭제되었습니다. )
		 		5. 현재 잔액
		 			(현재 잔액은 -50000원 입니다.)
		
				6. 종료
		+) 
		sort();
		1. 잔액이 일정 금액 이하가 되면 경고 알림을 준다.
		2. 월 수입 대비 지출금액
	 * */
	
	public static void main(String[] args) {
		ABProgram p = new ABProgram();
		p.run();
	}

}

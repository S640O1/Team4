package _ysj.accountBook_ysj2;

import java.io.Serializable;

public class Item implements Serializable{

	private static final long serialVersionUID = 6132810887038327706L;

	//사용금액, 들어온 금액(?), 잔액, 일자
	private int useMoney, plusMoney, totalMoney, date; //date (yyyyMMdd)
	//수입 / 지출
	private boolean inMoney, outMoney;
	//내역
	private String memo;

	@Override
	public String toString() {//번호. 수입/지출 yyyyMMdd 사용금액/들어온금액 내역
		//수입일때
		if(inMoney && !outMoney) {
			return inMoney + " " + date + " " + plusMoney + " " + memo;
		}
		//지출일때
		else {			
			return outMoney + " " + date + " " + useMoney + memo;
		}
	}
	
	
	
}
package _ysj.accountBook_ysj2;

import java.io.Serializable;

import lombok.Data;

//지출
@Data
public class OutMoney implements Serializable {

	private static final long serialVersionUID = 25042759432977540L;
	//순서, 사용금액, 잔액, 일자
	private int num, useMoney, totalMoney, date; //date (yyyyMMdd) - int로 받기위해
	//지출
	private boolean outMoney;
	//내역
	private String memo;
	
	//지출 출력
	@Override
	public String toString() {
		return num + ". " + date + " " + outMoney + " " + useMoney + " " + totalMoney + " " + memo;
	}
}
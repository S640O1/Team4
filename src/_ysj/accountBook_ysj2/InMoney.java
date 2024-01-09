package _ysj.accountBook_ysj2;

import java.io.Serializable;

import lombok.Data;

//수입
@Data
public class InMoney implements Serializable {

	private static final long serialVersionUID = -7578575179046296962L;
	//순서, 들어온 금액(?), 잔액, 일자
	private int num, plusMoney, totalMoney, date; //date (yyyyMMdd) - int로 받기위해
	//수입
	private boolean inMoney;
	//내역
	private String memo;
	
	//수입 출력
	@Override
	public String toString() {
		return num + ". " + date + " " + inMoney + " " + plusMoney + " " + totalMoney + " " + memo;
	}
	
}
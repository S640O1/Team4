package accountBook;

import java.io.Serializable;

import lombok.Data;

@Data
public class Item implements Serializable{

	private static final long serialVersionUID = 6132810887038327706L;

	//사용금액, 잔액, 일자
	private int money, totalMoney, date; //date (yyyyMMdd) 
	
	//수입 / 지출
	private boolean inMoney, outMoney;
	
	//내역
	private String memo;

	/** 순번을 받아와서
		list.get(0).toString(list.indexOf(ab));
	 * @param num : 현재 위치값
	 * @return
	 */
	public String toString(int num) {
		num += 1;
		if(inMoney && !outMoney) {
			return num + "번, 일자 : " + date +", 수입 : " + money + "원, 총액 : " + totalMoney + "메모 : " + memo;
		}else {
			return num + "번, 일자 : " + date +", 지출 : " + money + "원, 총액 : " + totalMoney + "메모 : " + memo;
		}
	}	
	
	

}

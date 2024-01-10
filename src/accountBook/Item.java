package accountBook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Item implements Serializable{

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	private static final long serialVersionUID = 6132810887038327706L;
	//사용금액, 잔액, 일자
	private int money, totalMoney; //date (yyyy-MM-dd) 
	
	//수입 / 지출
	private boolean inMoney, outMoney;
	
	//내역
	private String memo;

	Date date = new Date();
	
	/** 순번을 받아와서
		list.get(0).toString(list.indexOf(ab));
	 * @param num : 현재 위치값
	 * @return
	 */
	public String toString(int num) {
		num += 1;
		if(inMoney && !outMoney) {
			return num + "번, 일자 : " +  format1.format(date) +", 수입 : " + money + "원, 총액 : " + totalMoney + "메모 : " + memo;
		}else {
			return num + "번, 일자 : " +  format1.format(date)  +", 지출 : " + money + "원, 총액 : " + totalMoney + "메모 : " + memo;
		}
	}

	public Item(int money, int totalMoney, Date date, boolean inMoney, boolean outMoney, String memo) {
		super();
		this.money = money;
		this.totalMoney = totalMoney;
		this.date = date;
		this.inMoney = inMoney;
		this.outMoney = outMoney;
		this.memo = memo;
	}

	public void setDate(Date date) {
		this.date = date;
		
	}

	
	

}

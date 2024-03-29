package _sgj.accountBook_sgj2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.Data;

@Data
public class Item implements Serializable{


	private static final long serialVersionUID = 6132810887038327706L;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	private int year,month,day;
	
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
		
		String printNum = String.format("%4d번", num);
		String printMoney = String.format(" %,10d원", money);
		String printTotalMoney = String.format(" %,10d원", totalMoney);
		
		
		if(inMoney && !outMoney) {
			return printNum + "    수입     " + format1.format(date)
			+ printMoney + printTotalMoney + "     "+ memo;
		}else if(!inMoney && outMoney){
			return printNum + "    지출     " + format1.format(date)
			+ printMoney + printTotalMoney + "     "+ memo;
		}else {
			return "파일이 잘못되었습니다.";
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH) + 1;
		this.day = cal.get(Calendar.DAY_OF_MONTH);
	}
	

	public void setDate(Date date) {
		this.date = date;
		
	}


	@Override
	public String toString() {
		return "Item [format1=" + format1 + ", money=" + money + ", totalMoney=" + totalMoney + ", inMoney=" + inMoney
				+ ", outMoney=" + outMoney + ", memo=" + memo + ", date=" + date + "]";
	}

	
	
	

}

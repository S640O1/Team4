package accountBook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Item implements Serializable{
	
	/* 강사 피드백
	 * - 날짜 변환을 위해 SimpleDateFormat을 멤버로 하기보단 패턴을 멤버로 한 후, 
	 *   지역변수로 SimpleDateFormat을 이용하는 방법도 있음
	 * public final static String datePattern = "yyyy-MM-dd";
	 * - 위와 같이 선언하면 Item.datePattern을 통해 다른 클래스에서도 Item 클래스의 날짜 패턴을 알 수 있음.*/

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	private static final long serialVersionUID = 6132810887038327706L;
	//사용금액, 잔액, 일자
	private int money, totalMoney; //date (yyyy-MM-dd) 
	
	//수입 / 지출
	/* 강사 피드백
	 * - 수입/지출 여부를 하나의 변수로 해서, in = true이면 수입, in이 false이면 지출로 활용 가능 */
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
		String printMoney;
		String printTotalMoney = String.format(" %,10d원", totalMoney);
		
		/* 강사 피드백
		 * - 위에서 하나의 변수로 한다면 else가 필요 없어짐
		 * - 앞에 수입, 지출을 표시하기 때문에 지출을 나타내기 위해 -를 표현할 필요가 없지 않은까요?
		 * - 그렇다면 조건 여산자를 이용해 코드를 간결하게 변경할 수 있음 
		 * String type = isIn?"수입" : "지출";
		 * printMoney = String.format("  %,10d원", money);
			return printNum + "    "+type+"     " + format1.format(date)
			+ printMoney + printTotalMoney + "     "+ memo;
		 * 
		 * */
		
		if(inMoney && !outMoney) {
			printMoney = String.format("  %,10d원", money);
			return printNum + "    수입     " + format1.format(date)
			+ printMoney + printTotalMoney + "     "+ memo;
		}else if(!inMoney && outMoney){
			printMoney = String.format(" -%,10d원", money);
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
	}
	

	public void setDate(Date date) {
		this.date = date;
		
	}


	
	

}

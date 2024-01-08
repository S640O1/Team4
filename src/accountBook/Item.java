package accountBook;

import java.io.Serializable;

public class Item implements Serializable{

	private static final long serialVersionUID = 6132810887038327706L;

	//사용금액, 잔액, 일자
	private int useMoney, totalMoney, date; //date (yyyyMMdd)
	
	//수입 / 지출
	private boolean inMoney, outMoney;
	
	//내역
	private String memo;	

}

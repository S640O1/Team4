package accountBook_ysj;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Data;

@Data
public class AccountBook {
	//- (년, 월, 일), 사용금액, 수입(0)/지출(1), 잔액, 내역
	
	Date date = new Date();
	//년 월 일
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일",Locale.KOREAN);
	//년 월 주
	SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월 W주");
	//년
	SimpleDateFormat format3 = new SimpleDateFormat("yyyy년");
	
	//사용금액, 잔액, 수입/지출
	private int useMoney, totalMoney, classify;
	
	//내역
	private String memo;


}

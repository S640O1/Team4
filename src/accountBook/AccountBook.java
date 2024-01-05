package accountBook;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountBook {
	//- (년, 월, 일), 사용금액, 수입(0)/지출(1), 잔액, 내역

	Date date = new Date();
	//년 월 일
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일",Locale.KOREAN);
	//년 월 주
	SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월 W주");
	//년
	SimpleDateFormat format3 = new SimpleDateFormat("yyyy년");
	
	//년, 월, 일
	@NonNull
	private LocalDate localDate;
	
	//금액
	@NonNull
	private int money;
	
	//총 잔액
	@NonNull
	private int totalMoney;
	
	//내역, 수입/지출
	@NonNull
	private String memo, classify;
	
	public AccountBook(LocalDate localDate, int money, String memo) {
		this.localDate = localDate;
		this.money = money;
		this.memo = memo;
	}
	
	public AccountBook(String classify) {
		this.classify = classify;
	}

	@Override
	public String toString() {
		return localDate + " 금액 : " + money + " 수입/지출 : "  + classify + " 내역 : " + memo;
	}
	
}

package _ysj.accountBook_ysj;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< Updated upstream
public class AccountBook {
=======
public class AccountBook implements Serializable {
	private static final long serialVersionUID = 7943507860978054358L;
	
>>>>>>> Stashed changes
	//- (년, 월, 일), 사용금액, 수입(0)/지출(1), 잔액, 내역
	Date date = new Date();
	//년
	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy년");
	String year = yearFormat.format(date);
	//월
	SimpleDateFormat monthFormat = new SimpleDateFormat("MM월");
	String month = monthFormat.format(date);
	//주
	SimpleDateFormat weekFormat = new SimpleDateFormat("W주");
	String week = weekFormat.format(date);
	//일
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd일");
	String day = dayFormat.format(date);
	
	//년, 월, 일
	SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy MM dd");
	String ymd = simpleFormat.format(date);
	
	//금액, 총 잔액
	@NonNull
	private int money,totalMoney;
	
	//순서
	@NonNull
	private int num;
	
	//내역, 수입/지출
	@NonNull
	private String memo, classify;
	
	public void print() {
		System.out.println(num + ". " + year + month + day 
				+ "  수입/지출 : " + classify + ", 금액 : " + money + ", 내역 : " + memo);
	}
	 
	public AccountBook(int num, String ymd, String classify, int money, String memo) {
			this.num = num;
			this.ymd = ymd;
			this.classify = classify;
			this.money = money; 
			this.memo = memo;
		}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountBook other = (AccountBook) obj;
<<<<<<< Updated upstream
		return Objects.equals(day, other.day) && Objects.equals(month, other.month) && Objects.equals(year, other.year);
=======
		return Objects.equals(day, other.day) && Objects.equals(week, other.week) && Objects.equals(month, other.month) && Objects.equals(year, other.year);
>>>>>>> Stashed changes
	}

	@Override
	public int hashCode() {
<<<<<<< Updated upstream
		return Objects.hash(day, month, year);
=======
		return Objects.hash(day, week, month, year);
>>>>>>> Stashed changes
	}

	
	
}

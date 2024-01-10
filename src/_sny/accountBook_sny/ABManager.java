package _sny.accountBook_sny;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ABManager {

	private List<AccountBook> list = new ArrayList<AccountBook>();
	static String fileName = "src/accountBook/accountBookList.txt";
	
	public void insertMoney(AccountBook ab) {
		list.add(ab);
		sort();
	}

	private void sort() {
		//람다식으로 익명클래스 객체를 만들어 넣어줌
//		list.sort((s1,s2)->{
//			return;
//		});
		
	}

	public void printAccountBook() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("  순번  수입/지출     일자         금액       잔액        내역");
		System.out.println("----------------------------------------------------------------------");
		
		String ClassifyS ="";
		
		try {
			for(AccountBook ab : list) {
				if(ab.getClassify()==0) {
					ClassifyS ="수입";
				}else if(ab.getClassify() ==1) {
					ClassifyS = "지출";
				}
				int index = list.indexOf(ab)+1;
				
				System.out.println("  " + index + "       "
								+ ClassifyS + "     " +ab.getDateString() + "     "
								+ ab.getUseMoney() + "     " + ab.getTotalMoney() 
								+ "       " + ab.getMemo());	
			}
		}catch (Exception e) {
			System.out.println("등록된 가계부가 없습니다.");
		}
		System.out.println("----------------------------------------------------------------------");

	}

	public void updateAB(int num, AccountBook ab) {
		int index = num-1;
		
		list.get(index).setUseMoney(ab.getUseMoney());
		list.get(index).setTotalMoney(ab.getTotalMoney());
		list.get(index).setClassify(ab.getClassify());
		list.get(index).setDateString(ab.getDateString());
		list.get(index).setMemo(ab.getMemo());
		
	}

	public void load() {
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<AccountBook>)ois.readObject();
			System.out.println("가계부를 불러왔습니다.");
		}catch (Exception e) {
			System.out.println("불러오기에 실패했습니다.");
		}
	}

	public void save() {
		//게시글을 파일에 저장
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
			System.out.println("저장을 성공했습니다.");
		}
		catch (IOException e) {
			System.out.println("저장에 실패했습니다.");
		}		
	}
	
	public void save2() {
		//게시글을 파일에 저장
		try(FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
		}
		catch (IOException e) {
		}		
	}


}

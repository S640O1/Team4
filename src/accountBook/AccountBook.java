package accountBook;

import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class AccountBook {	//가계부(내역 리스트)
	
	private List<Item> list; 	//가계부 리스트
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountBook other = (AccountBook) obj;
		return Objects.equals(list, other.list);
	}
	@Override
	public int hashCode() {
		return Objects.hash(list);
	}
	
	//배열 출력 메서드
	public void print() {
		System.out.println();
	}
	
	//배열을 삭제하는 메서드
	public boolean removeAccountBook(int num) {
		//가계부 리스트가 비어있으면
		if(list == null) {
			return false;
		}
		//삭제 후 삭제 여부를 리턴
		//오류 return list.remove(num);
		return true;
	}
}

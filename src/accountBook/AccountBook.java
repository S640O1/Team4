package accountBook;

import java.util.List;

import lombok.Data;

@Data
public class AccountBook {	//가계부(내역 리스트)
	
	private List<Item> list; 	//가계부 리스트
 
}
package university;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class University {
	

	
	private List<Professor> list;	//교수 리스트
	

	public University(List<Professor> list) {
		if(list == null) {
			list = new ArrayList<Professor>();
		}
		this.list = list;
	}
	
	/** 교수 추가 기능 */
	
	/** 교수 수정 기능 */
	
	/** 교수 삭제 기능 */
}

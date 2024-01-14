package university;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class University {
	
	//제안 : 통합이 아니라 각 데이터 파일을 따로 만드는것은 어떨까
	
	private List<Professor> list;	//교수 리스트
	
	//모든 list가 null이지 않을 때만 University가 생성되어 문제가 발생하지 않을까?
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

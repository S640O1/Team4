package university;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class University {
	

	private List<Student> sList;	//학생 리스트
	private List<Professor> pList;	//교수 리스트
	private List<Department> dList;	//강의 리스트
	private List<Lecture> lList;	//학과 리스트
	
	public University(List<Student> sList, List<Professor> pList, List<Department> dList, List<Lecture> lList) {
		this.sList = sList;
		this.pList = pList;
		this.dList = dList;
		this.lList = lList;
	}
	
	
	
	



	/** 교수 추가 기능 */
	
	/** 교수 수정 기능 */

	/** 교수 삭제 기능 */
}

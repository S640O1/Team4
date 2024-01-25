package university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/*학과 클래스 : 신경재*/

//학과 분류 번호, 학과명 

@Data
//@AllArgsConstructor
public class Department implements Serializable{
	
	private static final long serialVersionUID = -4403777244245105266L;
	//학과 분류 번호
	int dpNum;
	//학과명
	String dpName;
	//학생리스트
	List<Student> sList;
	//교수리스트
	List<Professor> pList;
	
	// 생성자에 초기화 코드 추가
	public Department(int dpNum, String dpName, List<Student> sList, List<Professor> pList) {
        this.dpNum = dpNum;
        this.dpName = dpName;
        if(sList == null || sList.size() ==0) {
			this.sList = new ArrayList<Student>();
		}
        if(pList == null || pList.size() ==0) {
			this.pList = new ArrayList<Professor>();
		}
    }
	
	@Override
	public String toString() {
		return "\t["+ dpNum +"]\t"+ dpName  ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(dpName, other.dpName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dpName);
	}
	
	


}

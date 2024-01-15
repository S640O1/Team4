package university;

import lombok.AllArgsConstructor;
import lombok.Data;

/*학과 클래스 : 신경재*/

//학과 분류 번호, 학과명 

@Data
@AllArgsConstructor
public class Department {
	
	//학과 분류 번호
	int dpNum;
	//학과명
	String dpName;
	
	@Override
	public String toString() {
		return "Department [dpName=" + dpName + "]";
	}


}

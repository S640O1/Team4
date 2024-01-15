package university;

import lombok.AllArgsConstructor;
import lombok.Data;

/*학과 클래스 : 신경재*/

//학과명 

@Data
@AllArgsConstructor
public class Department {
	
	//학과명
	String deparmentName;

	@Override
	public String toString() {
		return "Department [deparmentName=" + deparmentName + "]";
	}
}

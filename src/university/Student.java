package university;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/* 학생 클래스 : 양선진
 * 기본기능 - 학생 관리 - 학생 등록/수정/삭제 
 * 학생 : 학번, 이름, 학과, 연락처
 * 수강 과목? -> lecture에 넣는게 맞는거 같음. 학생 클래스에는 학생에 대한 정보만
 * 성별은 삭제 
 * 시리얼넘버도 넣어야되나?
 * 연락처 -> 앞의 010 때문에 int, long에 안들어감 -> String으로 넣음*/

@Data
@AllArgsConstructor
public class Student {
	 private int studentId;//학번, 연락처(01012345678 식으로 출력)
	 private String name, phoneNumber, department;	//이름, 연락처, 학과
	 
	 //학번, 이름이 같다면
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name) && studentId == other.studentId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, studentId);
	}
	
	/* 학번 이름
	 * 학과 : 학과명
	 * 연락처 : 01012345678 */
	@Override
	public String toString() {
		return studentId + " " + name + "\n학과 : " + department + "\n연락처 : " + phoneNumber;
	}
	 
}

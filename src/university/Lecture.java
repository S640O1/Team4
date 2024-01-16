package university;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*강의 클래스 : 심아진*/

// 강의 번호, 강의명, 담당교수, 인원, 강의 시간, 강의실 + 성적

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecture  implements Serializable {

	private static final long serialVersionUID = 9088622124125979149L;
	
	int lectureNum, maxNum  ;	// 강의 번호(학수 번호), 수강가능인원
	String lectureName, pNum, pName, lectureRoom;		// 강의명, 교번, 교수이름, 강의실
	Date date = new Date(); // 강의 시간
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecture other = (Lecture) obj;
		return Objects.equals(date, other.date) && Objects.equals(lectureName, other.lectureName)
				&& lectureNum == other.lectureNum && Objects.equals(lectureRoom, other.lectureRoom)
				&& Objects.equals(pName, other.pName) && maxNum == other.maxNum;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, lectureName, lectureNum, lectureRoom, pName, maxNum);
	}
	
	
	
	
	
}

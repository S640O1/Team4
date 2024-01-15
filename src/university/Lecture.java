package university;

import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*강의 클래스 : 심아진*/

// 강의 번호, 강의명, 담당교수, 인원, 강의 시간, 강의실

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {

	int lectureNum, personnel  ;
	String lectureName, pName, lectureRoom;
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
				&& Objects.equals(pName, other.pName) && personnel == other.personnel;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, lectureName, lectureNum, lectureRoom, pName, personnel);
	}
	
	
}

package university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import university.service.LectureServiceImp;

/*강의 클래스 : 심아진*/

// 강의 번호, 강의명, 담당교수, 인원, 강의 시간, 강의실 + 성적

@Data
//@AllArgsConstructor
public class Lecture  implements Serializable {

	private static final long serialVersionUID = 9088622124125979149L;
	
	int lectureNum, maxNum, pNum  ;	// 강의 번호(학수 번호), 수강가능인원
	String lectureName, pName, lectureRoom;		// 강의명, 교번, 교수이름, 강의실
	Date date = new Date(); // 강의 시간
	List<Student> students;
	

	/*
	 강의 번호. 강의명 && 시간 (담당교수 : ㅇㅇㅇ) (현재인원/최대인원) 강의실  
	 
	 */
	
	@Override
	public String toString() {

		return lectureNum + "." + lectureName + " [" + LectureServiceImp.format1.format(date) + "] 담당 교수 : " +  pName + " ("+students.size() +" / " + maxNum + ") 강의실 : " + lectureRoom;
	}

	public Lecture(int lectureNum, int maxNum, int pNum, String lectureName, String pName, String lectureRoom,
			Date date, List<Student> students) {
		super();
		this.lectureNum = lectureNum;
		this.maxNum = maxNum;
		this.pNum = pNum;
		this.lectureName = lectureName;
		this.pName = pName;
		this.lectureRoom = lectureRoom;
		this.date = date;
		if(students == null || students.size() ==0) {
			this.students = new ArrayList<Student>();
		}
	}

	// 강의 번호, 강의명, 교수번호, 교수이름, 강의시간, 강의실이 같은 경우
	
	

	@Override
	public int hashCode() {
		return Objects.hash(lectureNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecture other = (Lecture) obj;
		return lectureNum == other.lectureNum;
	}



	
	
	
	
}

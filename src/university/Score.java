package university;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score implements Serializable{
	private static final long serialVersionUID = -5049220704660946774L;
	
	//점수, 강의명, 학생아이디
	int lectureNum, stdId;
	double score;
	String lectureName, stdName;
	
	
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return lectureNum == other.lectureNum && stdId == other.stdId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lectureNum, stdId);
	}
	
	@Override
	public String toString() {
		String printLectureName = String.format("%10s", lectureName);
		String printStdName = String.format("%5s", stdName);
		String printScore = String.format("%.2f", score);
		return "[" + printLectureName + "] " + printStdName + " : " + printScore + "점";
	}

}

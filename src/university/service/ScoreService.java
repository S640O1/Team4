package university.service;

import java.util.List;

import university.Lecture;
import university.Student;

public interface ScoreService {
	
	//성적 등록
	public void addScore(List<Student> sList, List<Lecture> lList);
	
	//평균 학점 조회
	public void showStudentStandardScore(List<Student> sList);
	
	//강의 학점 조회
	public void showStudentLectureScore(List<Student> sList);
	
	// 성적 수정
	public void updateScore(List<Student>sList, List<Lecture> lList);
}

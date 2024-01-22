package university.service;

import java.util.List;

import university.Student;

public interface ScoreService {
	
	//평균 학점 조회
	public void showStudentStandardScore(List<Student> sList);
	
	//강의 학점 조회
	public void showStudentLectureScore(List<Student> sList);
}

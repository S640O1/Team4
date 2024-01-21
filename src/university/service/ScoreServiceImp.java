package university.service;

import java.util.List;

import university.Student;
import university.UniversityProgram;

public class ScoreServiceImp implements ScoreService {
	
	private StudentService studentService = new StudentServiceImp();
	private int index;

	//평균
	@Override
	public void showStudentScore(List<Student> sList) {
		studentService.printStudentList(sList);
		System.out.println("학점을 볼 학생 선택 : ");
		index = UniversityProgram.scan.nextInt() - 1;
		//입력한 인덱스 학생의 평균 보여주기
		sList.get(index).getLecture();
	}
	
	//각 강의
	@Override
	public void showStudentLectureScore(List<Student> sList) {
		studentService.printStudentList(sList);
		System.out.println("학점을 볼 학생 선택 : ");
		index = UniversityProgram.scan.nextInt() - 1;
		//입력한 인덱스 학생의 각 강의의 학점 보여주기
		sList.get(index).getLecture();
	}

}

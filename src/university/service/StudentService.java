package university.service;

import java.util.List;

import university.Student;

public interface StudentService {

	//학생추가
	public void insertStudent(List<Student> list, Student student);
	
	//학생수정
	public void updateStudent(List<Student> list, Student student);

	//학생삭제
	public void deleteStudent(List<Student> list, Student student);
	
	//학생목록
	public boolean printStudentList(List<Student> list);
	
}

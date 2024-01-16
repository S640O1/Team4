package university.service;

import java.util.List;
import java.util.Scanner;

import university.Student;

public class StudentServiceImp implements StudentService {

	Scanner sc = new Scanner(System.in);
	
	@Override
	public void addStudent(List<Student> list, Student student) {
		System.out.println("추가할 학생 정보를 입력하세요.");
		System.out.println("학번 : ");
		int studentId = sc.nextInt();
		System.out.println("이름 : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("성별(1 : 여성, 2 : 남성) : ");
		int gender = sc.nextInt();
		//번호 잘못치면 return
		if(gender != 1 || gender != 2) {
			System.out.println("없는 번호입니다.");
			return;
		}
		System.out.println("학과 : ");
		sc.nextLine();
		String department = sc.nextLine();
		System.out.println("연락처 : ");
		sc.nextLine();
		String phoneNumber = sc.nextLine();

		Student std = new Student(studentId, gender, name, department, phoneNumber);
		
		//이미있는 학생일때 추가 X (equals = 학번과 이름이 같을때)
		if(list.equals(std)) {
			System.out.println("이미있는 학생입니다.");
			return;
		}
		//등록안되있으면 배열에 추가
		list.add(student);
		System.out.println("등록되었습니다.");
	}

	@Override
	public void setStudent(List<Student> list, Student student) {
		
	}

	@Override
	public void deleteStudent(List<Student> list, Student student) {
		
	}

}

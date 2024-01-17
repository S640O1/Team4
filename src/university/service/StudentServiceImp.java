package university.service;

import java.util.List;
import java.util.Scanner;

import university.Student;

public class StudentServiceImp implements StudentService {

	//정렬(sort) 메서드 어떻게?
	Scanner sc = new Scanner(System.in);
	
	//학생추가
	@Override
	public void insertStudent(List<Student> list, Student student) {
		System.out.println("추가할 학생 정보를 입력하세요.");
		System.out.print("학번 : ");
		int studentId = sc.nextInt();
		System.out.print("이름 : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("성별(m, f) : ");
		char gender = sc.next().charAt(0);
		System.out.print("학과 : ");
		sc.nextLine();
		String department = sc.nextLine();
		System.out.print("연락처 : ");
		String phoneNumber = sc.nextLine();

		Student std = new Student(studentId, name, department, phoneNumber, gender);
		
		//이미있는 학생일때 추가 X (equals = 학번과 이름이 같을때)
		if(list.equals(std)) {
			System.out.println("이미있는 학생입니다.");
			return;
		}
		//등록안되있으면 배열에 추가 후 정렬
		list.add(std);
		System.out.println(list.toString());
		//정렬 메서드
		System.out.println("등록되었습니다.");
	}

	//학생수정
	@Override
	public void updateStudent(List<Student> list, Student student) {
		printStudentList(list);
		//수정할 학생 선택
		System.out.println("어떤 학생 정보를 수정하겠습니까? ");
		int index = sc.nextInt() - 1;
		
		//학생의 어떤 항목을 수정할지 선택
		list.get(index).toString();
		System.out.println("어떤 정보를 수정하겠습니까?"
							+ "\n1. 학번 \n2. 이름 \n3. 성별 \n4. 전공 \n5. 연락처");
		int menu = sc.nextInt();
		switch(menu) {
		case 1 : 
			System.out.println("수정할 학번 : ");
			int studentID = sc.nextInt();
			list.get(index).setStudentId(studentID);
			System.out.println(list.toString());
			System.out.println("수정되었습니다.");
			break;
			
		case 2 : 
			System.out.println("수정할 이름 : ");
			sc.nextLine();
			String name = sc.nextLine();
			list.get(index).setName(name);
			System.out.println("수정되었습니다.");
			break;
			
		case 3 : 
			System.out.println("성별을 바꾸시겠습니까?(y/n)");
			char areYouChange = sc.next().charAt(0);
			if(areYouChange == 'y' || areYouChange == 'Y') {
				if(student.getGender() == 'f') {
					student.setGender('m');
					System.out.println("수정되었습니다.");
					break;
				} else {
					student.setGender('f');
					System.out.println("수정되었습니다.");
					break;
					//set을 했는데 인스턴스를 다시 만들어서 넣어야되나?
				}
			} else if(areYouChange == 'n' || areYouChange == 'N') {
					System.out.println("취소되었습니다.");
					break;
			} else {
				System.out.println("잘못된 문자입니다.");
				break;
			}
			
		case 4 : 
			System.out.println("수정할 전공 : ");
			sc.nextLine();
			String department = sc.nextLine();
			list.get(index).setDepartment(department);
			System.out.println("수정되었습니다.");
			break;
			
		case 5 : 
			System.out.println("수정할 연락처 : ");
			sc.nextLine();
			String phoneNumber = sc.nextLine();
			list.get(index).setPhoneNumber(phoneNumber);
			System.out.println("수정되었습니다.");
			break;
			
		default : System.out.println("잘못된 번호입니다.");
			break;
		}
	}

	//학생삭제
	@Override
	public void deleteStudent(List<Student> list, Student student) {
		//학생 리스트 보여주기(sort 해야되나?)
		printStudentList(list);
		//삭제할 학생정보 입력
		System.out.println("목록 중 어떤 학생 정보를 삭제하시겠습니까? : ");
		int index = sc.nextInt()-1;
		System.out.println("정말로 삭제하시겠습니까? (y/n) ");
		char areYouSure = sc.next().charAt(0);
		if(areYouSure == 'y' || areYouSure == 'Y') {
			list.remove(index);
			System.out.println("삭제되었습니다.");
		} else if(areYouSure == 'n' || areYouSure == 'N') {
			System.out.println("취소되었습니다.");
		} else {
			System.out.println("잘못된 문자를 입력하여 취소되었습니다.");
		}
	}

	//학생목록
	@Override
	public boolean printStudentList(List<Student> list) {
		if(list.isEmpty()) {
			System.out.println("등록된 학생이 없습니다.");
			//왜 return false로 밖에 안나오지???
			return false;
		}
		for(int i=0;i<list.size();i++) {
			list.get(i);
			System.out.println((i+1) + ". " + list.toString());
		}
			return true;
	}
	
}

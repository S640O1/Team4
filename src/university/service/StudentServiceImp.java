package university.service;

import java.util.List;
import java.util.Scanner;

import accountBook.Item;
import university.Student;
import university.UniversityProgram;
import university.Department;

public class StudentServiceImp implements StudentService {

	/* 안되는 거
	 * 1. 학생 등록할 때 2번째 부터 추가하는 학생과 그 전의 학생의 출력이 합쳐진다. 
	 * 2. 성별 수정 때 오류*/
	//정렬(sort) 메서드 어떻게?
	
	//학생추가
	@Override
	public void insertStudent(List<Department> dList, List<Student> list) {
		//학과 목록을 보여주면서 여기서 어떤건지 번호 입력 Student 인스턴스 값에 저장
//		System.out.print("학과 번호 : ");
//		int dpIndex = UniversityProgram.scan.nextInt();
//		//해당 학과가 없으면 return
//		Department department = dList.get(dpIndex);
		
		System.out.println("추가할 학생 정보를 입력하세요.");
		System.out.print("학번 : ");
		int studentId = UniversityProgram.scan.nextInt();
		for(int i=0; i<list.size();i++) {
			if(list.get(i).equals(studentId)) {
				System.out.println("이미있는 학번입니다.");
				return;
			}
		}
		System.out.print("이름 : ");
		UniversityProgram.scan.nextLine();
		String name = UniversityProgram.scan.nextLine();
		System.out.print("성별(m, f) : ");
		char gender = UniversityProgram.scan.next().charAt(0);
		System.out.print("연락처 : ");
		UniversityProgram.scan.nextLine();
		String phoneNumber = UniversityProgram.scan.nextLine();
		
															//null = department
		Student std = new Student(studentId, name, phoneNumber, gender, null, null);

		//이미있는 학생일때 추가 X (equals = 학번이 같을때)
		for(int i=0; i<list.size();i++) {			
			if(list.get(i).equals(std)) {
				System.out.println("이미있는 학생입니다.");
				return;
			}
		}
		//등록안되있으면 배열에 추가 후 정렬
		list.add(std);
		printStudentList(list);
		/* 위의 메서드와 같은 코드
		 * for(int i=0;i<list.size();i++) {
			System.out.println((i+1)+ ". " + list.get(i).toString());
			}
		 * */
		System.out.println("등록되었습니다.");
	}

	//학생수정
	@Override
	public void updateStudent(List<Student> list) {
		//printStudentList(list); 밑의 코드와 겹쳐서 중복으로 나옴
		if(!printStudentList(list)) {
			return; //반환 = 메소드를 종료하고 값을 내놓음, 밑으로 내려가지 않음
					//break는 중괄호 하나만 벗어나고 밑으로 내려감
		}
		//수정할 학생 선택
		System.out.println("어떤 학생 정보를 수정하겠습니까? ");
		int index = UniversityProgram.scan.nextInt() - 1;
		Student std = list.get(index);
		
		//학생의 어떤 항목을 수정할지 선택
//		list.get(index).toString(); printStudentList(list) 메서드에 있는 코드와 이 코드가 의미하는 바가 같아서 콘솔창에 중복으로 뜸
		System.out.println("어떤 정보를 수정하겠습니까?"
							+ "\n1. 학번 \n2. 이름 \n3. 성별 \n4. 전공 \n5. 연락처");
		int menu = UniversityProgram.scan.nextInt();
		switch(menu) {
		case 1 : 
			System.out.println("수정할 학번 : ");
			int studentID = UniversityProgram.scan.nextInt();
			std.setStudentId(studentID);
			System.out.println(std.toString());
			System.out.println("수정되었습니다.");
			break;
			
		case 2 : 
			System.out.println("수정할 이름 : ");
			UniversityProgram.scan.nextLine();
			String name = UniversityProgram.scan.nextLine();
			std.setName(name);
			System.out.println(std.toString());
			System.out.println("수정되었습니다.");
			break;
			
		case 3 : 
			//성별 오류
			System.out.println("성별을 바꾸시겠습니까?(y/n)");
			char areYouChange = UniversityProgram.scan.next().charAt(0);
			if(areYouChange == 'y' || areYouChange == 'Y') {
				if(std.getGender() == 'f') {
					std.setGender('m');
					System.out.println(std.toString());
					System.out.println("수정되었습니다.");
					break;
				} else {
					std.setGender('f');
					System.out.println(std.toString());
					System.out.println("수정되었습니다.");
					break;
					//set을 했는데 인스턴스를 다시 만들어서 넣어야되나? no, set되서 새로운 값이 들어가서 만들 필요 X
				}
			} else if(areYouChange == 'n' || areYouChange == 'N') {
					System.out.println("취소되었습니다.");
					break;
			} else {
				System.out.println("잘못된 문자입니다.");
				break;
			}
			
		case 4 : //학과 수정
			//전공리스트 출력
			
			System.out.println("수정하고싶은 전공 : ");
			int dIndex = UniversityProgram.scan.nextInt()-1;
			Department department = UniversityProgram.dList.get(dIndex);
			//setDepartment를 어떻게 구현해야 할까...
			std.setDepartment(department);
			System.out.println(std.toString());
			System.out.println("수정되었습니다.");
			break;
			
		case 5 : 
			System.out.println("수정할 연락처 : ");
			UniversityProgram.scan.nextLine();
			String phoneNumber = UniversityProgram.scan.nextLine();
			std.setPhoneNumber(phoneNumber);
			System.out.println(std.toString());
			System.out.println("수정되었습니다.");
			break;
			
		default : System.out.println("잘못된 번호입니다.");
			break;
		}
	}

	//학생삭제
	@Override
	public void deleteStudent(List<Student> list) {
		//학생 리스트 보여주기(sort 해야되나?)
		//printStudentList(list);
		if(!printStudentList(list)) {
			return;
		}
		//삭제할 학생정보 입력
		System.out.println("목록 중 어떤 학생 정보를 삭제하시겠습니까? : ");
		int index = UniversityProgram.scan.nextInt()-1;
		System.out.println("정말로 삭제하시겠습니까? (y/n) ");
		char areYouSure = UniversityProgram.scan.next().charAt(0);
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
			return false;
		}
		for(int i=0;i<list.size();i++) {
			list.get(i);
			System.out.println((i+1) + ". " + list.get(i).toString());
		}
			return true;
	}
	
}

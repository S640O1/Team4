package university.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import university.Department;
import university.Professor;
import university.UniversityProgram;

public class ProfessorServiceImp implements ProfessorService {
	
	
	/** 교수정보를 추가하는 메소드*/
	@Override
	public boolean addProfessor(Professor professor) {
		//이미 등록된 교수면(교번, 성별, 이름, 연락처 동일)
		if(UniversityProgram.pList.equals(professor)) {
			return false;
		}
		//새 교수를 등록
		UniversityProgram.pList.add(professor);
		return true;
	}

	/** 교수정보를 수정하는 메소드*/
		/** 수정 : 1. 전화번호 수정 메소드*/
	@Override
	public void setPhoneNum(int index) {
		System.out.print("전화번호 : ");
		String phoneNum = UniversityProgram.scan.next();	
		UniversityProgram.pList.get(index).setPhoneNum(phoneNum);
	}

		/** 수정 : 2. 성별 수정 메소드*/
	@Override
	public void setGender(int index) {
		int gender = UniversityProgram.pList.get(index).getGender()==1 ? 2 : 1;
		UniversityProgram.pList.get(index).setGender(gender);
	}

		/** 수정 : 3. 학과 수정 메소드*/
	@Override
	public void setDepartment(int index) {
		System.out.print("학과 : ");
		UniversityProgram.scan.nextLine();
		String dpName = UniversityProgram.scan.nextLine();
		//만약 학과가 학과 리스트안에 없다면
//			if(.equals("dpName")) {
//				System.out.println("존재하지 않은 학과입니다.");			
//			}
		//있다면 학과 리스트에서 동일한 학과 정보를 저장
		Department department = new Department(0, dpName, null, UniversityProgram.pList);
		
		UniversityProgram.pList.get(index).setDepartment(department);
	}

		/** 수정 : 4. 이름 수정 메소드*/
	@Override
	public void setName(int index) {
		System.out.print("이름 : ");
		String name = UniversityProgram.scan.next();
		UniversityProgram.pList.get(index).setName(name);
	}

		/** 수정 : 5. 교번 수정 메소드*/
	@Override
	public void setNum(int index) {
		System.out.print("교번 : ");
		int num = UniversityProgram.scan.nextInt();
		UniversityProgram.pList.get(index).setNum(num);
	}

	/** 교수정보를 삭제하는 메소드*/
	@Override
	public void deleteProfessor(int index) {
		UniversityProgram.pList.remove(index);
	}







	
}

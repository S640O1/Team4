package university.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import university.Department;
import university.Professor;
import university.UniversityProgram;

public class ProfessorServiceImp implements ProfessorService {
	
	public static List<Professor> pList = UniversityProgram.pList;
	public static Scanner scan = UniversityProgram.scan;
	
	/** 교수정보를 추가하는 메소드*/
	@Override
	public boolean addProfessor(Professor professor) {
		//이미 등록된 교수면(교번, 성별, 이름, 연락처 동일)
		if(pList.equals(professor)) {
			return false;
		}
		//새 교수를 등록
		pList.add(professor);
		return true;
	}

	/** 교수정보를 수정하는 메소드*/
		/** 수정 : 1. 전화번호 수정 메소드*/
	@Override
	public void setPhoneNum(int index) {
		System.out.print("전화번호 : ");
		String phoneNum = scan.next();	
		pList.get(index).setPhoneNum(phoneNum);
	}

		/** 수정 : 2. 성별 수정 메소드*/
	@Override
	public void setGender(int index) {
		int gender = pList.get(index).getGender()==1 ? 2 : 1;
		pList.get(index).setGender(gender);
	}

		/** 수정 : 3. 학과 수정 메소드*/
	@Override
	public void setDepartment(int index, List<Department>  dList) {
		System.out.print("학과 : ");
		scan.nextLine();
		String dpName = scan.nextLine();
		
		//만약 학과가 학과 리스트안에 없다면
		for(int i=0; i<dList.size(); i++){
			if(!(dList.get(i).getDpName() == dpName)) {
				System.out.println("존재하지 않은 학과입니다.");	
				return;
			}
		}
		Department department = new Department(0, dpName, null, pList);
		
		pList.get(index).setDepartment(department);
		System.out.println("학과를 수정했습니다.");
	}

		/** 수정 : 4. 이름 수정 메소드*/
	@Override
	public void setName(int index) {
		System.out.print("이름 : ");
		String name = scan.next();
		pList.get(index).setName(name);
	}

		/** 수정 : 5. 교번 수정 메소드*/
	@Override
	public void setNum(int index) {
		System.out.print("교번 : ");
		int num = scan.nextInt();
		pList.get(index).setNum(num);
	}

	/** 교수정보를 삭제하는 메소드*/
	@Override
	public void deleteProfessor(int index) {
		pList.remove(index);
	}

	/** 교수정보를 조회하는 메소드*/
	@Override
	public boolean printProfessor() {
		if(pList.isEmpty()) {
			System.out.println("등록된 교수가 없습니다.");
			return false;
		}
		
		System.out.println("  교번  |    학과    |  이름  | 성별 |    전화번호    ");
		System.out.println("------------------------------------------------------");
		
//		pList.stream().forEach(p->p.toString());
		for(int i=0; i<pList.size(); i++) {
			System.out.println(pList.get(i).toString());
		}
		return true;
		
	}







	
}

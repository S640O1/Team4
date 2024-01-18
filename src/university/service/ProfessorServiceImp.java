package university.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import university.Department;
import university.Professor;
import university.UniversityProgram;

public class ProfessorServiceImp implements ProfessorService {
	public List<Professor> pList = new ArrayList<Professor>();
	
	private ProfessorService professorService = new ProfessorServiceImp();
	static final int PROFESSOR_EXIT = 4;
	static final int SETPROFESSOR_EXIT = 6;
	
	/** 2. 교수 관리 : 손나영 */
	public void professorManage() {
		int menu = 0;
		do {
			printProfessorMenu();
			try {
				menu = UniversityProgram.scan.nextInt();
				runProfessorMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				UniversityProgram.scan.nextLine();
			}
		} while (menu != PROFESSOR_EXIT);
	}

		/** 교수 : 메뉴실행 */
	private void runProfessorMenu(int menu) {
		switch(menu) {
		case 1: 	//교수등록
			addProfessor();
			//세이브 
			break;
		case 2: 	//교수수정
			setProfessor();
			break;	
		case 3: 	//교수삭제
			delseteProfessor();

			break;
		case 4: 
			break;
		default : 
			throw new InputMismatchException();
		}
	}

		/** 교수 : 1.교수 등록 */
	private void addProfessor() {
		//교수 정보 입력
		//이름, 교번, 성별, 전화번호, 학과
		
		
		System.out.print("학과 : ");
		UniversityProgram.scan.nextLine();
		String dpName = UniversityProgram.scan.nextLine();
		//만약 학과가 학과 리스트안에 없다면
//		if(.equals("dpName")) {
//			System.out.println("존재하지 않은 학과입니다.");			
//		}
		//있다면 학과 리스트에서 동일한 학과 정보를 저장
		Department department = new Department(0, dpName, null, pList);
		
		System.out.print("성함 : ");
		String name = UniversityProgram.scan.next();
		System.out.print("교번 : ");
		int num = UniversityProgram.scan.nextInt();
		System.out.print("전화번호 : ");
		String phoneNum = UniversityProgram.scan.next();
		System.out.print("성별(남:1, 여:2) : ");
		int gender = 0;
		//만약 성별이 1과 2가 아닌 숫자라면 다시 입력
		do{
			gender = UniversityProgram.scan.nextInt();
			System.out.println("잘못된 성별입니다.");
		}while(!(gender==1) || !(gender==2));
		
		Professor professor = new Professor(num, gender, name, phoneNum, department, null);
		if(!professorService.addProfessor(professor)){
			System.out.println("이미 등록된 교수입니다.");
			return;
		}
		System.out.println("교수를 등록했습니다.");
	}
	
		/** 교수 : 2. 교수 수정 */
	private void setProfessor() {
		//교수 목록을 보여줌(조회)
		
		System.out.print("수정할 교수의 교번을 입력하세요 : ");
		int num = UniversityProgram.scan.nextInt();
		
		//해당 교번과 동일한 교수정보 인덱스를 찾아서 삭제진행
		int index = -1;
		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getNum()==num) {
				index = i;
				break;
			}
		}
		//교번이 잘못된 경우
		if(index<0 || index >= pList.size()) {
			System.out.println("잘못된 교번입니다.");
			return;
		}
		
		//수정 메뉴 선택
		int menu = 0;
		do {
			printSetProfessorMenu();
			try {
				menu = UniversityProgram.scan.nextInt();
				runSetProfessorMenu(menu, index);
				break;
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				UniversityProgram.scan.nextLine();
			}
		} while (menu != SETPROFESSOR_EXIT);
	}
	
			/** 교수 : 2. 교수 수정 메뉴실행 */
	private void runSetProfessorMenu(int menu, int index) {
		switch(menu) {
		case 1: 	//교번
			professorService.setNum(index);
			System.out.println("교변을 수정했습니다.");
			break;
		case 2: 	//이름
			professorService.setName(index);
			System.out.println("이름을 수정했습니다.");
			break;	
		case 3: 	//학과
			professorService.setDepartment(index);
			System.out.println("학과를 수정했습니다.");
			break;
		case 4: 	//성별
			professorService.setGender(index);
			System.out.println("성별을 수정했습니다.");
			break;
		case 5:		//전화번호
			professorService.setPhoneNum(index);
			System.out.println("전화번호를 수정했습니다.");
			break; 
		case 6:
			break;
		default : 
			throw new InputMismatchException();
		}
		//세이브
	}
	
			/** 교수 : 2. 교수 수정 메뉴출력 */
	private void printSetProfessorMenu() {
		System.out.println("------교수 정보 수정------");
		System.out.println("1. 교번");
		System.out.println("2. 이름");
		System.out.println("3. 학과");
		System.out.println("4. 성별");
		System.out.println("5. 전화번호");
		System.out.println("6. 뒤로가기");
		System.out.println("---------------");
		System.out.print("수정할 항목을 선택하세요 : ");
	}

		/** 교수 : 3. 교수 삭제 */
	private void delseteProfessor() {
		//교수 목록을 보여줌(조회)


		System.out.print("삭제할 교수의 교번을 입력하세요 : ");
		int num = UniversityProgram.scan.nextInt();

//		해당 교번과 동일한 교수정보 인덱스를 찾아서 삭제진행
		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getNum()==num) {
				professorService.deleteProfessor(i);
				System.out.println("교수 정보를 삭제했습니다.");
				return;
			}
		}
		System.out.println("잘못된 교번입니다.");
		
	}

		/** 교수 : 메뉴출력 */
	private void printProfessorMenu() {
		System.out.println("---------교수관리---------");
		System.out.println("1. 교수 등록");
		System.out.println("2. 교수 정보 수정");
		System.out.println("3. 교수 삭제");
		System.out.println("4. 종료");
		System.out.println("---------------");
		System.out.print("메뉴 선택 : ");
	}

	
//	////////////////////////////////////////////////////////////////////////
	

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
	@Override
	public List<Professor> setProfessor(List<Professor> list, Professor professor) {
		return null;
	}
	
	/** 수정 : 1. 전화번호 수정 메소드*/
	@Override
	public void setPhoneNum(int index) {
		System.out.print("전화번호 : ");
		String phoneNum = UniversityProgram.scan.next();	
		pList.get(index).setPhoneNum(phoneNum);
	}

	@Override
	public void setGender(int index) {
		int gender = pList.get(index).getGender()==1 ? 2 : 1;
		pList.get(index).setGender(gender);
	}

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
		Department department = new Department(0, dpName, null, pList);
		
		pList.get(index).setDepartment(department);
	}

	@Override
	public void setName(int index) {
		System.out.print("성함 : ");
		String name = UniversityProgram.scan.next();
		pList.get(index).setName(name);
	}

	@Override
	public void setNum(int index) {
		System.out.print("교번 : ");
		int num = UniversityProgram.scan.nextInt();
		pList.get(index).setNum(num);
	}


	/** 교수정보를 삭제하는 메소드*/
	@Override
	public void deleteProfessor(int index) {
		pList.remove(index);
	}





	
}

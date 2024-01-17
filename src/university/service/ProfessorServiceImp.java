package university.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import university.Department;
import university.Professor;

public class ProfessorServiceImp implements ProfessorService {
	private Scanner scan = new Scanner(System.in);
	static final int PROFESSOR_EXIT = 4;
	
	public void professorManage() {
		int menu = 0;
		do {
			printProfessorMenu();
			try {
				menu = scan.nextInt();
				runProfessorMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != PROFESSOR_EXIT);
	}

	private void runProfessorMenu(int menu) {
		switch(menu) {
		case 1: 	//교수등록
			addProfessor();
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

	private void addProfessor() {
		//교수 정보 입력
		//이름, 교번, 성별, 전화번호, 학과
		
		System.out.print("학과 : ");
		scan.nextLine();
		String dpName = scan.nextLine();
		//만약 학과가 학과 리스트안에 없다면
//		if(.equals("dpName")) {
//			System.out.println("존재하지 않은 학과입니다.");			
//		}
		//있다면 학과 리스트에서 동일한 학과 정보를 저장
		Department department;
		
		System.out.print("성함 : ");
		String phoneNum = scan.next();
		System.out.print("교번 : ");
		String name = scan.next();
		System.out.print("전화번호 : ");
		int num = scan.nextInt();
		System.out.print("성별(남:1, 여:2) : ");
		int gender = 0;
		//만약 성별이 1과 2가 아닌 숫자라면 다시 입력
		while(!(gender==1) || !(gender==2)) {
			gender = scan.nextInt();
		}
		
		
		
		
	}

	private void setProfessor() {
		
	}

	private void delseteProfessor() {
		
	}

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
	public List<Professor> pList = new ArrayList<Professor>();

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



	//리스트를 출력 후 선택하게 할 것>없는 교수를 선택할 수 없음
	//수정사항이 무엇이냐에 따라 다른 메소드를 둬야할듯
	@Override
	public List<Professor> setProfessor(List<Professor> list, Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean deleteProfessor(int pos) {
		//pos가 잘못된 경우
		if(pos < 0 || pos >= pList.size()) {
			return false;
		}
		pList.remove(pos);
		return true;
	}






	
}

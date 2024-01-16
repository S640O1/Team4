package university;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import program.Program;
import university.service.FileService;
import university.service.FileServiceImp;
import university.service.PrintService;
import university.service.PrintServiceImp;
import university.service.ProfessorService;
import university.service.ProfessorServiceImp;

public class UniversityProgram implements Program {
	private Scanner scan = new Scanner(System.in);
	static String fileName = "src/teamProject1/university/universityList.txt";
	
	//메뉴 종료 상수
	static final int EXIT = 5;
	
	//서비스 목록
	private PrintService printService = new PrintServiceImp();
	private FileService fileService = new FileServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();
	
	//대학교 정보
	private University university = new University(null);
	
	@Override
	public void run() {
		int menu = 0;
		
		university = fileService.load(fileName);
		
		List<Student> sList =  university.
		List<Department> dList =  fileService.load(fileName);
		List<Lecture> lList =  fileService.load(fileName);
		List<Professor> pList =  fileService.load(fileName);
		
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != EXIT);
		//저장하기
		if(fileService.save(fileName, university.getList())) {
			System.out.println("저장이 완료되었습니다.");
		}else {
			System.out.println("저장에 실패했습니다.");
		}
	}

	@Override
	public void printMenu() {

	}

	@Override
	public void runMenu(int menu) {

	}

}

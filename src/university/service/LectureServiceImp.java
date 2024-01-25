package university.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import university.Lecture;
import university.Professor;
import university.UniversityProgram;

public class LectureServiceImp implements LectureService {
	
	private FileService fileService = new FileServiceImp();
	private PrintService printService = new PrintServiceImp();
	private ProfessorService professorService = new ProfessorServiceImp();

	
	Date date = new Date();
	public static String pattern = "HH:mm";
	public static SimpleDateFormat format1 = new SimpleDateFormat(pattern);
	
	public static List<Professor> pList = new ArrayList<Professor>();
	
	/** 강의(리스트)에 내역을 추가하는 메소드 : 심아진 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<Lecture> addLecture(List<Lecture> lList, String lectureFileNames) {

		if(lList == null) {
			lList = new ArrayList<Lecture>();
		}
		
		System.out.print("강의 번호를 입력하세요 : ");
		int lectureNum = UniversityProgram.scan.nextInt();
		

		
		System.out.print("강의명을 입력하세요 : ");
		UniversityProgram.scan.nextLine();
		String lectureName = UniversityProgram.scan.nextLine();
		
		//교수 리스트 출력
		professorService.printProfessor();
		
		System.out.print("담당 교수 번호를 입력하세요 : ");
		int num = UniversityProgram.scan.nextInt();
		
//		int index = -1;
		
		boolean isP=false;
		
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getNum() == num) {
//				index = i;
				isP = true;
				break;
			}
		}
	
		if (isP == false) {
			System.out.println("등록되지 않은 교번입니다");
			return null;
		}
		
//		int nameIndex = -1;
	
		System.out.print("담당 교수를 입력하세요 : ");
		String pName = UniversityProgram.scan.next();
		
//		for (int i = 0; i < pList.size(); i++) {
//			if (pList.get(i).getName().equals(pName)) {
//				nameIndex = i;
//				break;
//			}
//		}
//		
//		if (nameIndex < 0 || nameIndex >= pList.size()) {
//			System.out.println("등록되지 않은 교수입니다");
//			return null;
//		}

		
		System.out.print("최대 수강 인원을 입력하세요 : ");
		int maxNum = UniversityProgram.scan.nextInt();
		UniversityProgram.scan.nextLine();
		
		System.out.print("강의 시간을 입력하세요 : ");
		while (UniversityProgram.scan.hasNextLine()) {
			try {
				date = format1.parse(UniversityProgram.scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("시간을" + pattern + " 형식에 맞게 다시 입력하세요.");
			}
		}
		
		System.out.print("강의실을 입력하세요 : ");
		String lectureRoom = UniversityProgram.scan.nextLine();
		
		Lecture lecture = new Lecture(lectureNum, maxNum, num, lectureName,  pName, lectureRoom, date, null);
		// System.out.println(lecture);
		
		//강의 같으면 등록 X 코드 작성 => 같은 강의 등록해도 저장이 되는 오류

		for(int i=0; i<lList.size(); i++) {
			if(lList.get(i).equals(lectureNum)) {
				System.out.println("이미 등록된 강의입니다.");
				return lList;
			}
		}
		
		lList.add(lecture);
		System.out.println("강의가 등록되었습니다.");
		
		return lList;
	}


	/** 강의 리스트 수정 : 심아진*/
	@Override
	public void setLecture(List<Lecture> lList, Lecture lecture) {
		if(!printLecture(lList)) {
			return;
		}
		
		// 수정할 강의 선택
		System.out.print("수정할 강의번호를 선택하세요. : ");
		
		int index = UniversityProgram.scan.nextInt()-1;
		
		if (index < 0 || index >= lList.size() ) {
			System.out.println("일치하는 번호가 없습니다.");
			return;
		}
		
		Lecture lec = lList.get(index);
		
		// 수정할 리스트만 보여줌
		System.out.println();
		System.out.println("--------수정전 내용--------");
		System.out.println(lList.get(index));
		
		//수정할 번호 선택
		int menu = 0;
		
		// 수정 메뉴 출력
		printService.printLectureUpdateMenu();
		
		try {
			menu = UniversityProgram.scan.nextInt();
			UniversityProgram.scan.nextLine();
			runUpdateLecture(menu, lec);
		} catch (InputMismatchException e) {
			System.out.println("잘못된 메뉴입니다.");
			UniversityProgram.scan.nextLine();
		}

	}

	private void runUpdateLecture(int menu, Lecture lec) {
		switch(menu) {
		case 1:
			// 강의 번호 수정
			System.out.print("수정할 강의 번호를 입력하세요 : ");
			int lectureNum = UniversityProgram.scan.nextInt();
			lec.setLectureNum(lectureNum);
			System.out.println(lec.toString());
			System.out.println("수정이 완료되었습니다.");
			break;
			
		case 2:
			// 강의명 수정
			System.out.print("수정할 강의명을 입력하세요 : ");
			String lectureName = UniversityProgram.scan.nextLine();
			lec.setLectureName(lectureName);
			System.out.println(lec.toString());
			System.out.println("수정이 완료되었습니다.");			
			break;
			
		case 3:
			// 담당 교수 번호 수정
			System.out.print("수정할 담당 교수 번호를 입력하세요 : ");
			int pNum = UniversityProgram.scan.nextInt();
			lec.setPNum(pNum);
			System.out.println(lec.toString());
			System.out.println("수정이 완료되었습니다.");
			break;
			
		case 4:
			// 담당 교수 이름 수정
			System.out.print("수정할 교수 이름을 입력하세요 : ");
			String pName = UniversityProgram.scan.nextLine();
			lec.setPName(pName);
			System.out.println(lec.toString());
			System.out.println("수정이 완료되었습니다.");
			break;
			
		case 5:
			// 최대 수강 인원 수정
			System.out.print("수정할 최대 수강 인원을 입력하세요 : ");
			int maxNum = UniversityProgram.scan.nextInt();
			lec.setMaxNum(maxNum);
			System.out.println(lec.toString());
			System.out.println("수정이 완료되었습니다.");
			break;
			
		case 6:
			// 강의 시간 수정
			
			System.out.print("수정할 강의 시간을 입력하세요 : ");
			while (UniversityProgram.scan.hasNextLine()) {
				try {
					date = format1.parse(UniversityProgram.scan.nextLine());
					lec.setDate(date);
					System.out.println(lec.toString());
					System.out.println("수정이 완료되었습니다.");
					break;
				} catch (Exception e) {
					System.out.println("시간을" + pattern + " 형식에 맞게 다시 입력하세요.");
				}
			}
			
			break;
		case 7:
			// 강의실 수정
			System.out.print("수정할 강의실을 입력하세요 : ");
			String lectureRoom = UniversityProgram.scan.nextLine();
			lec.setLectureRoom(lectureRoom);
			System.out.println(lec.toString());
			System.out.println("수정이 완료되었습니다.");
			break;

		case 8:
			break;
		default : throw new InputMismatchException();
		}
	}


	/** 강의 리스트 삭제 : 심아진*/
	@Override
	public void deleteLecture(List<Lecture> lList) {
		// 강의 리스트 보여주고
		if(!printLecture(lList)) {
			return;
		}
		
		// 삭제할 학생 번호 입력 받고
		System.out.print("삭제할 강의의 번호를 입력하세요. : ");
		int index = UniversityProgram.scan.nextInt()-1;
		
		// 일치하는 번호가 있으면
		if (index < 0 || index >= lList.size() ) {
			System.out.println("일치하는 번호가 없습니다.");
			return;
		}
			
		// 정말 삭제할 건지 물어보고
		System.out.print("정말로 삭제하시겠습니까? (y/n) : ");
		String yOrN = UniversityProgram.scan.next();
		
		// y나 n을 입력하지 않았을 경우 예외처리
		try {
			// Y라고 하면 삭제, N이면 취소해서 돌아감
			if (yOrN.equals("y") || yOrN.equals("Y")) {
				lList.remove(index);
				System.out.println("삭제되었습니다.");
			} else if (yOrN.equals("n") ||yOrN.equals("N")) {
				System.out.println("취소되었습니다.");
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("y나 n을 입력하세요.");	// 왜 이코드가 안 먹는가
		}

	}

	
	/** 강의 리스트 조회 : 심아진*/
	@Override
	public boolean printLecture(List<Lecture> lList) {
		if(lList.isEmpty()) {
			System.out.println("등록된 강의가 없습니다.");
			return false;
		}
		
		for(int i = 0; i < lList.size(); i++) {
			lList.get(i);
			System.out.println((i+1) + ". " + lList.get(i).toString());
		}
		return true;
	}

}

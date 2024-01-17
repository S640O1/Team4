package university.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import university.Lecture;
import university.UniversityProgram;

public class LectureServiceImp implements LectureService {
	

	/*
	@Override
	public List<Lecture> addLecture(List<Lecture> list, Lecture lecture) {
		return null;
	}
	// University university = new University();
	 */
	/*
	int lectureNum, maxNum  ;	// 강의 번호(학수 번호), 수강가능인원
	String lectureName, pNum, pName, lectureRoom;		// 강의명, 교번, 교수이름, 강의실
	Date date = new Date(); // 강의 시간
	*/
	
	private FileService fileService = new FileServiceImp();
	
	Date date = new Date();
	String pattern = "HH:mm";
	SimpleDateFormat format1 = new SimpleDateFormat(pattern);
	
	@Override
	public List<Lecture> addLecture(List<Lecture> lList, String lectureFileName) {

		if(lList == null) {
			lList = new ArrayList<Lecture>();
		}
		
		System.out.print("강의 번호를 입력하세요 : ");
		int lectureNum = UniversityProgram.scan.nextInt();
		
		System.out.print("강의명을 입력하세요 : ");
		UniversityProgram.scan.nextLine();
		String lectureName = UniversityProgram.scan.nextLine();
		
		//교수가 있다면 입력 받고, 없으면 받지 않음 -> 교수 완성되면 실행
		System.out.print("담당 교수 번호를 입력하세요 : ");
		int pNum = UniversityProgram.scan.nextInt();
		
		System.out.print("담당 교수를 입력하세요 : ");
		String pName = UniversityProgram.scan.next();
		
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
		
		Lecture lecture = new Lecture(lectureNum, maxNum, pNum, lectureName,  pName, lectureRoom, date);
		System.out.println(lecture);
		
		lList.add(lecture);
		
		return lList;
	}


}

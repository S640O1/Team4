package university.service;

import java.util.List;

import university.Lecture;
import university.Professor;
import university.Student;
import university.UniversityProgram;


/*
 * - 만약 강의 리스트의 사이즈가 수강인원보다 적다면
 *     				//수강신청완료
 *     					Lecture lecture = new ~~
 *     			1. (강의) 강의안에 수강학생 리스트에 해당 학번 학생 인스턴스 추가
 *     			2. (교수) 해당 강의 교수 정보에 강의 정보 업데이트
 *     `		3. (학생) sList.get(index).getLecture.add(lecture); 해당 학번 학생 안 수강 강의 리스트에 해당 강의 인스턴스 추가
 *     				//아니면 불가 
 * */


public class EnrolmentServiceImp implements EnrolmentService {
	
	public static List<Professor> pList = UniversityProgram.pList;

	/** 수강신청 메소드 (강의인덱스, 학생인덱스, 강의리스트, 학생리스트)*/
	@Override
	public boolean applications(int indexL, int indexS, List<Lecture> lList, List<Student> sList) {
		//수강신청 학생 인스턴스
		Student std = sList.get(indexS);
		//수강 강의 인스턴스
		Lecture lecture = lList.get(indexL);
		
		
		
		//만약 강의 속 학생 리스트의 사이즈가 수강인원보다 적다면 수강신청 완료
		
		if(lecture.getStudents().size() >= lecture.getMaxNum()) {
			System.out.println("수강인원이 초과되었습니다.");
			return false;
		}else if(lecture.getStudents().isEmpty()) {
	
		}
		
		//학생 정보에 수강강의 추가
		std.getLecture().add(lecture);
		
//		List<Lecture> lec = std.getLecture();
//		lec.add(lecture);
//		std.getLecture().set(indexL, lecture);
		
		
		//강의의 수강학생 목록에 학생 추가
		lecture.getStudents().add(std);
		
		
		//해당 강의 교수 정보에 강의 정보 업데이트
		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getNum() == lecture.getPNum()) {
				pList.get(i).getLList().add(lecture);
				break;
			}
		}
		return true;
		
		
	}

}

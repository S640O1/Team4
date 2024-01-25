package university.service;

import java.util.List;

import university.Lecture;
import university.Student;
import university.UniversityProgram;

public class ScoreServiceImp implements ScoreService {
	
	private StudentService studentService = new StudentServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	private int index;
	
	@Override
	public void addScore(List<Student> sList, List<Lecture> lList) {
		//강의목록 출력
		if(!lectureService.printLecture(lList)) {
			return;
		}
		
		//점수를 줄 강의 선택
		System.out.print("점수를 입력할 강의를 선택하세요. : ");
		int indexL = UniversityProgram.scan.nextInt() - 1;
		
		//해당 강의를 수강하는 학생리스트 출력
		List<Student> stdList = lList.get(indexL).getStudents();
		if(!studentService.printStudentList(stdList)) {
			return;
		}
		
		//점수 등록할 학생 인덱스
		System.out.print("성적을 등록할 학생을 선택하세요 : ");
		int indexS = UniversityProgram.scan.nextInt() - 1;
		
		//점수입력
		System.out.print("점수를 입력하세요(4.5점 만점) : ");
		double score = UniversityProgram.scan.nextDouble();
		
		//점수 저장
		 lList.get(indexL).getStudents().get(indexS).setScore(score);
		
		//학생리스트에 업데이트
		 for(int i=0; i<sList.size(); i++) {
			 //만약 전체학생리스트 중 해당 학생의 학생id와 동일한 인스턴스가 있다면
			 if(sList.get(i).getStudentId() == stdList.get(indexS).getStudentId()) {
				 //해당학생이 수강하고 있는 강의의 인덱스
				 //만약 해당 학생이 수강하고 있는 강의의 num과 해당 강의num이 동일하다면
				 for(int j=0; j<sList.get(i).getLecture().size(); j++) {
					 if(sList.get(i).getLecture().get(j).getLectureNum() == lList.get(indexL).getLectureNum()) {
						 //그 강의를 업데이트
						 sList.get(i).getLecture().set(j, lList.get(indexL)); 
						 break;
					 }
				 }
			 }
		 }
		 studentService.printStudentList(lList.get(indexL).getStudents());
		 
		
	}

	

	//강의 평균 점수
	@Override
	public void showStudentStandardScore(List<Student> sList) {
		//실수로 변경하기
		
		//학생 목록
		studentService.printStudentList(sList);
		System.out.print("학점을 볼 학생 선택 : ");
		
		//해당 학생의 인덱스 Student 인스턴스 생성
		index = UniversityProgram.scan.nextInt() - 1;
		Student std =  sList.get(index);
		
		//수강하고 있는 강의 리스트
		List<Lecture> lList = sList.get(index).getLecture();
		
		//전체 합계 초기화
		double sumScore=0;
		
		//수강하고 있는 강의의
		for(int i=0; i<lList.size(); i++) {
			//해당 학생의 학생id와 동일한 정보를 찾아서
			for(int j=0; j<lList.get(i).getStudents().size(); j++) {
				//해당 학생의 정보라면
				//내가 수강하고 있는 i번째 강의의 학생목록 중 나는 j번째
				if(std.getStudentId() == lList.get(i).getStudents().get(j).getStudentId()) {
					System.out.println(lList.get(i).getLectureName() + " : " + lList.get(i).getStudents().get(j).getScore() + "점");
					sumScore += lList.get(i).getStudents().get(j).getScore();
				}
			}
		}
		//sumScore안에 자신의 전체 학점합이 저장된다.
		
		double standardScore = sumScore / lList.size();
		
		
		System.out.println(sList.get(index).getName() + " 학생의 평균 점수는 " + standardScore + "점 입니다.");
		
		//학생정보에 평균점수 저장
		

		
	}
	
	//각 강의 점수 조회
	@Override
	public void showStudentLectureScore(List<Student> sList, List<Lecture> lList) {

		studentService.printStudentList(sList);
		System.out.print("학점을 볼 학생 선택 : ");
		index = UniversityProgram.scan.nextInt() - 1;
		Student std = sList.get(index);
		
		//선택한 인덱스의 학생
		System.out.println(std.getName() + " 학생의 각 강의 학점");
		
		for(int i=0; i<lList.size(); i++) {
			for(int j=0; j<lList.get(i).getStudents().size(); j++) {
				if(std.getStudentId() == lList.get(i).getStudents().get(j).getStudentId()) {
					System.out.println(std.getLecture().get(i).getLectureName() + " : "
							+ std.getLecture().get(i).getStudents().get(j).getScore());
				}
			}
		}
	}


	@Override
	public void updateScore(List<Student> sList, List<Lecture> lList) {
		
		if(!lectureService.printLecture(lList)) {
			return;
		}
		
		//점수를 줄 강의 선택
		System.out.print("점수를 수정할 강의를 선택하세요. : ");
		int indexL = UniversityProgram.scan.nextInt() - 1;
				
		//해당 강의를 수강하는 학생리스트 출력
		List<Student> stdList = lList.get(indexL).getStudents();
		if(!studentService.printStudentList(stdList)) {
			return;
		}
				
		//점수를 수정할 학생 인덱스
		System.out.print("성적을 수정할 학생을 선택하세요 : ");
		int indexS = UniversityProgram.scan.nextInt() - 1;
		
		//수정할 점수 입력
		System.out.print("수정할 점수를 입력하세요(4.5점 만점) : ");
		double score = UniversityProgram.scan.nextDouble();
		
	
		 for(int i=0; i<sList.size(); i++) {
			 if(sList.get(i).getStudentId() == stdList.get(indexS).getStudentId()) {
				 for(int j=0; j<sList.get(i).getLecture().size(); j++) {
					 if(sList.get(i).getLecture().get(j).getLectureNum() == lList.get(indexL).getLectureNum()) {
						 sList.get(i).getLecture().set(j, lList.get(indexL)); 
						 break;
					 }
				 }
			 }
		 }
		 studentService.printStudentList(lList.get(indexL).getStudents());
		 
	}
}

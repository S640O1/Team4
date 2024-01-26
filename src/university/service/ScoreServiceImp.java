package university.service;

import java.util.ArrayList;
import java.util.List;

import university.Lecture;
import university.Score;
import university.Student;
import university.UniversityProgram;

public class ScoreServiceImp implements ScoreService {

	/*
	 * 5. 성적 조회 - 1. 학생 평균 학점 조회
	 * -> 각 다른 강의에 각각의 학점을 입력했는데, 다른 강의에 동일한 학점이 저장
	 * 
	 * 5. 성적 조회 - 2. 학생 각 강의별 학점 조회
	 * -> 점수를
	 * */
	
	public static List<Score> scoreList = UniversityProgram.scoreList;

	private StudentService studentService = new StudentServiceImp();
	private LectureService lectureService = new LectureServiceImp();
	private int index;
	
	@Override
	public void addScore(List<Student> sList, List<Lecture> lList) {
		//강의목록 출력
		if(!lectureService.printLecture(lList)) {
			return;
		}
		
		//점수를 줄 강의 B선택
		System.out.print("점수를 입력할 강의를 선택하세요. : ");
		int indexL = UniversityProgram.scan.nextInt() - 1;
		
		//해당 강의를 수강하는 학생리스트 출력
		List<Student> stdList = lList.get(indexL).getStudents();
		if(!studentService.printStudentList(stdList)) {
			System.out.println("해당 강의를 듣는 학생이 없습니다.");
			return;
		}
		
		//점수 등록할 학생 인덱스
		//해당 강의의 학생리스트의 A학생인덱스
		System.out.print("성적을 등록할 학생을 선택하세요 : ");
		int indexS = UniversityProgram.scan.nextInt() - 1;
		
		//점수입력
		System.out.print("점수를 입력하세요(4.5점 만점) : ");
		double score = UniversityProgram.scan.nextDouble();
		
		//전체학생 리스트에서 A학생 인덱스 찾기
		int indexStd = -1;
		
		for(int i=0; i<sList.size(); i++) {
			if(sList.get(i).getStudentId() == lList.get(indexL).getStudents().get(indexS).getStudentId()) {
				indexStd = i;
				break;
			}
		}
		
		//score 클래스 이용
		Score sObj = new Score(lList.get(indexL).getLectureNum(), sList.get(indexStd).getStudentId() , score, lList.get(indexL).getLectureName(), sList.get(indexStd).getName());
		
		//만약이미 해당 학생의 정보가 들어있다면(강의num과 학생id가 일치한 항목)
		 if(scoreList.equals(sObj)) {
			 System.out.println("이미 점수를 등록했습니다.");
			 return;
		 }
		 
		 //점수 등록
		 scoreList.add(sObj);
		 System.out.println("점수를 등록했습니다.");
	}
	
	@Override
	public void updateScore(List<Student> sList, List<Lecture> lList) {
		
		if(!lectureService.printLecture(lList)) {
			return;
		}
		
		//점수를 줄 강의 선택
		System.out.print("점수를 수정할 강의를 선택하세요. : ");
		int indexL = UniversityProgram.scan.nextInt() - 1;
		
		//해당 강의를 수강하는 학생 중 점수가 등록된 학생 리스트 출력
//		int scoreIndex = -1;
		for(int i =0; i<scoreList.size(); i++) {
			if(scoreList.get(i).getLectureNum() == lList.get(indexL).getLectureNum()) {
				System.out.println((i+1) + scoreList.toString());
			}
		}
		//점수를 수정할 학생 인덱스
		System.out.print("성적을 수정할 학생을 선택하세요 : ");
		int indexS = UniversityProgram.scan.nextInt() - 1;
		
		//수정할 점수 입력
		System.out.print("수정할 점수를 입력하세요(4.5점 만점) : ");
		double score = UniversityProgram.scan.nextDouble();
		
		scoreList.get(indexS).setScore(score);
	}
  
	/*
	 * 5. 성적 조회 - 1. 학생 평균 학점 조회
	 * -> 각 다른 강의에 각각의 학점을 입력했는데, 다른 강의에 동일한 학점이 저장
	 *
	 * */

	//강의 평균 점수
	@Override
	public void showStudentStandardScore(List<Student> sList, List<Lecture> lList) {
		
		//학생 목록
		studentService.printStudentList(sList);
		System.out.print("학점을 볼 학생 선택 : ");
		
		//해당 학생의 인덱스 Student 인스턴스 생성
		index = UniversityProgram.scan.nextInt() - 1;
		Student std = sList.get(index);
		
		//전체 합계 초기화
		double sumScore=0;
		
		//전체 점수list에서 해당 학생이 포함된 학생점수 list만들기
		List<Score> stdScoreList = new ArrayList<Score>();
		
		for(int i=0; i<scoreList.size(); i++) {
			//만약 성적 리스트에 본인 id정보가 있다면
			if(scoreList.get(i).getStdId() == std.getStudentId()) {
				//해당 점수 인스턴스를 학생점수list에 추가
				stdScoreList.add(scoreList.get(i));
			}
		}
		
		//자신의 점수를 모두 합침
		for(int i=0; i<scoreList.size(); i++) {
			sumScore  += scoreList.get(i).getScore();
		}
		
		//평균을 구함
		double standardScore = sumScore / std.getLecture().size();
		
		System.out.println(std.getName() + " 학생의 평균 점수는 " + standardScore + "점 입니다.");
		
		//학생정보에 평균점수 저장
		sList.get(index).setAverageScore(standardScore);
		
	}

	//수강하고 있는 강의의 각 점수 조회
	@Override
	public void showStudentLectureScore(List<Student> sList) {

		studentService.printStudentList(sList);
		System.out.print("학점을 볼 학생 선택 : ");
		index = UniversityProgram.scan.nextInt() - 1;
		Student std = sList.get(index);
		
		//전체 점수list에서 해당 학생이 포함된 학생점수list만들기
		List<Score> stdScoreList = new ArrayList<Score>();
		
		for(int i=0; i<scoreList.size(); i++) {
			//만약 성적 리스트에 본인 id정보가 있다면
			if(scoreList.get(i).getStdId() == std.getStudentId()) {
				//해당 점수 인스턴스를 학생점수list에 추가
				stdScoreList.add(scoreList.get(i));
			}else {
				System.out.println("등록된 점수가 없습니다.");
				return;
			}
		}
		
		//각 강의 점수 출력
		System.out.println("[" + sList.get(index).getName() + " 학생의 각 강의 학점]");
		for(int i=0; i<stdScoreList.size(); i++) {
			System.out.println(stdScoreList.get(i).toString());
		}
	}
		


}

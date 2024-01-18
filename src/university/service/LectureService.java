package university.service;

import java.util.List;

import university.Lecture;

public interface LectureService {
	
	// 1.  강의를 등록하는 메서드
	List<Lecture> addLecture(List<Lecture> lList, String lectureFileName);

	
	// 2. 강의를 수정하는 메서드
	public void setLecture(List<Lecture> lList, Lecture lecture);
	
	// 3. 강의를 삭제하는 메서드

	// 4. 강의 목록
	public boolean printLecture(List<Lecture> lList);
	
	
}

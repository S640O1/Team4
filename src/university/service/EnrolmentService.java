package university.service;

import java.util.List;

import university.Lecture;
import university.Student;

/* 수강 신청 서비스*/

public interface EnrolmentService {

	/** 수강신청 메소드 (강의인덱스, 학생인덱스, 강의리스트, 학생리스트)*/
	boolean applications(int indexL, int indexS, List<Lecture> lList,  List<Student> sList); 

}

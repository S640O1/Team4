package university.service;

import java.util.List;

import university.Lecture;
import university.Professor;
import university.Student;


public interface FileService {

	List<Lecture>lLoad(String lectureFileName);
	
	boolean lSave(String lectureFileName, List<Lecture> lList);



}

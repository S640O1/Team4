package university.service;

import java.util.List;

import university.Professor;
import university.Student;
import university.University;

public interface FileService {


	boolean save(String fileName, List<Professor> list);

	University load(String fileName);


}

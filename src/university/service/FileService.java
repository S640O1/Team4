package university.service;

import java.util.List;

import university.Professor;
import university.Student;
import university.University;

public interface FileService {

	University load(String fileName);

	boolean save(String fileName, University university);


}

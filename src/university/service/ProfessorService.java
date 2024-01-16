package university.service;

import java.util.List;

import university.Professor;

public interface ProfessorService {
	
	/** 교수정보를 추가하는 메소드*/
	boolean addProfessor(Professor professor);
	
	/** 교수정보를 수정하는 메소드*/
	List<Professor> setProfessor(List<Professor> list, Professor professor);
	
	/** 교수정보를 삭제하는 메소드*/
	boolean deleteProfessor(int pos);

	
}

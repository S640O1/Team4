package university.service;

import java.util.List;

import university.Professor;

public class ProfessorServiceImp implements ProfessorService {


	/** 교수정보를 추가하는 메소드*/

	@Override
	public List<Professor> add(List<Professor> pList, Professor professor) {
		//이미 등록된 교수면
		if(pList.equals(professor)) {
			return pList;
		}
		//새 교수를 등록
		pList.add(professor);
		return pList;
	}

	
}

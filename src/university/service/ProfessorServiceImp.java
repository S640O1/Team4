package university.service;

import java.util.ArrayList;
import java.util.List;

import university.Professor;

public class ProfessorServiceImp implements ProfessorService {

	List<Professor> pList = new ArrayList<Professor>();

	/** 교수정보를 추가하는 메소드*/

	@Override
	public boolean addProfessor(Professor professor) {
		//이미 등록된 교수면(교번, 성별, 이름, 연락처 동일)
		if(pList.equals(professor)) {
			return false;
		}
		//새 교수를 등록
		pList.add(professor);
		return true;
	}



	//리스트를 출력 후 선택하게 할 것>없는 교수를 선택할 수 없음
	//수정사항이 무엇이냐에 따라 다른 메소드를 둬야할듯
	@Override
	public List<Professor> setProfessor(List<Professor> list, Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean deleteProfessor(int pos) {
		//pos가 잘못된 경우
		if(pos < 0 || pos >= pList.size()) {
			return false;
		}
		pList.remove(pos);
		return true;
	}






	
}

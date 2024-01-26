package university.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import university.Department;
import university.Lecture;
import university.Professor;
import university.Score;
import university.Student;
import university.UniversityProgram;


public class FileServiceImp implements FileService {



	/** 학과 파일정보 불러오기*/
	@Override
	public List<Department> dLoad(String departmentFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(departmentFileName))) {
			System.out.println("학과를 불러왔습니다.");
			return (List<Department>)ois.readObject();
		} catch (Exception e) {
			System.out.println("학과를 등록해주세요.");
		}
		return null;
	}
	
	/** 학과 파일정보 저장하기*/
	 @Override
    public boolean dpSave(String fileName, List<Department> departmentList) {
		 try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
				oos.writeObject(departmentList);
				oos.flush();
				return true;
			} catch (Exception e) {
				System.out.println("예외가 발생했습니다.");
			}
			return false;
	    }
	

	/** 교수 파일정보 불러오기*/
	@Override
	public List<Professor> pLoad(String professorFileName) {
		try(ObjectInputStream ois = 
			new ObjectInputStream(new FileInputStream(professorFileName))){
			System.out.println("교수 정보를 불러왔습니다.");
			return (List<Professor>) ois.readObject();				
		} catch (Exception e) {
			System.out.println("교수를 등록해주세요.");
		}
		return null;
	}

	/** 교수 파일정보 저장하기*/
	@Override
	public boolean pSave(String professorFileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(professorFileName))) {
			oos.writeObject(UniversityProgram.pList);
			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

	/** 학생 파일정보 불러오기*/
	@Override
	public List<Student> sLoad(String studentFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(studentFileName))) {
			System.out.println("학생을 불러왔습니다.");
			return (List<Student>)ois.readObject();
		} catch (Exception e) {
			System.out.println("학생을 등록해주세요.");
		}
		return null;
	}

	/** 학생 파일정보 저장하기*/
	@Override
	public boolean sSave(String studentFileName, List<Student> sList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(studentFileName))) {
			oos.writeObject(sList);
			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

	/** 강의 파일정보 불러오기*/
	@Override
	public List<Lecture> lLoad(String lectureFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(lectureFileName))) {
			System.out.println("강의를 불러왔습니다.");
			return (List<Lecture>)ois.readObject();
		} catch (Exception e) {
			System.out.println("강의를 등록해주세요.");
		}
		return null;
	}
	
	/** 강의 파일정보 저장하기*/
	@Override
	public boolean lSave(String lectureFileName, List<Lecture> lList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(lectureFileName))) {
			oos.writeObject(lList);
			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

	@Override
	public List<Score> scoreLoad(String scoreFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(scoreFileName))) {
			System.out.println("성적 정보를 불러왔습니다.");
			return (List<Score>)ois.readObject();
		} catch (Exception e) {
			System.out.println("성적을 등록해주세요.");
		}
		return null;
	}

	@Override
	public boolean scoreSave(String scoreFileName, List<Score> scoreList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(scoreFileName))) {
			oos.writeObject(scoreList);
			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

	
	
	
}

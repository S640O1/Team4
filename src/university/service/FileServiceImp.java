package university.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import accountBook.Item;
import university.Department;
import university.Lecture;
import university.Professor;
import university.Student;
import university.UniversityProgram;


public class FileServiceImp implements FileService {



	/** 학과 파일정보 불러오기*/
	@Override
	public List<Department> dLoad(String departmentFileName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 학과 파일정보 저장하기*/
	 @Override
    public boolean dpSave(String fileName, List<Department> departmentList) {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
	            oos.writeObject(departmentList);
    			} catch (IOException e) {
    				return false;
	        }
    	return true;
	    }
	

	/** 교수 파일정보 불러오기*/
	@Override
	public List<Professor> pLoad(String professorFileName) {
		try(ObjectInputStream ois = 
			new ObjectInputStream(new FileInputStream(professorFileName))){
			if(ois.readObject() == null) {
				System.out.println("교수 정보를 등록해주세요.");
				return new ArrayList<Professor>();
			}
			System.out.println("교수 정보를 불러왔습니다.");
			return (List<Professor>) ois.readObject();				
		} catch (Exception e) {
			System.out.println("교수 정보를 불러오는 중 오류가 발생하였습니다.");
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
		// TODO Auto-generated method stub
		return null;
	}

	/** 학생 파일정보 저장하기*/
	@Override
	public boolean sSave(String studentFileName, List<Student> sList) {
		// TODO Auto-generated method stub
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

	
	
	
}

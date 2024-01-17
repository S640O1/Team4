package university.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import university.Lecture;
import university.Professor;


public class FileServiceImp implements FileService {

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

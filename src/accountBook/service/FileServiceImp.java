package accountBook.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import accountBook.Item;


public class FileServiceImp implements FileService {
	@Override
	public List<Item> load(String fileName) {
		try(ObjectInputStream ois = 
			new ObjectInputStream(new FileInputStream(fileName))){
			
//			List<Item> list = (List<Item>) ois.readObject();
//			
//			if(list == null) {
//				System.out.println("새 가계부를 작성합니다.");
//				return new ArrayList<Item>();
//			}
//			
			System.out.println("가계부를 불러왔습니다.");
			return (List<Item>) ois.readObject();				
		} catch (Exception e) {
			System.out.println("가계부를 등록해주세요.");
		}
		return null;
	}

	@Override
	public boolean save(String fileName, List<Item> list) {
		try(ObjectOutputStream oos = 
			new ObjectOutputStream(new FileOutputStream(fileName))){
			oos.writeObject(list);
			oos.flush();
			return true;
		}catch(Exception e) {
			System.out.println("예외가 발생하였습니다.");
		}
		return false;
	}
}

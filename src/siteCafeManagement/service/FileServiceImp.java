package siteCafeManagement.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import siteCafeManagement.manager.board.Board;

public class FileServiceImp implements FileService {

	// 게시판 파일 정보 불러오기
	@Override
	public List<Board> bLoad(String boardFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(boardFileName))) { 
			System.out.println("게시판을 불러왔습니다.");
			return (List<Board>)ois.readObject();
		} catch (Exception e) {
			System.out.println("게시판을 등록해주세요.");
		}
		return null;
	}

	@Override
	public boolean bSave(String boardFileName, List<Board> bList) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(boardFileName))) {
			oos.writeObject(bList);
			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

}

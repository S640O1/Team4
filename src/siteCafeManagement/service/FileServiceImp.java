package siteCafeManagement.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


import siteCafeManagement.manager.board.Board;
import siteCafeManagement.manager.category.Category;
import siteCafeManagement.post.Post;
import university.Department;

public class FileServiceImp implements FileService {

	/** 카테고리 파일 정보 불러오기 : 신경재 */
	@Override
	public List<Category> categoryLoad(String categoryFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(categoryFileName))) { 
			System.out.println("카테고리를 불러왔습니다.");
			return (List<Category>)ois.readObject();
		} catch (Exception e) {
			System.out.println("카테고리를 등록해주세요.");
		}
		return null;
	}
	
	// 게시판 파일 정보 불러오기
	@Override
	public List<Board> boardLoad(String boardFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(boardFileName))) { 
			System.out.println("게시판을 불러왔습니다.");
			return (List<Board>)ois.readObject();
		} catch (Exception e) {
			System.out.println("게시판을 등록해주세요.");
		}
		return null;
	}

	/** 게시글 파일정보 불러오기 : 손나영*/
	@Override
	public List<Post> postLoad(String postFileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(postFileName))) {
			System.out.println("학과를 불러왔습니다.");
			return (List<Post>)ois.readObject();
		} catch (Exception e) {
			System.out.println("학과를 등록해주세요.");
		}
		return null;

	}
	
	/** 카테고리 파일 정보 저장하기 : 신경재 */
	@Override
	public boolean categorySave(String categoryFileName, List<Category> categoryList) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(categoryFileName))) {
			oos.writeObject(categoryList);

			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

	// 게시판 파일 정보 저장하기
	@Override
	public boolean boardSave(String boardFileName, List<Board> boardList) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(boardFileName))) {
			oos.writeObject(boardList);

			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
	}

	/** 게시글 파일정보 저장하기 : 손나영*/
	@Override
	public boolean postSave(String postFileName, List<Post> postList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(postFileName))) {
			oos.writeObject(postList);
			
			oos.flush();
			return true;
		} catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		return false;
		}
}

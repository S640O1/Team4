package cafe.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cafe.dao.BoardDAO;
import cafe.model.vo.Board;

public class BoardServiceImp implements BoardService {

	private BoardDAO boardDao;
	
	public BoardServiceImp() {
		String resource = "cafe/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			boardDao = session.getMapper(BoardDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Board> getBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public boolean insertBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	@Override
	public boolean updateBoard(Board newBoard) {
		if(newBoard == null) {
			return false;
		}
		return boardDao.updateBoard(newBoard);
	}

	@Override
	public boolean deleteBoard(int b_num) {
		return boardDao.deleteBoard(b_num);
	}

	@Override
	public ArrayList<Board> selectCaBoardList(int b_c_num) {
		return boardDao.selectCaBoardList(b_c_num);
	}

	@Override
	public ArrayList<Board> getCBoradList(int b_c_num) {
		return boardDao.selectCBoardList(b_c_num);
	}

}

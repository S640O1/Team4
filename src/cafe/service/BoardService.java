package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.Board;

public interface BoardService {

	ArrayList<Board> getBoardList();

	boolean insertBoard(Board board);

	boolean updateBoard(Board newBoard);

	boolean deleteBoard(int b_num);

	ArrayList<Board> selectCaBoardList(int b_c_num);
}

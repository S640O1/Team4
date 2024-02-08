package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.Board;

public interface BoardService {

	ArrayList<Board> getBoardList();

	boolean insertBoard(Board board);
}

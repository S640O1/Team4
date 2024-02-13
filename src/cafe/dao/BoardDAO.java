package cafe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cafe.model.vo.Board;

public interface BoardDAO {

	ArrayList<Board> selectBoardList();

	boolean insertBoard(@Param("board")Board board);

	

}

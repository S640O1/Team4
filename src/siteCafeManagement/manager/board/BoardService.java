package siteCafeManagement.manager.board;

import java.util.List;

public interface BoardService {
	
	public void addBoardService(List<Board> boardList);
	public void updateBoardService(List<Board> boardList, Board board);
	public void deleteBoardServiece(List<Board> boardList, Board board);
	public boolean printBoardService(List<Board> boardList);
	
}

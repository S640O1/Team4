package siteCafeManagement.manager.board;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import siteCafeManagement.MainProgram;
import siteCafeManagement.service.PrintService;
import siteCafeManagement.service.PrintServiceImp;

public class BoardServiceImp implements BoardService {
	public PrintService printService = new PrintServiceImp();
	public static Scanner scan = MainProgram.scan;
	

	// 게시판 추가 서비스
	@Override
	public void addBoardService(List<Board> boardList) {
		// admin으로 로그인시
		
		// 카테고리 리스트 출력
		
		// 게시판 추가할 카테고리 선택
		
		// 게시판 제목 입력 받기
		int boardNum = -1;
		
		// 게시판 리스트가 비어있을 땐 게시판 번호를 1번으로 설정
		if (boardList.isEmpty()) {
			boardNum = 1;
			return;
		}
		
		// 마지막 게시판 번호 + 1	
		boardNum = boardList.get(boardList.size()-1).getBoardNum() + 1;
		System.out.print("등록할 게시판 제목을 입력하세요 : ");
		scan.nextLine();
		String boardTitle = scan.nextLine();
		
		Board board = new Board(null, boardNum, boardTitle);
		
		// 게시판 번호 같으면 등록 X
		for (int i = 0; i < boardList.size(); i++) {
			if(boardList.get(i).equals(board)) {
				System.out.println("이미 등록된 게시판 입니다.");
				return;
			}
		}
		
		boardList.add(board);
		System.out.println(board);
		System.out.println("게시판을 추가하였습니다.");
	}


	// 게시판 수정 서비스
	@Override
	public void updateBoardService(List<Board> boardList, Board board) {
		
		int menu = 0;
		
		// 수정 메뉴 출력
		System.out.println();
		printService.printBoardUpdate();
		
		try {
			menu = scan.nextInt();
			scan.nextLine();
			runUpdateBoard(menu, boardList);
		} catch (InputMismatchException e) {
			System.out.println("잘못된 메뉴입니다.");
			scan.nextLine();
		}		
	}


	private void runUpdateBoard(int menu, List<Board> boardList) {
		switch(menu) {
		case 1 :		// 게시판 카테고리 변경
			
			// 카테고리 - 게시판 보여주기
			// 카테고리 변경할 게시판 선택
			// 카테고리 리스트 출력
			// 카테고리 선택
			// 수정 완료 문구
			break;
		case 2 :		// 게시판 제목 수정
			// 게시판 리스트 출력
			System.out.println();
			if(!printBoardService(boardList)) {
				return;
			}
			
			// 수정할 게시판 선택
			System.out.println();
			System.out.print("수정할 게시판을 선택하세요 : ");
			
			int index = scan.nextInt() -1;
			if (index < 0 || index >= boardList.size()) {
				System.out.println("일치하는 번호가 없습니다.");
				return;
			}
			
			Board bd = boardList.get(index);
			
			// 수정할 게시판 제목
			System.out.print("수정할 게시판 제목을 입력하세요 : " );
			scan.nextLine();
			String boardTitle = scan.nextLine();
			bd.setBoardTitle(boardTitle);
			System.out.println(bd.toString());
			System.out.println("수정이 완료되었습니다.");
			break;
		case 3 :
			break;
		}
	}


	// 게시판 삭제 서비스
	@Override
	public void deleteBoardServiece(List<Board> boardList, Board board) {
		// 게시판 리스트 출력
		if(!printBoardService(boardList)) {
			return;
		}
		
		// 삭제할 게시판 선택
		System.out.print("삭제할 게시판을 선택하세요 : ");
		
		int index = scan.nextInt() -1;
		if (index < 0 || index >= boardList.size()) {
			System.out.println("일치하는 번호가 없습니다.");
			return;
		}
		
		// 삭제 여부 묻기
		System.out.print("정말로 삭제하시겠습니까? (y/n) : ");
		String yOrN = scan.next();
		
		// y나 n을 입력하지 않았을 경우 예외처리
		try {
			// Y라고 하면 삭제, N이면 취소해서 돌아감
			if (yOrN.equals("y") || yOrN.equals("Y")) {
				boardList.remove(index);
				System.out.println("삭제되었습니다.");
			} else if (yOrN.equals("n") ||yOrN.equals("N")) {
				System.out.println("취소되었습니다.");
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("y나 n을 입력하세요.");	// 왜 이코드가 안 먹는가
		}
	}


	// 게시판 조회 서비스
	@Override
	public boolean printBoardService(List<Board> boardList) {
		if(boardList.isEmpty()) {
			System.out.println("등록된 게시판이 없습니다.");
			return false;
		}
		
		for (int i = 0; i < boardList.size(); i++) {
			boardList.get(i);
			System.out.println(boardList.get(i).toString());
		}
		return true;
	}

}

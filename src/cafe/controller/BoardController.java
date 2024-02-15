package cafe.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import cafe.model.vo.Board;
import cafe.model.vo.Category;
import cafe.service.BoardService;
import cafe.service.BoardServiceImp;
import cafe.service.CategoryService;
import cafe.service.PrintService;
import cafe.service.PrintServiceImp;

public class BoardController {
	private Scanner scan;
	private BoardService boardService;
	private CategoryService categoryService;
	public PrintService printService = new PrintServiceImp();
	
	private static final int EXIT_BOARD = 5;
	
	public BoardController(Scanner scan) {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		boardService = new BoardServiceImp();
	}
	
	public void run() {
		int menu = 0;
		
		do {
				printService.printBoardMenu();
			try {
				menu = scan.nextInt();
				runBoardMenu(menu);
			} catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != EXIT_BOARD);
		
		
	}

	private void runBoardMenu(int menu) {
		switch(menu) {
		case 1:
			addBoardService();
			break;
		case 2:
			printBoardService();
			break;
		case 3:
			updateBoardService();
			break;
		case 4:
			deleteBoardServiece();
			break;
		case 5:
			break;
		default : throw new InputMismatchException();
		}
	}

	private void addBoardService() {
		Board board = boardInput();
		if(boardService.insertBoard(board)) {
			System.out.println(board.toString());
			System.out.println("내역을 추가하였습니다.");
		} else {
			System.out.println("내역을 추가하지 못했습니다.");
		}
	}

	/*
	if(!printBoardList(boardList)) {
		System.out.println("게시판이 없습니다.");
		return;
	}
	
	int b_num, index;
	while(true) {
		System.out.print("수정할 게시판 번호를 선택하세요 : ");
		b_num = scan.nextInt();
		if(boardList.contains(new Board(b_num))) {
			index = boardList.indexOf(new Board(b_num));
			break;
		}
		System.out.println("잘못된 번호입니다.");
	}
	*/
	
	private Board boardInput() {
		// 카테고리 리스트 출력
		ArrayList<Category> categoryList = categoryService.getCategoryList();
		
		// 게시판 추가할 카테고리 선택
		
		
		// int b_c_num = scan.nextInt();
		int b_c_num = 1;
		
		// 정규 표현식 (제목 1-20자)
		System.out.print("제목을 입력하세요(1~20자) : ");
		scan.nextLine();
		String b_title = scan.nextLine();
		
		Board board = new Board(b_c_num, b_title);
		
		return board;
	}

	private void printBoardService() {
		// 카테고리 리스트 출력
		
		// 카테고리 선택
		// 카테고리에 해당하는 게시판 출력
		printBoardList();
		
		
	}

	public void printBoardList() {
		ArrayList<Board> boardList = boardService.getBoardList();
		if(!printBoardList(boardList)) {
			System.out.println("조회할 게시판이 없습니다.");
			return;
		}
	}

	public boolean printBoardList(ArrayList<Board> boardList) {
		if(boardList.isEmpty()) {
			return false;
		}
		for (Board b : boardList) {
			System.out.println(b.toString());
		}
		return true;
	}

	
	private void updateBoardService() {
		// 카테고리 리스트 선택
		
		// 게시판 리스트 출력
		ArrayList<Board> boardList = boardService.getBoardList();	
		if(!printBoardList(boardList)) {
			System.out.println("게시판이 없습니다.");
			return;
		}
		
		int b_num, index;
		while(true) {
			System.out.print("수정할 게시판 번호를 선택하세요 : ");
			b_num = scan.nextInt();
			if(boardList.contains(new Board(b_num))) {
				index = boardList.indexOf(new Board(b_num));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		Board newBoard = boardUpdateInput(boardList.get(index));
		newBoard.setB_num(b_num);
		if(boardService.updateBoard(newBoard)) {
			System.out.println(newBoard.toString());
			System.out.println("게시판 제목을 수정하였습니다.");
		} else {
			System.out.println("게시판 제목을 수정하지 못했습니다.");
		}
	}

	private Board boardUpdateInput(Board board) {
		// 카테고리 출력
		// 카테고리 선택
		System.out.print("수정할 카테고리를 선택하세요 : ");
		int b_c_num = scan.nextInt();
		
		System.out.print("수정할 제목을 입력하세요(1~20자) : ");
		scan.nextLine();
		String b_title = scan.nextLine();
		
		Board newBoard = new Board(b_c_num, b_title);
		System.out.println(newBoard.toString());
		return newBoard;
	}

	private void deleteBoardServiece() {
		ArrayList<Board> boardList = boardService.getBoardList();	
		if(!printBoardList(boardList)) {
			System.out.println("게시판이 없습니다.");
			return;
		}
		
		int b_num, index;
		
		while(true) {
			System.out.print("삭제할 게시판 번호를 선택하세요 : ");
			b_num = scan.nextInt();
			if(boardList.contains(new Board(b_num))) {
				index = boardList.indexOf(new Board(b_num));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		if(boardService.deleteBoard(b_num)) {
			System.out.println("게시판을 삭제하였습니다.");	
		} else {
			System.out.println("게시판을 삭제하지 못했습니다.");
		}
	}
	
	public boolean containsBoardServiece(int b_num) {
		ArrayList<Board> boardList = boardService.getBoardList();
		if(boardList.contains(new Board(b_num))) {
			return true;
		}
		return false;
	}

	public ArrayList<Board> getBoardList() {
		return boardService.getBoardList();
	}
	
}

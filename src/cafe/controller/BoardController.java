package cafe.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import cafe.model.vo.Board;
import cafe.service.BoardService;
import cafe.service.BoardServiceImp;
import cafe.service.PrintService;
import cafe.service.PrintServiceImp;

public class BoardController {
	private Scanner scan;
	private BoardService boardService;
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
		
//		// 카테고리 리스트 출력
//		

//		
//		// 게시판 제목 입력 받기
//		int b_num = -1;

		Board board = boardInput();
		if(boardService.insertBoard(board)) {
			System.out.println(board.toString());
			System.out.println("내역을 추가하였습니다.");
		} else {
			System.out.println("내역을 추가하지 못했습니다.");
		}
	}

	private Board boardInput() {
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

	private void printBoardList() {
		ArrayList<Board> boardList = boardService.getBoardList();
		if(!printBoardList(boardList)) {
			System.out.println("조회할 게시판이 없습니다.");
			return;
		}
	}

	private boolean printBoardList(ArrayList<Board> boardList) {
		if(boardList.isEmpty()) {
			return false;
		}
		for (Board b : boardList) {
			System.out.println(b.toString());
		}
		return true;
	}

	private void updateBoardService() {
		
	}

	private void deleteBoardServiece() {
		
	}
}

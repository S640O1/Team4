package cafe.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import cafe.model.vo.Board;
import cafe.model.vo.Category;
import cafe.service.BoardService;
import cafe.service.BoardServiceImp;
import cafe.service.CategoryService;
import cafe.service.CategoryServiceImp;
import cafe.service.PostService;
import cafe.service.PostServiceImp;
import cafe.service.PrintService;
import cafe.service.PrintServiceImp;

public class BoardController {
	private Scanner scan;
	private BoardService boardService;
	private CategoryController categoryController = new CategoryController(scan);
	private CategoryService categoryService = new CategoryServiceImp();
	public PrintService printService = new PrintServiceImp();
	private PostService postService = new PostServiceImp();
	
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

	private Board boardInput() {
		// 카테고리 리스트 출력
		categoryController.printNumCategory();
		
		// 게시판 추가할 카테고리 선택
		int b_c_num;
		
		while(true) {
			System.out.print("카테고리를 선택하세요 : ");
			b_c_num = scan.nextInt();
			if (categoryController.contains(b_c_num)) {	
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		// 제목 (1~20자) 입력받기
		
		String b_title = null;
		scan.nextLine();
		
		while(true) {
			System.out.print("제목을 입력하세요(1~20자) : ");
			b_title = scan.nextLine();
			if (b_title.length() <= 20 && b_title.length() >0) {
				break;
			}
			System.out.println("제목은 1~20자리만 가능합니다.");
		}
		
		Board board = new Board(b_c_num, b_title);
		
		return board;
		
	}

	private void printBoardService() {
		// 카테고리 리스트 출력
		ArrayList<Board> cBoardList = new ArrayList<Board>();
		ArrayList<Category> categoryList = categoryService.getCategoryList();
		
		for(int i=0; i<categoryList.size(); i++) {
			System.out.println("[" + categoryList.get(i).getC_title() + "]");
			cBoardList = boardService.getBoardList(categoryList.get(i).getC_num());
			if(!printBoardList(cBoardList)) {
				System.out.println("조회 가능한 게시판이 없습니다.");
			}	
		}
		
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

	public boolean printCaBoardList(ArrayList<Board> boardList) {
		if(boardList.isEmpty()) {
			return false;
		}
		for (Board b : boardList) {
			System.out.println(b.toString());
		}
		return true;
	}

	
	
	private void updateBoardService() {
		// 카테고리 리스트 출력
		categoryController.printNumCategory();
		
		// 게시판 수정할 카테고리 선택
		int b_c_num;
		
		while(true) {
			System.out.print("카테고리를 선택하세요 : ");
			b_c_num = scan.nextInt();
			if (categoryController.contains(b_c_num)) {	
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		// 카테고리에 포함된 게시판 리스트 출력
		ArrayList<Board> caBoardList = boardService.selectCaBoardList(b_c_num);
		if(!printCaBoardList(caBoardList)) {
			System.out.println("게시판이 없습니다.");
			return;
		}
		
		int b_num, index;
		while(true) {
			System.out.print("수정할 게시판 번호를 선택하세요 : ");
			b_num = scan.nextInt();
			if(caBoardList.contains(new Board(b_num))) {
				index = caBoardList.indexOf(new Board(b_num));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		Board newBoard = boardUpdateInput(caBoardList.get(index));
		
		newBoard.setB_num(b_num);
		if(boardService.updateBoard(newBoard)) {
			System.out.println(newBoard.toString());
			System.out.println("게시판 제목을 수정하였습니다.");
		} else {
			System.out.println("게시판 제목을 수정하지 못했습니다.");
		}
	}

	private Board boardUpdateInput(Board board) {
		
		System.out.print("수정할 카테고리를 선택하세요 : ");
		int b_c_num = scan.nextInt();
		
		String b_title = null;
		scan.nextLine();
		
		while(true) {
			System.out.print("수정할 제목을 입력하세요(1~20자) : ");
			b_title = scan.nextLine();
			if (b_title.length() <= 20 && b_title.length() >0) {
				break;
			}
			System.out.println("제목은 1~20자리만 가능합니다.");
		}
		
		Board newBoard = new Board(b_c_num, b_title);
		return newBoard;
	}
	
	

	private void deleteBoardServiece() {
		
		// 카테고리 리스트 출력
		categoryController.printNumCategory();
		
		// 게시판 수정할 카테고리 선택
		int b_c_num;
		
		while(true) {
			System.out.print("카테고리를 선택하세요 : ");
			b_c_num = scan.nextInt();
			if (categoryController.contains(b_c_num)) {	
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		
		// 카테고리에 포함된 게시판 리스트 출력
		ArrayList<Board> caBoardList = boardService.selectCaBoardList(b_c_num);
		if(!printCaBoardList(caBoardList)) {
			System.out.println("게시판이 없습니다.");
			return;
		}
				
		int b_num, index;
		
		while(true) {
			System.out.print("삭제할 게시판 번호를 선택하세요 : ");
			b_num = scan.nextInt();
			if(caBoardList.contains(new Board(b_num))) {
				index = caBoardList.indexOf(new Board(b_num));
				break;
			}
			System.out.println("잘못된 번호입니다.");
		}
		//게시판의 게시글을 삭제합니다
		if(postService.deleteBoardPostList(b_num)) {
			System.out.println("게시판의 모든 게시글을 삭제했습니다.");
		}else if(!postService.deleteBoardPostList(b_num)) {
			System.out.println("게시판의 모든 게시글을 삭제했습니다.");
		}
		else {
			System.out.println("게시글을 삭제하지 못했습니다.");
		}
		
		//게시판을 삭제합니다.
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

	public boolean printBoardListBoolean() {
		// 카테고리 리스트 출력
		ArrayList<Board> boardList = boardService.getBoardList();
		ArrayList<Board> cBoardList = new ArrayList<Board>();
		ArrayList<Category> categoryList = categoryService.getCategoryList();
		
		if(boardList.isEmpty()) {
			return false;
		}
		
		for(int i=0; i<categoryList.size(); i++) {
			System.out.println(categoryList.get(i).getC_title());
			cBoardList = boardService.getBoardList(categoryList.get(i).getC_num());
			if(!printBoardList(cBoardList)) {
				System.out.println("입력 가능한 게시판이 없습니다.");
				return false;
			}	
		}
		return true;
	}
	
}

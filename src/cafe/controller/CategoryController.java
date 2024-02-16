package cafe.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import cafe.model.vo.Board;
import cafe.model.vo.Category;
import cafe.service.BoardService;
import cafe.service.BoardServiceImp;
import cafe.service.CategoryService;
import cafe.service.CategoryServiceImp;
import cafe.service.PrintService;
import cafe.service.PrintServiceImp;

public class CategoryController {

	private Scanner scan;
	private CategoryService categoryService;
	public PrintService printService = new PrintServiceImp();
	public BoardService boardService = new BoardServiceImp();
	public BoardController boardController = new BoardController(scan);
	public PostController postController = new PostController(scan, null);
	
	ArrayList<Category> cList = new ArrayList<Category>();
	
	private static final int EXIT_CATEGORY = 5;
	
	public CategoryController(Scanner sc) {
		if(sc == null) {
			sc = new Scanner(System.in);
		}
		this.scan = sc;
		categoryService = new CategoryServiceImp();
	}
	
	public void run() {
		int menu = 0;
		do {
			System.out.println();
			prinCategoryMenu();
			try {
				menu = scan.nextInt();
				runCategoryMenu(menu);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT_CATEGORY);
	}

	private void prinCategoryMenu() {

		System.out.println("╭─────────────────╮");
		System.out.println(" 　꒰ 카테고리 관리 ꒱ ");
		System.out.println("╰─────────────────╯");
		System.out.println("[1] 카테고리 등록");
		System.out.println("[2] 카테고리 수정");
		System.out.println("[3] 카테고리 조회");
		System.out.println("[4] 카테고리 삭제");
		System.out.println("[5] 뒤로 가기");
		System.out.print("[메뉴 선택] ");		
	}

	private void runCategoryMenu(int menu) {
		switch(menu) {
		case 1:
			// 카테고리 등록
			insertCategory();
			break;
		case 2:
			// 카테고리 수정
			updateCategory();
			break;
		case 3:
			// 카테고리 조회
			printCategory();
			break;
		case 4:
			// 카테고리 삭제
			deleteCategory();
			break;
		case 5:
			// 뒤로 가기
			break;
		default:
			// 잘못된 클릭 후 문구 출력
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	
	/** 4. 카테고리 삭제하는 메서드*/
	private void deleteCategory() {
		printNumCategory();
		System.out.println("카테고리 삭제 시 내부 게시판, 게시글도 삭제됩니다. \n 정말 삭제하시겠습니까? (Y/N) : ");
		String delCategory = scan.next();
		
		if ("Y".equalsIgnoreCase(delCategory)) {
			try {
				System.out.print("삭제할 카테고리 번호를 입력하세요: ");
				int c_num = scan.nextInt();
				
				//카테고리에 있는 게시판 리스트
				ArrayList<Board> cBoardList = new ArrayList<Board>();
				cBoardList = boardService.getBoardList(c_num);
				
				//게시판이 있다면 
				if(!cBoardList.isEmpty()) {
					//먼저 게시판의 게시글을 삭제
					for(int i=0; i<cBoardList.size(); i++) {
						//각각의 게시판의 번호를 가져와서 삭제 cBoardList.get(i).getB_num
						postController.deleteBoardPostList(cBoardList.get(i).getB_num());
					}
					//게시판을 삭제
					boardController.deleteCAllBoard(c_num);	
				}
			 
				if (categoryService.deleteCategory(c_num)) {
		            System.out.println("카테고리를 삭제하였습니다.");
		        } else {
		            System.out.println("카테고리 삭제에 실패했습니다.");
		        }
			}catch (InputMismatchException e) {
				System.out.println("오류가 발생되어 카테고리 삭제를 취소합니다.");
	            scan.next(); // 버퍼를 비워줍니다.
			}
		}else {
			System.out.println("카테고리 삭제를 취소합니다.");
		}
    }

	/** 3. 카테고리 전체 조회하는 메서드*/
	public void printCategory() {
		//번호 없이 출력
		cList = categoryService.getCategoryList();
		if(cList.size() == 0) {
			
			System.out.println("등록된 카테고리가 없습니다.");
			return;
		}
		for(Category c : cList) {
			System.out.println(c.toString());
		}
	}
	
	/** 3_1. 카테고리 num 포함 전체 조회하는 메서드*/
	public void printNumCategory() {
		//번호 있도록 출력
		cList = categoryService.getCategoryList();
		if(cList.size() == 0) {
			
			System.out.println("등록된 카테고리가 없습니다.");
			return;
		}
		for(Category c : cList) {
			System.out.println(c.toNumString());
		}		
	}
	
	/** 2. 카테고리 수정하는 메서드*/
	private void updateCategory() {
		printNumCategory();
		System.out.print("수정할 카테고리 번호를 입력하세요 : ");
		int c_num = scan.nextInt();

        System.out.print("수정할 카테고리 이름을 입력하세요 : ");
        String c_title = scan.next();
      
        if (categoryService.updateCategory(c_num, c_title)) {
            System.out.println("카테고리를 수정하였습니다.");
        } else {
            System.out.println("카테고리 수정에 실패했습니다.");
        }
	}
	


	/** 1. 카테고리 등록하는 메서드*/
	private void insertCategory() {
		
		// 정규표현식 체크
		// 카테고리 이름 : 한글, 영어 대소문자, 숫자, 특수문자((!@#$%?(단, 특수문자 중 []를 제외하여 사용))
		
		String regex = "^[가-힣a-zA-Z0-9!@#$%?]{1,15}$";
		String c_title;
		do {
			System.out.print("카테고리 이름 : ");
			scan.nextLine();
			c_title = scan.nextLine();
			
			// 정규표현식 검사
	        if (Pattern.matches(regex, c_title)) {	
	        	//카테고리명 중복체크(중복 시 등록불가)
	        	if (isCategoryNameDuplicate(c_title)) {
	                System.out.println("이미 존재하는 카테고리명입니다. 다른 이름을 입력해주세요.");
	                continue; // 중복된 경우 처음으로 돌아가 다시 입력을 받도록 합니다.
	            }
	        	
	            Category category = new Category(c_title);
	
				//c_num의 경우 AUTO_INCREMENT로 자동생성되기 때문에 등록할 때 필요없습니다.
				//mapper에서 입력하지 않아도 문제가 없습니다.(또는 null입력 시 자동으로 번호가 매겨짐)
	            
				if(categoryService.insertCategory(category)) {
					System.out.println("카테고리를 등록하였습니다.");
					break; // 등록되어 종료
				}else {
					System.out.println("카테고리 등록에 실패했습니다.");
				}
	        }else {
				System.out.println("양식에 맞추어 다시 입력해주세요.\n양식: 1~15글자 이내로 한글, 영어 대소문자, 숫자, 특수문자((!@#$%?(단, []를 제외하여 사용))");
			}
		}while (true);
	}
	// replaceAll 사용 : [,]를 제외한 부분을 가져오기
	private String getFilteredTitle(String input) {
	    return input.replaceAll("[\\[\\]]", "");
	}
	

	// 카테고리명 중복체크 메서드
	private boolean isCategoryNameDuplicate(String c_title) {
		for(int i = 0; i < cList.size(); i++) {
			Category c = cList.get(i);
			if (c.getC_title().equals(c_title)) {
				return true; // 중복된 카테고리명 존재 시
			}
		}
		return false; // 중복된 카테고리명 없을 시
	}
	
	public boolean contains(int b_c_num) {
		cList = categoryService.getCategoryList();
		if(cList.contains(new Category(b_c_num))) {
			return true;
		}
		return false;
	}
}

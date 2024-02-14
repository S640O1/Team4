package cafe.controller;

import java.util.ArrayList;
import java.util.Scanner;

import cafe.model.vo.Category;
import cafe.service.CategoryService;
import cafe.service.CategoryServiceImp;
import cafe.service.PrintService;
import cafe.service.PrintServiceImp;

public class CategoryController {

	private Scanner scan;
	private CategoryService categoryService;
	public PrintService printService = new PrintServiceImp();
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
		int menu;
		do {
			prinCategoryMenu();
			menu = scan.nextInt();
			runCategoryMenu(menu);
		}while(menu != EXIT_CATEGORY);
	}

	private void prinCategoryMenu() {
		System.out.println("카테고리 관리 메뉴");
		System.out.println("-------------------");
		System.out.println("1. 카테고리 등록");
		System.out.println("2. 카테고리 수정");
		System.out.println("3. 카테고리 삭제");
		System.out.println("4. 카테고리 조회");
		System.out.println("5. 뒤로가기");
		System.out.println("-------------------");
		System.out.println("메뉴 선택 : ");		
	}

	private void runCategoryMenu(int menu) {
		switch(menu) {
		case 1:
			insertCategory();
			break;
		case 2:
			updateCategory();
			break;
		case 3:
			deleteCategory();
			break;
		case 4:
			printCategory();
			break;
		case 5:
			System.out.println("뒤로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	/** 4. 카테고리 전체 조회하는 메서드*/
	private void printCategory() {
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
	
	/** 3. 카테고리 삭제하는 메서드*/
	private void deleteCategory() {
		printNumCategory();
		System.out.println("삭제할 카테고리 번호를 입력하세요: ");
		 int c_num = scan.nextInt();
		 
		 if (categoryService.deleteCategory(c_num)) {
	            System.out.println("카테고리를 삭제하였습니다.");
	        } else {
	            System.out.println("카테고리 삭제에 실패했습니다.");
	        }
	    }

	
	/** 2. 카테고리 수정하는 메서드*/
	private void updateCategory() {
		printNumCategory();
		System.out.println("수정할 카테고리 번호를 입력하세요: ");
		int c_num = scan.nextInt();

        System.out.println("수정할 카테고리 이름을 입력하세요: ");
        String c_title = scan.next();
      
        if (categoryService.updateCategory(c_num, c_title)) {
            System.out.println("카테고리를 수정하였습니다.");
        } else {
            System.out.println("카테고리 수정에 실패했습니다.");
        }
	}
	
	private void printNumCategory() {
		cList = categoryService.getCategoryList();
		if(cList.size() == 0) {

			System.out.println("등록된 카테고리가 없습니다.");
			return;
		}
		for(Category c : cList) {
			System.out.println(c.toNumString());
		}		
	}

	/** 1. 카테고리 등록하는 메서드*/
	private void insertCategory() {
		
		//정규표현식 체크
		
		System.out.println("카테고리 이름 : ");
		String c_title = scan.next();
		
		
		Category category = new Category(c_title);
	
		//카테고리명 중복체크(중복 시 등록불가)
	
		
		
		//c_num의 경우 AUTO_INCREMENT로 자동생성되기 때문에 등록할 때 필요없습니다.
		//mapper에서 입력하지 않아도 문제가 없습니다.(또는 null입력 시 자동으로 번호가 매겨짐)
		if(categoryService.insertCategory(category)) {
			System.out.println("카테고리를 등록하였습니다.");
		}else {
			System.out.println("카테고리 등록에 실패했습니다.");
		}
	}
}

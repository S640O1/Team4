package siteCafeManagement.manager.category;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

	@RequiredArgsConstructor
	public class CategoryServiceImp implements CategoryService {
    private final List<Category> categories = new ArrayList<>();

    @Override
    public void addCategory(String categoryName) {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryName);
        categories.add(newCategory);
        System.out.println("카테고리 " + categoryName + "가 추가되었습니다.");
    }

    @Override
    public void updateCategory(String oldCategoryName, String newCategoryName) {
        for (Category category : categories) {
            if (category.getCategoryName().equals(oldCategoryName)) {
                category.setCategoryName(newCategoryName);
                System.out.println("카테고리가 수정되었습니다.");
                return;
            }
        }System.out.println("해당하는 카테고리가 없습니다.");
    }

    @Override
    public void deleteCategory(String categoryName) {
        categories.removeIf(category -> category.getCategoryName().equals(categoryName));
        System.out.println("카테고리 " + categoryName + "가 삭제되었습니다.");
    }

    @Override
    public void printCategories() {
        if (categories.isEmpty()) {
            System.out.println("등록된 카테고리가 없습니다.");
        }else{
            System.out.println("등록된 카테고리 목록:");
            for (Category category : categories) {
                System.out.println(category.getCategoryName());
            }
        }
    }
}
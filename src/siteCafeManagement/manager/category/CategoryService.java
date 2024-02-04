package siteCafeManagement.manager.category;

public interface CategoryService {
	void addCategory();
    void updateCategory(String oldCategoryName, String newCategoryName);
    void deleteCategory(String categoryName);
    void printCategories();
	
}
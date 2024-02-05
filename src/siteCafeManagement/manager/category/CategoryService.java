package siteCafeManagement.manager.category;

public interface CategoryService {
	void addCategory();
	
    void updateCategory(String oldCategoryName, String newCategoryName);
    void updateCategoryService();
    void deleteCategoryService();
    void deleteCategory(String categoryName);
    void printCategories();
	
}
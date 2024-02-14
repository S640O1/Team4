package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.Category;

public interface CategoryService {

	ArrayList<Category> getCategoryList();

	boolean deleteCategory(int c_num);

	boolean updateCategory(Category updatedCategory);

	boolean insertCategory(Category category);
}

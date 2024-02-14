package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.Category;

public interface CategoryService {

	ArrayList<Category> getCategoryList();

	boolean deleteCategory(int c_num);

	boolean updateCategory(int c_num, String c_title);

	boolean insertCategory(Category category);
}

package cafe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cafe.model.vo.Category;

public interface CategoryDAO {

	boolean insertCategory(@Param("category")Category category);

	ArrayList<Category> selectCategoryList();

	int deleteCategory(int c_num); //@param 부터 작업 필요

	int updateCategory(CategoryDAO categoryDao); //@param 부터 작업 필요
}

package cafe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cafe.model.vo.Category;

public interface CategoryDAO {

	boolean insertCategory(@Param("category")Category category);

	ArrayList<Category> selectCategoryList();

	int deleteCategory(@Param("input_c_num")int c_num); 

	int updateCategory(@Param("input_c_num")int c_num, @Param("input_c_title")String c_title); 
}

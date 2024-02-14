package cafe.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cafe.dao.BoardDAO;
import cafe.dao.CategoryDAO;
import cafe.model.vo.Board;
import cafe.model.vo.Category;

public class CategoryServiceImp implements CategoryService {

	private CategoryDAO categoryDao;
	private InputStream inputStream;
	private SqlSession session;
	
	public CategoryServiceImp() {
		String resource = "cafe/config/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			categoryDao = session.getMapper(CategoryDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Category> getCategoryList() {
		return categoryDao.selectCategoryList();
	}

	@Override
	public boolean deleteCategory(int c_num) {
	    try {
            return categoryDao.deleteCategory(c_num) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean updateCategory(int c_num, String c_title) {
		try {
            return categoryDao.updateCategory(categoryDao) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	@Override
	public boolean insertCategory(Category category) {
		return categoryDao.insertCategory(category);
	}
}

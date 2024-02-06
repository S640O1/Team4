package cafe.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cafe.dao.UserDAO;
import cafe.model.vo.User;

public class UserServiceImp implements UserService {

	private UserDAO userDao;
	
	public UserServiceImp() {
		String resource = "cafe/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			userDao = session.getMapper(UserDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertUser(User user) {
		if(user == null || user.getU_id() == null) {
			return false;
		}
		return userDao.insertUser(user);
	}

	@Override
	public ArrayList<User> getUserList() {
		return userDao.selectUserList();
	}
	
	
}

package cafe.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cafe.dao.PostDAO;
import cafe.dao.UserDAO;
import cafe.model.vo.Post;

public class PostServiceImp implements PostService {
	
	private PostDAO postDao;
	
	public PostServiceImp() {
		String resource = "cafe/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertPost(Post post) {
		if(post == null) {
			System.out.println("Null");
			return false;
		}
		System.out.println("postDao호출");
		return postDao.insertPost(post);
	}


}

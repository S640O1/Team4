package cafe.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cafe.dao.PostDAO;
import cafe.model.vo.Post;
import cafe.pagination.Criteria;

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
			return false;
		}
		return postDao.insertPost(post);
	}

	@Override
	public ArrayList<Post> getPostList() {
		return postDao.selectPostList();
	}
	
	@Override
	public ArrayList<Post> getMyPostList(String u_id) {
		return postDao.selectMyPostList(u_id);
	}
	

	/** post 1개를 가져오는 메소드*/
	@Override
	public Post getPost(int p_num) {
		return postDao.selectPost(p_num);
	}

	@Override
	public boolean updatePost(Post setPost) {
		if(setPost == null) {
			return false;
		}
		
		return postDao.updatePost(setPost);
	}

	@Override
	public boolean deletePost(int p_num) {
		return postDao.deletePost(p_num);
	}

	@Override
	public boolean deleteBoardPostList(int p_b_num) {
		return postDao.deleteBoardPostList(p_b_num);
	}

	
	@Override
	public ArrayList<Post> getBoardPostList(int p_b_num) {
		return postDao.selectBoardPostList(p_b_num);
	}

	@Override
	public ArrayList<Post> getPostListPage(Criteria cri) {
		if(cri == null) {
			cri = new Criteria();
		}
		return postDao.selectPostListPage(cri);
	}

	@Override
	public ArrayList<Post> getBoardPostListPage(Criteria cri, int p_b_num) {
		if(cri == null) {
			cri = new Criteria();
		}
		return postDao.selectBoardPostListPage(cri, p_b_num);
	}

	@Override
	public ArrayList<Post> getMyPostListPage(Criteria cri, String u_id) {
		if(cri == null) {
			cri = new Criteria();
		}
		return postDao.selectMyPostListPage(cri, u_id);
	}

	


}

package cafe.service;

import cafe.dao.PostDAO;
import cafe.model.vo.Post;

public class PostServiceImp implements PostService {
	
	private PostDAO postDao;

	@Override
	public boolean insertPost(Post post) {
		if(post == null) {
			return false;
		}
		return postDao.insertPost(post);
	}


}

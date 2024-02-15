package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.Post;

public interface PostService {

	boolean insertPost(Post post);

	ArrayList<Post> getPostList();

	Post getPost(int p_num);

	ArrayList<Post> getMyPostList(String u_id);

	boolean updatePost(Post setPost);

	boolean deletePost(int p_num);

	ArrayList<Post> getBoardPostList(int p_b_num);



}

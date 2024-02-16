package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.Post;
import cafe.pagination.Criteria;

public interface PostService {

	boolean insertPost(Post post);

	ArrayList<Post> getPostList();

	Post getPost(int p_num);

	ArrayList<Post> getMyPostList(String u_id);

	boolean updatePost(Post setPost);

	boolean deletePost(int p_num);

	ArrayList<Post> getBoardPostList(int p_b_num);

	ArrayList<Post> getPostListPage(Criteria cri);

	ArrayList<Post> getBoardPostListPage(Criteria cri, int p_b_num);

	ArrayList<Post> getMyPostListPage(Criteria cri, String u_id);

	boolean deleteBoardPostList(int p_b_num);




}

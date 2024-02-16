package cafe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cafe.model.vo.Post;
import cafe.pagination.Criteria;


public interface PostDAO {

	boolean insertPost(@Param("post")Post post);

	ArrayList<Post> selectPostList();

	Post selectPost(@Param("p_num")int p_num);

	ArrayList<Post> selectMyPostList(@Param("u_id")String u_id);

	boolean updatePost(@Param("post")Post setPost);

	boolean deletePost(@Param("p_num")int p_num);

	boolean deleteBoardPostList(@Param("p_b_num")int p_b_num);

	ArrayList<Post> selectBoardPostList(@Param("p_b_num")int p_b_num);

	ArrayList<Post> selectBoardPostListPage(@Param("cri")Criteria cri, @Param("p_b_num")int p_b_num);
	
	ArrayList<Post> selectPostListPage(@Param("cri")Criteria cri);

	ArrayList<Post> selectMyPostListPage(@Param("cri")Criteria cri, @Param("u_id")String u_id);


}

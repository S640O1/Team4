package siteCafeManagement.post;

import java.util.List;

import siteCafeManagement.manager.board.Board;

public interface PostService {
	
	void addPostService(List<Post> categoryList, List<Board> boardList, List<Post> postList);
	void printPostService(List<Post> postList);
	void setPostService(List<Post> postList);
	void deletePostService(List<Post> postList);
	
	void addPost(List<Post> postList, Post post);
	boolean printPostList(List<Post> postList);
	void printPost(List<Post> postList, int index);
	void setPost(List<Post> postList, int index, String title, String content);
	void deletePost(List<Post> postList, int index);
	boolean printUserPostList(List<Post> postList);
	
	
}

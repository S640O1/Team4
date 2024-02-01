package siteCafeManagement.post;

import java.util.List;

public interface PostService {
	
	void addPostService(List<Post> postList);
	void printPostService(List<Post> postList);
	void setPostService(List<Post> postList);
	void deletePostService(List<Post> postList);
	
	void addPost(List<Post> postList, Post post);
	void printPostList(List<Post> postList);
	void printPost(List<Post> postList);
	void setPost(List<Post> postList, int index, String title, String content);
	void deletePost(List<Post> postList);
	
	
}

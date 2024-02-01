package siteCafeManagement.user;

public interface PostService {
	
	void addPostService();
	void printPostService();
	void setPostService();
	void deletePostService();
	
	void addPost(Post post);
	void printPostList();
	void printPost();
	void setPost(int index, String title, String content);
	void deletePost();
	
	
}

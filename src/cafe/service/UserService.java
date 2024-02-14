package cafe.service;

import java.util.ArrayList;

import cafe.model.vo.User;

public interface UserService {

	boolean insertUser(User user);

	ArrayList<User> getUserList();

	ArrayList<User> getUserId(String id);

	
}

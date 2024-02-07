package cafe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cafe.model.vo.User;

public interface UserDAO {

	boolean insertUser(@Param("user")User user);

	ArrayList<User> selectUserList();

}

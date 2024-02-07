package cafe.dao;

import org.apache.ibatis.annotations.Param;

import cafe.model.vo.Post;

public interface PostDAO {

	boolean insertPost(@Param("post")Post post);

}

package siteCafeManagement.user;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
	사용자 : 아이디, 비밀번호, email, nickName, 유저등급(level)
	- 아이디가 같으면 중복 불가
	- 비밀번호, 이메일, 닉네임은 수정가능
	- level
		: 1level - 게시글 51~
		: 2level - 게시글 21~50개
		: 3level - 게시글 11~20개
		: 4level - 게시글 6~10개
		: 5level - 게시글 0~5개
		
*/	
@Data
@AllArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 5804120004791065508L;
	int id, pw, level;
	String email, nickName;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}

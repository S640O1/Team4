package cafe.model.vo;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String u_id;
	private String u_pw;
	private String u_nickName;
	private String u_phone;
	private String u_birth;
	private int u_gender;
	
	//id가 같을 때(중복 확인)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(u_id, other.u_id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(u_id);
	}
	
	//아이디 메서드
	public User(String u_id) {
		this.u_id = u_id;
	}
	
	//회원가입 시 완료 시 뜨는 창
	//양선진(qwer1234)님 회원가입이 완료되었습니다.
	@Override
	public String toString() {
		return u_nickName+"("+u_id+")"+"님"+ " 회원가입이 완료되었습니다.";
	}
}

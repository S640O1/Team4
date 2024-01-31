package siteCafeManagement.membership;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import siteCafeManagement.MainProgram;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership implements Serializable {
	
	private static final long serialVersionUID = 1216589789038238596L;
	
	//아이디, 비밀번호, 비밀번호 확인, 이름, 휴대폰 번호(010-1234-5678)
	private String id, pw, pw2, name, phoneNumber;	
	private int idNumber, gender;	//주민등록번호(6자리)-성별(남:1,3/여:2,4) ex)981009-1,3(남자)/2,4(여자) 
	
	//회원 생성자
	public Membership(String id, String pw, String name, String phoneNumber, int idNumber, int gender) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.idNumber = idNumber;
		this.gender = gender;
	}
	
	// 아이디가 같을 때(중복 확인)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membership other = (Membership) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	//회원가입 시 완료 시 뜨는 창
	//양선진(qwer1234)님 회원가입이 완료되었습니다.
	@Override
	public String toString() {
		return name+"("+id+")"+"님"+ " 회원가입이 완료되었습니다.";
	}

	
}

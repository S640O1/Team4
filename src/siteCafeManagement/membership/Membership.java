package siteCafeManagement.membership;

import java.io.Serializable;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import siteCafeManagement.MainProgram;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership implements Serializable {

//	public static Scanner scan = MainProgram.scan;
	private static final long serialVersionUID = 1216589789038238596L;
	
	// 정규표현식 활용 어떻게 하는지 확인
	//아이디, 비밀번호, 비밀번호 확인, 이름, 휴대폰 번호(010-1234-5678)
	private String id, pw, pw2, name, phoneNumber;	
	private int idNumber;	//주민등록번호 앞 8자리 ex)1998.10.09
	private char gender;	//성별
	
	public Membership(String id, String pw, String name, String phoneNumber, int idNumber, char gender) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.idNumber = idNumber;
		this.gender = gender;
	}
	
	//회원가입 시 완료 시 뜨는 창
	//양선진님(qwer1234) 회원가입이 완료되었습니다.
	@Override
	public String toString() {
		return name +"님"+ "("+id+")" + " 회원가입이 완료되었습니다.";
	}
	
}

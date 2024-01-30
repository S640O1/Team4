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
public class membership implements Serializable {

	private static final long serialVersionUID = 1216589789038238596L;
	public static Scanner scan = MainProgram.scan;
	
	// 정규표현식 활용 어떻게 하는지 확인
	private String id, pw, pw2, phoneNumber;	//아이디, 비밀번호, 비밀번호 확인, 휴대폰 번호
	private int idNumber;	//주민등록번호 앞 8자리 ex)19981009
	private char gender;	//성별
	
}

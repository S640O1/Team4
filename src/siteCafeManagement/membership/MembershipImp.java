package siteCafeManagement.membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import siteCafeManagement.MainProgram;

public class MembershipImp implements MembershipInterface {

	Scanner scan = MainProgram.scan;
	List<Membership> memberShip = new ArrayList<Membership>();
	
	
	@Override
	public void logIn() {
		
	}

	@Override
	public void logOut() {
		
	}

	@Override
	public void join() {
		//아이디, 비밀번호 정규표현식
		String idRegex = "^[a-zA-Z0-9!@#$%^&*()]{8,12}$";
		String pwRegex = "^[a-zA-Z0-9!@#$%^&*()]{8,15}+$";
		
		//아이디
		System.out.print("");
		scan.nextLine();
		//비밀번호
		System.out.print("");
		scan.nextLine();
		//비밀번호 확인
		System.out.print("");
		scan.nextLine();
		
	}

}

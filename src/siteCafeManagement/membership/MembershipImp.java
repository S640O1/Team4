package siteCafeManagement.membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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

	/**
	 * 회원가입 : 아이디, 비밀번호, 비밀번호 확인, 이름, 주민등록번호-성별(1,3남자/2,4여성), 전화번호
	 */
	@Override
	public void join() {
		//아이디, 비밀번호 정규표현식
		String idRegex = "^[a-zA-Z0-9!@#$%^&*]{8,12}$";
		String pwRegex = "^[a-zA-Z0-9!@#$%^&*]{8,15}$";
		int index = 0;
		
		System.out.print("생성할 아이디 입력 : ");
		scan.nextLine();
		String id = scan.nextLine();
		//만약 있는 아이디이면 다른 아이디 입력
		for(int i=0;i<memberShip.size();i++) {
			if(memberShip.get(index).getId()) {
				System.out.println("이미 있는 아이디입니다.");
				return;
			}
		}
		if(!Pattern.matches(idRegex, id)) {
			System.out.println("8자~ 12자 영어 대 · 소문자, 숫자, 특수문자(!@#$%^&*)만 사용할 수 있습니다.");
			return;
		}			
		
		//비밀번호
		System.out.print("생성할 비밀번호 입력 : ");
		String pw = scan.nextLine();
		if(!Pattern.matches(pwRegex, pw)) {
			System.out.println("8~15자리 영어 대 · 소문자, 숫자, 특수문자(!@#$%^&*)만 사용할 수 있습니다.");
			return;
		}
		//비밀번호 확인
		String pwCheck = null;
		do {
			System.out.print("생성한 비밀번호 확인 : ");
			pwCheck = scan.nextLine();
			if(!pw.equals(pwCheck)) {
				System.out.println("비밀번호를 다시 입력하세요");
			}
		}while(!pw.equals(pwCheck));
		
		//이름
		System.out.print("이름 : ");
		String name = scan.nextLine();
	}

}

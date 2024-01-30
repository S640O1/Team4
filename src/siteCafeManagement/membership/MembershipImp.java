package siteCafeManagement.membership;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import siteCafeManagement.MainProgram;

public class MembershipImp implements MembershipService {

	Scanner scan = MainProgram.scan;
	List<Membership> mList = new ArrayList<Membership>(); 
	
	@Override
	public void logIn() {
		
	}

	@Override
	public void logOut() {
		
	}

	/**
	 * 회원가입 : 아이디, 비밀번호, 비밀번호 확인, 이름, 주민등록번호(6자리)-성별(남:1,3/여:2,4), 전화번호(01X-XXXX-XXXX)
	 */
	@Override
	public void join() {
		//아이디, 비밀번호 정규표현식
		String idRegex = "^[a-zA-Z0-9!@#$%^&*]{8,12}$";
		String pwRegex = "^[a-zA-Z0-9!@#$%^&*]{8,15}$";
		
		//mList에서 있는 아이디와 같은 아이디가 있다면 중복출력 후, 다른 아이디 입력
		String id = null;
		scan.nextLine();
		do {
			System.out.print("생성할 아이디 입력 : ");
			id = scan.nextLine();
			//아이디 중복 ***중복이 안걸러짐!!!			
				if(mList.contains(id)) {
					System.out.println("이미 있는 아이디입니다.");
				}
			//아이디 정규표현식
			if(!Pattern.matches(idRegex, id)) {
				System.out.println
				("8자~12자 영어 대 · 소문자, 숫자, 특수문자(!@#$%^&*)만 사용할 수 있습니다.");
			}
			// mList에 id가 포함되거나 아이디 정규표현식이 맞지 않을 동안 순환
			// = mList에 id가 포함되지 않거나 + 정규표현식이 맞으면 나옴.(개념 헷갈...)
			// 하나만 false라도 다시 순환해야 함 -> || (&&을 쓰면 둘다 true거나 false여야 순환해서 계속 나왔음)
		}while(mList.contains(id) || !Pattern.matches(idRegex, id));
		
//		이걸 쓸려면 메서드를 boolean으로 해야 함...
//		//mList에서 있는 아이디와 같은 아이디가 있다면 중복출력 후, 다른 아이디 입력
//		String id = null;
//		int i = 0;
//		scan.nextLine();
//		do {
//			System.out.print("생성할 아이디 입력 : ");
//			id = scan.nextLine();
//			//아이디 중복
//			for(i=0;i<mList.size();i++) {
//				if(mList.get(i).getId().contains(id)) {
//					System.out.println("이미 있는 아이디입니다.");
//					return false;
//				}
//			}
//			//아이디 정규표현식
//			if(!Pattern.matches(idRegex, id)) {
//				System.out.println
//				("8자~12자 영어 대 · 소문자, 숫자, 특수문자(!@#$%^&*)만 사용할 수 있습니다.");
//				return false;
//			}
//			return true;
//			// mList에 id가 포함되거나 아이디 정규표현식이 맞지 않을 동안 순환
//			// = mList에 id가 포함되지 않거나 + 정규표현식이 맞으면 나옴.(개념 헷갈...)
//			// 하나만 false라도 다시 순환해야 함 -> || (&&을 쓰면 둘다 true거나 false여야 순환해서 계속 나왔음)
//		}while(mList.get(i).getId().contains(id) || !Pattern.matches(idRegex, id));
		
		//비밀번호
		String pw = null;
		do {
			System.out.print("생성할 비밀번호 입력 : ");
			pw = scan.nextLine();
			if(!Pattern.matches(pwRegex, pw)) {
				System.out.println
				("8~15자리 영어 대 · 소문자, 숫자, 특수문자(!@#$%^&*)만 사용할 수 있습니다.");
			}	
		}while(!Pattern.matches(pwRegex, pw));
		
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
		
		//주민등록번호 앞 6자리-성별(남:1,3/여:2,4)
		//주민등록번호 앞 6자리
		System.out.print("주민등록번호 앞 6자리 : ");
		int idNumber = scan.nextInt();
		//성별
		int gender = 0;
		do {			
			try {
				System.out.print("주민등록번호 뒤 1자리(성별) : ");
				gender = scan.nextInt();
				//확인용
				switch(gender) {
				case 1, 3 : 
					System.out.println(idNumber+"-"+gender);
				break;
				case 2, 4 :
					System.out.println(idNumber+"-"+gender);
				break;
				default : throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("주민등록번호 뒤 1자리를 다시 입력하세요.");
			}
		}while(gender != 1 && gender != 2 && gender != 3 && gender != 4); //이것도 헷갈
		
		//전화번호(01X-XXXX-XXXX)
		String phoneRegex = "^01(?:[0-9])-\\d{4}-\\d{4}$";
		String phoneNumber = null;
		scan.nextLine();
		do {			
			System.out.print("전화번호 입력(양식 : 01X-XXXX-XXXX) : ");
			phoneNumber = scan.nextLine();
			if(!Pattern.matches(phoneRegex, phoneNumber)) {
				System.out.println("전화번호 양식에 맞게 입력하세요.");
			}
		}while(!Pattern.matches(phoneRegex, phoneNumber));
		
		//회원 인스턴스 생성
		Membership membership = new Membership(id, pw, name, phoneNumber, idNumber, gender);
		//회원 list에 저장
		mList.add(membership);
		System.out.println(membership);
	}

}

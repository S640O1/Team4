 package cafe.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import cafe.model.vo.User;
import cafe.service.UserService;
import cafe.service.UserServiceImp;

public class UserController {

	private Scanner sc;
	private UserService userService;
	
	public UserController(Scanner sc) {
		if(sc == null) {
			sc = new Scanner(System.in);
		}
		this.sc = sc;
		userService = new UserServiceImp();
	}

	/**
	 * 회원가입
	 */
	public void join() {
		User user = joinInput();
		if(userService.insertUser(user)) {
			System.out.println("내역을 추가했습니다.");
		}else {
			System.out.println("내역을 추가하지 못했습니다.");
		}
	}
		
	
	public User joinInput() {
		  ArrayList<User> uList = userService.getUserList();
		//아이디, 비밀번호 정규표현식
		String idRegex = "^[a-zA-Z0-9]{8,12}$";
		
		//mList에서 있는 아이디와 같은 아이디가 있다면 중복출력 후, 다른 아이디 입력
		String id = null;
		sc.nextLine();
		boolean idTrue = true;
		do {
			System.out.print("생성할 아이디 입력 : ");
			id = sc.nextLine();
			//아이디 중복 해결
			//contains는 앞 객체.와 (괄호 안의 객체)의 "클래스" 를 비교, 아예 다른 클래스면 비교자체를 안함(false)
			//mList와 new Membership(id)의 클래스가 같아야 비교를 함
			//1. 생성자 추가(new Membership(id)) 2. Membership 클래스에 메서드 추가 3. id를 이용한 equals로 오버라이딩
			User user = new User(id);
			if(uList.contains(new User(id))) {
				System.out.println("이미 있는 아이디입니다.");
				idTrue = false;
			}
			//아이디 정규표현식
			if(!Pattern.matches(idRegex, id)) {
				System.out.println
				("8자~12자 영어 대 · 소문자, 숫자만 사용할 수 있습니다.");
				idTrue = false;
			}
			// mList에 Membership의 멤버변수 id가 포함되거나 아이디 정규표현식이 맞지 않을 동안 순환
			// = mList에 id가 포함되지 않거나 + 정규표현식이 맞으면 나옴.(개념 헷갈...)
			// 하나만 false라도 다시 순환해야 함 -> || (&&을 쓰면 둘다 true거나 false여야 순환해서 계속 나왔음)
		}while(!idTrue);
		
		//비밀번호
		String pwRegex = "^[a-zA-Z0-9!@#$%^&*]{8,15}$";
		String pw = null;
		do {
			System.out.print("생성할 비밀번호 입력 : ");
			pw = sc.nextLine();
			if(!Pattern.matches(pwRegex, pw)) {
				System.out.println
				("8~15자리 영어 대 · 소문자, 숫자, 특수문자(!@#$%^&*)만 사용할 수 있습니다.");
			}	
		}while(!Pattern.matches(pwRegex, pw));
		
		//비밀번호 확인
		String pwCheck = null;
		do {
			System.out.print("생성한 비밀번호 확인 : ");
			pwCheck = sc.nextLine();
			if(!pw.equals(pwCheck)) {
				System.out.println("비밀번호를 다시 입력하세요");
			}
		}while(!pw.equals(pwCheck));
		
		//닉네임(정규표현식)
		//2~10자 한글, 영어 사용 가능
		String nickNameRegex = "^[a-zA-Zㄱ-힣]{2,10}$";
		String nickName = null;
		do {			
			System.out.print("닉네임 : ");
			nickName = sc.nextLine();
			if(!Pattern.matches(nickNameRegex, nickName)) {
				System.out.println("2~10자 이내의 한글, 영어만 가능합니다.");
			}
		}while(!Pattern.matches(nickNameRegex, nickName));
		
		//생년월일(주민등록번호 앞 6자리)-성별(남:1,3/여:2,4)
		//주민등록번호 앞 6자리
		String birthRegex = "^[0-9]{6}$";
		String birth = null;
		do {			
			System.out.print("생년월일(주민등록번호 앞 6자리) : ");
			birth = sc.nextLine();
			if(!Pattern.matches(birthRegex, birth)) {
				System.out.println("주민등록번호 앞 6자리를 입력하세요.");
			}
		}while(!Pattern.matches(birthRegex, birth));
		
		//성별
		int gender = 0;
		do {			
			try {
				System.out.print("주민등록번호 뒤 1자리(성별) : ");
				gender = sc.nextInt();
				//확인용
				switch(gender) {
				case 1, 3 : 
					System.out.println(birth+"-"+gender+"(남성)");
				break;
				case 2, 4 :
					System.out.println(birth+"-"+gender+"(여성)");
				break;
				default : throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("주민등록번호 뒤 1자리를 다시 입력하세요.");
			}
		//성별이 1, 2, 3, 4가 아니면 순환 / 성별이 1, 2, 3, 4 중 하나이면 나옴
		}while(gender != 1 && gender != 2 && gender != 3 && gender != 4);
		
		//전화번호(01X-XXXX-XXXX)
		String phoneRegex = "^01(?:[0-9])-[0-9]{4}-[0-9]{4}$";
		String phoneNumber = null;
		sc.nextLine();
		do {			
			System.out.print("전화번호 입력(양식 : 01X-XXXX-XXXX) : ");
			phoneNumber = sc.nextLine();
			if(!Pattern.matches(phoneRegex, phoneNumber)) {
				System.out.println("01X-XXXX-XXXX 양식으로 정수를 입력해주세요.");
			}
		}while(!Pattern.matches(phoneRegex, phoneNumber));

		User user = new User(id, pw, nickName, phoneNumber, birth, gender);
		return user;
				
	}



	public void logIn() {

		//로그인 됐다면
		//관리자 아이디일경우
			adminService();
		
		//아닐경우
			//게시판 컨트롤러 메소드 불러오기

		
		
	}

	private void adminService() {
		int menu = 0;
		
		do {
			System.out.println();
			printAdminMenu();
			try {
				menu = sc.nextInt();
				runAdminMenu(menu);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				sc.nextLine();
			}
		}while(menu != 5);
		
	}

	private void runAdminMenu(int menu) {
		switch(menu) {
		
		}
		
	}

	private void printAdminMenu() {
		// 1. 카테고리 관리
		// 2. 게시판 관리
	}

}

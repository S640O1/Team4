 package cafe.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import cafe.main.Main;
import cafe.model.vo.User;
import cafe.service.UserService;
import cafe.service.UserServiceImp;

public class UserController {

	private static Scanner sc;
	private Main main = new Main();
	private UserService userService;
	private static final int LOGOUT_ADMIN = 3;

	//controller
	private CategoryController categoryController = new CategoryController();
	private BoardController boardController = new BoardController(sc);
	private static PostController postController;
	
	public UserController(Scanner sc) {
		if(sc == null) {
			sc = new Scanner(System.in);
		}
		this.sc = sc;
		userService = new UserServiceImp();
	}
	
	/**
	 * 1. 로그인
	 * @param id : user 아이디
	 * @param pw : user 비밀번호
	 */
	ArrayList<User> uList = new ArrayList<User>();	
	public void logIn() {
		uList = userService.getUserList();
		//scan한 id, pw
		User user = logInInput();
		//uList의 인덱스에서 user 생성자 getU_id
		int index = uList.indexOf(new User(user.getU_id()));

		int menu = 0;
		//user list 안에 있는 id, pw가 포함된다면
		if(uList.contains(user)) {
			//id와 pw가 admin123과 같으면 관리자 모드
			//관리자모드(카테고리 컨트롤러 + 보드 컨트롤러)
			if(user.getU_id().equals("admin123") && user.getU_pw().equals("admin123")) {
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
				}while(menu != LOGOUT_ADMIN);
			}
			
			//회원모드(게시글 컨트롤러)
			//아이디만 일치하면 통과됨
			else if(!user.getU_id().equals("admin123") 
					//user(스캔한 user)와 같다(uList의 인덱스에서 가져온 id)
					&& user.getU_id().equals(uList.get(index).getU_id())
					&& user.getU_pw().equals(uList.get(index).getU_pw())) {
				postController = new PostController(sc, user);
				postController.run();
			}
			
			//아이디나 비밀번호가 다를 때
			else {
				System.out.println("비밀번호를 다시 입력하세요.");
				return;
			}
		}
		System.out.println("아이디를 다시 입력하세요.");
		return;
	}
	
	private void printAdminMenu() {
		System.out.println("[KH Cafe 관리자 모드]");
		System.out.println("1. 카테고리 관리");
		System.out.println("2. 게시판 관리");
		System.out.println("3. 로그아웃");
		System.out.print("메뉴 선택 : ");
	}
	
	private void runAdminMenu(int menu) {
		switch(menu) {
		case 1 : 
			//categoryController.run();
			break;
		case 2 : 
			boardController.run();
			break;
		case 3 : 
			logOut();
			break;
		default : throw new InputMismatchException();
		}
		
	}

	private User logInInput() {
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비번 : ");
		String pw = sc.next();
		
		User user = new User(id,pw);
		return user;
	}

	/**
	 * 2. 회원가입
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
		String idRegex = "^[a-zA-Z0-9]{8,12}$";
		
		//아이디 : uList에서 있는 아이디와 같은 아이디가 있다면 중복출력 후, 다른 아이디 입력
		String id = null;
		sc.nextLine();
		do {
			//아이디 중복
			//문제점 : 이미 있는 아이디를 입력할 때 다음에 중복안된 아이디를 입력해도 계속 생성할 아이디 입력이 뜬다.
			// -> 해결 : boolean을 빼고 원래 쓰던 문법을 while문에 넣음
			System.out.print("생성할 아이디 입력 : ");
			id = sc.nextLine();
			if(uList.contains(new User(id))) {
				System.out.println("이미 있는 아이디입니다.");
			}
			//아이디 정규표현식
			if(!Pattern.matches(idRegex, id)) {
				System.out.println
				("8자~12자 영어 대 · 소문자, 숫자만 사용할 수 있습니다.");
			}
		}while(uList.contains(new User(id)) || !Pattern.matches(idRegex, id));
		
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
	
	/**
	 * 로그아웃
	 */
	// 현재 로그인된 아이디를 가져와서.equals 지금 입력한 아이디랑 같으면 로그아웃? -> 현재 로그인된 아이디는 어떻게 가져오나?
	public void logOut() {
		System.out.println("로그아웃 되었습니다.");
		Main.main(null);
	}
		
//		ArrayList<User> uList = new ArrayList<User>();	
//		uList = userService.getUserList();
//		try {
//			System.out.print("로그아웃 하시겠습니까? (하려면 본인 아이디 입력) ");
//			String id = sc.next();
//			User user = new User(id);
//			int index = uList.indexOf(new User(user.getU_id()));
//			if(id.equals(uList.get(index).getU_id())) {
//				System.out.println("로그아웃 되었습니다.");
//				user = new User(null, null);
//				Main.main(null);
//			} else if(!id.equals(uList.get(index).getU_id())) {
//				System.out.println("아이디를 잘못 입력하였습니다.");
//				return;
//			} else {
//				System.out.println("else문");
//			}
//		}catch(Exception e) {
//			e.setStackTrace(null);
//		}finally {
//			System.out.println("finally");
//		}
//	}

//	public boolean logOut() {
//		//user를 null값으로 만들기? -> nullpointer예외 계속 발생
//		ArrayList<User> uList = new ArrayList<User>();
//		System.out.print("로그아웃 하시겠습니까?(하실려면 본인 아이디 입력) ");
//		String id = sc.next();
//		User user = new User(id);
//		int index = uList.indexOf(new User(user.getU_id()));
//		if(user.getU_id().equals(uList.get(index).getU_id())) {
//			System.out.println("로그아웃 되었습니다.");
//			main.printPreLogInMenu();
//			return true;
//		}
//		else {
//			System.out.println("잘못 입력하셨습니다.");
//			return false;
//		}
//	}

//	public boolean logOut() {
//		//user를 null값으로 만들기? -> nullpointer예외 계속 발생
//		ArrayList<User> uList = new ArrayList<User>();
//		System.out.print("로그아웃 하시겠습니까?(하실려면 본인 아이디 입력) ");
//		String id = sc.next();
//		User user = new User(id);
//		int index = uList.indexOf(new User(user.getU_id()));
//		if(user.getU_id().equals(uList.get(index).getU_id())) {
//			System.out.println("로그아웃 되었습니다.");
//			main.printPreLogInMenu();
//			return true;
//		}
//		else {
//			System.out.println("잘못 입력하셨습니다.");
//			return false;
//		}
//	}
	
//	int areYouLogOut = 1;
//	public void logOut() {
//		logOutInput(areYouLogOut);
//	}
//	
//	public void logOut() {
//		int areYouLogOut = 1;
//		System.out.println("로그아웃 하시겠습니까?(1:네 / 2:아니오)");
//		if(areYouLogOut == 1) {
//			System.out.println("로그아웃 되었습니다.");
//			main.printPreLogInMenu();
//		} else if(areYouLogOut == 2) {
//			System.out.println("로그아웃이 취소되었습니다.");
//			return;
//		} else {
//			System.out.println("잘못된 번호입니다.");
//			return;
//		}
//		
//		
////		System.out.println("로그아웃 합니다.");
////		ArrayList<User> uList = userService.getUserList();
////		uList.remove(new User(null, null));
//		
//	}

}

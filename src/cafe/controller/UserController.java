 package cafe.controller;

import java.util.Scanner;

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
		
	}

}

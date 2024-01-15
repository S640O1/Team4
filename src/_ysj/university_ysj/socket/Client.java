package _ysj.university_ysj.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {
	private Socket socket;
	
	//read : 읽기
	public void read() {
		Thread t = new Thread(()->{
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				while(true) {
					String str = ois.readUTF();
					if(str.equals("-1")) {
						break;
					}
					System.out.println(str);
				}
				System.out.println("불러오기 기능이 정상 종료됩니다.");
			} catch(Exception e) {
				System.out.println("예외가 발생해서 불러오기 기능을 종료합니다.");
			}
		});
		t.start();
	}
	
	//write : 쓰기
	 public void write() {
		 Thread t = new Thread(()->{
			 ObjectOutputStream oos = null;
			 try {
				 oos = new ObjectOutputStream(socket.getOutputStream());
				 Scanner sc = new Scanner(System.in);
				 while(true) {
					 String str = sc.nextLine();
					 oos.writeUTF(str);
					 oos.flush();
					 if(str.equals("-1")) {
						 break;
					 }
				 }
				 System.out.println("저장 기능이 정상 종료됩니다.");
			 } catch(IOException e) {
				 System.out.println("예외가 발생해서 저장 기능을 종료합니다.");
			 }
		 });
		 t.start();
	 }
}

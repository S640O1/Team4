package _ysj.university_ysj.socket;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
	
	public static void main(String []args) {
		String ip = "000.000.00.000";
		int port = 0000;
		try {
			Socket socket = new Socket(ip, port);
			Client client = new Client(socket);
			client.write();
			client.read();
		} catch(IOException e) {
			System.out.println("클라이언트에서 예외가 발생하여 종료합니다.");
		}
	}
}

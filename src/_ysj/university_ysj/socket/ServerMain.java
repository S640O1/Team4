package _ysj.university_ysj.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) {
		//포트 입력
		int port = 0000;
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			while(true) {
				Socket socket = serverSocket.accept();
				Client client = new Client(socket);
				client.write();
				client.read();
			}
		} catch (IOException e) {
			System.out.println("서버에서 예외가 발생하여 종료합니다.");
		}
	}

}

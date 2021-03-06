/* 소켓 프로그래밍: connectionful 서버 만들기 */

package step18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class ChatApp {
  
  class Value {
    String userId;
    String content;
  }
  
  class ChatServer implements Runnable {
    Socket socket ;
    
    public ChatServer(Socket socket) {
      this.socket = socket;
      System.out.println("=> 클라이언트와 연결되었음!");
    }// CalculatorJob()
    
    public synchronized void run() {
      try 
      (
      Socket socket = this.socket;
      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      PrintStream out = new PrintStream(socket.getOutputStream());
      ) {
        while (true) {
        String json = in.readLine();
        if (json == null)
          break;
        System.out.println(json);
        Gson gson = new Gson();
        Value value = gson.fromJson(json, Value.class);

        out.printf("[%s] : %s", value.userId, value.content);
//        out.flush();
        } //while
        System.out.println("=> 클라이언트와 연결 끊김!");
      } catch (Exception e) {
        e.printStackTrace();
      }
    } // run()
    
  } // class ChatServer
  
  public void Listen(int port) throws Exception {
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("step18 Test07_1 서버 실행중");
    
    while (true) {
      new Thread(new ChatServer(serverSocket.accept())).start();
      
      
    } // while
    
  } // class Listen()
  
  public static void main(String[] args) throws Exception {
    
    ChatApp server1 = new ChatApp();
    ChatApp server2 = new ChatApp();
    
    server1.Listen(8888);
    server2.Listen(8888);
    
  }  // main
}

package study.thisIsJava.network.tcp.exam01;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("localhost", 8888);
      System.out.println("[client] connection is success");
      socket.close();
      System.out.println("[client] connection is over");

    } catch (UnknownHostException e) {

    } catch (IOException e) {

    }
  }
}

package study.thisIsJava.network.tcp.exam01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SeverExample {
  private static ServerSocket serverSocket = null;

  public static void main(String[] args) {
    System.out.println("-------------");
    System.out.println("to Quit input q");
    System.out.println("-------------");

    startServer();

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String key = scanner.nextLine();
      if (key.toLowerCase().equals("q")) {
        break;
      }
    }
    scanner.close();

    stopServer();
  }

  public static void startServer() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          serverSocket = new ServerSocket(8888);
          System.out.println("[server] is startted");
          while (true) {
            System.out.println("[server] is ready");
            Socket socket = serverSocket.accept();
            InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.println("[server] " + isa.getHostString() + " is Conneted");

            socket.close();
            System.out.println("[server] " + isa.getHostString() + " is Disconneted");
          }
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }).start();
  }

  public static void stopServer() {
    try {
      serverSocket.close();
      System.out.println("[server] is Over");
    } catch (IOException e) {

    }
  }
}

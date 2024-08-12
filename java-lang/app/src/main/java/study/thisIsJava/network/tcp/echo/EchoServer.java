package study.thisIsJava.network.tcp.echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
  public static ServerSocket serverSocket = null;

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
          System.out.println("[server] is started");
          try (Socket socket = serverSocket.accept();
              DataInputStream in = new DataInputStream(socket.getInputStream());
              DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
              System.out.println("[server] is ready");

              InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
              System.out.println("[server] " + isa.getHostString() + " is Conneted");

              String message = in.readUTF();
              if (message.equals("q")) {
                System.out.println("[server] " + isa.getHostString() + " is Disconneted");
                return;
              }
              System.out.println("[server]receive message : " + message);

              out.writeUTF(message);
              out.flush();
              System.out.println("[server]send message : " + message);


              System.out.println("[server] " + isa.getHostString() + " is Disconneted");
            }
          }
        } catch (IOException e) {
          System.out.println("system message : " + e.getMessage());
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

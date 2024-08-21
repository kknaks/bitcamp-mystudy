package study.thisIsJava.network.ExecutorService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
  public static void main(String[] args) throws InterruptedException {
    try (Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {
      System.out.println("[client] connection is success");

      while (true) {
        System.out.print("input message(quit : q ) : ");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        System.out.println("[client]send message : " + message);
        out.writeUTF(message);
        out.flush();

        if (message.equals("q")) {
          Thread.sleep(1000);
          break;
        }

        String receive = in.readUTF();
        System.out.println("[client]receive message : " + message);
      }

      System.out.println("[client] connection is over");
    } catch (UnknownHostException e) {

    } catch (IOException e2) {

    }
  }
}

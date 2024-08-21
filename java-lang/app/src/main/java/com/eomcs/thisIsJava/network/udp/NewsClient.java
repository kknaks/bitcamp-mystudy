package study.thisIsJava.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class NewsClient {
  public static void main(String[] args) {
    try {
      DatagramSocket datagramSocket = new DatagramSocket();

      Scanner scanner = new Scanner(System.in);
      System.out.print("interest subject : ");
      String data = scanner.nextLine();

      byte[] bytes = data.getBytes("UTF-8");
      DatagramPacket sendPacket =
          new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 8888));
      datagramSocket.send(sendPacket);
      int count = 0;
      while (true) {
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        datagramSocket.receive(receivePacket);

        String news = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        System.out.println(news);

        if (count == 10) {
          break;
        }
        count++;
      }
      datagramSocket.close();
    } catch (Exception e) {

    }
  }
}

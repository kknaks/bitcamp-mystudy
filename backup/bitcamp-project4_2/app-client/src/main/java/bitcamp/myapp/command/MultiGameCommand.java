package bitcamp.myapp.command;

import bitcamp.command.Command;
import bitcamp.context.ApplicationContext;
import bitcamp.util.Prompt;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiGameCommand implements Command {
  ApplicationContext appCtx;
  Socket socket;
  List<Integer> inputNum;

  public MultiGameCommand(ApplicationContext appCtx) {
    this.appCtx = appCtx;
  }

  @Override
  public void execute(String menuName) {
    try {
      socket = new Socket("localhost", 8888);
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("서버연결완료");
      System.out.println("대기중.....");


      while (true) {
        String message = (String) in.readObject();
        System.out.println(message);

        if (message.contains("배열")) {
          String[][] arr = (String[][]) in.readObject();
          for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
              System.out.printf("%s|", arr[i][j]);
            }
            System.out.println();
            for (int j = 0; j < arr.length * 4; j++) {
              System.out.print("-");
            }
            System.out.println();
          }
          inputNum = (List<Integer>) in.readObject();
          System.out.println(inputNum.toString());
        }

        if (message.contains("숫자")) {
          int cell = Prompt.inputInt("숫자를 입력하세요");
          while (inputNum.contains(cell)) {
            cell = Prompt.inputInt("숫자를 다시 입력하세요");
          }
          out.writeObject(cell);
          out.flush();
        }
      }

      //      int[][] arr = (int[][]) in.readObject();
      //      for (int i = 0; i < arr.length; i++) {
      //        for (int j = 0; j < arr[i].length; j++) {
      //          System.out.println(arr[i][j]);
      //        }
      //      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

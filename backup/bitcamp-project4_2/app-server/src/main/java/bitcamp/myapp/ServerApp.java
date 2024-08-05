package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.listener.InitApplicationListener;
import bitcamp.vo.Game;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApp {
  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();
  List<ClientHandler> clients = new ArrayList<>();
  List<Integer> inputNum = new ArrayList<>();
  Game game = new Game();

  // 현재 턴인 플레이어 (0 또는 1을 가짐)
  int currentPlayer = 0;

  public static void main(String[] args) {
    ServerApp app = new ServerApp();
    app.addApplicationListener(new InitApplicationListener());
    app.execute();
  }

  // 애플리케이션 리스너를 리스트에 추가하는 메서드
  private void addApplicationListener(ApplicationListener listener) {
    listeners.add(listener);
  }

  // 애플리케이션 리스너를 리스트에서 제거하는 메서드
  private void removeApplicationListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  void execute() {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버가 시작되었습니다.");

      for (ApplicationListener listener : listeners) {
        listener.onStart(appCtx);
      }

      // 두 명의 클라이언트가 연결될 때까지 대기
      while (clients.size() < 2) {
        Socket clientSocket = serverSocket.accept();
        ClientHandler clientHandler = new ClientHandler(clientSocket, this, clients.size() + 1);
        System.out.println(clients.size() + 1);
        clients.add(clientHandler);
        new Thread(clientHandler).start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    informStart();
    //    sendArr();

  }

  synchronized void informStart() {
    for (ClientHandler client : clients) {
      client.sendMessage("게임 시작");
    }
    broadcastMessage("배열");
    sendNextTurnMessage();
  }

  synchronized void sendNextTurnMessage() {
    System.out.println(currentPlayer);
    clients.get(currentPlayer).sendMessage((currentPlayer + 1) + "의 턴입니다. 숫자는?: ");
  }

  synchronized void handlePlayerInput(int playerNumber, int num) {
    if (playerNumber != currentPlayer + 1) {
      clients.get(playerNumber - 1).sendMessage("상대의 턴 입니다.");
      return;
    }

    broadcastMessage("배열");

    currentPlayer = 1 - currentPlayer;

    if (game.isGameOver()) {
      clients.get(currentPlayer).sendMessage(currentPlayer + " 님이 졌습니다!");
      clients.get(1 - currentPlayer).sendMessage((1 - currentPlayer) + " 님이 이겼습니다!");
    } else {
      sendNextTurnMessage();
    }
  }

  private void broadcastMessage(Object message) {
    for (ClientHandler client : clients) {
      client.sendMessage(message);
    }
  }

  class ClientHandler implements Runnable {
    private Socket socket;
    private ServerApp server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int player;

    public ClientHandler(Socket clientSocket, ServerApp server, int player) {
      this.socket = clientSocket;
      this.server = server;
      this.player = player;
      try {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      try {
        while (true) {
          int cell = (int) in.readObject();
          inputNum.add(cell);
          game.put(cell, player);
          server.handlePlayerInput(player, cell);

        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          in.close();
          out.close();
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    public void sendMessage(Object message) {
      if (!message.equals("배열")) {
        try {
          out.writeObject(message);
          out.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        try {
          out.writeObject(message);
          out.writeObject(game.getArr());
          out.writeObject(inputNum);
          System.out.println(inputNum.toString());
          out.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    public void sendArrary() {
      int[][] arr = new int[3][3];
      try {
        out.writeObject(arr);
        out.flush();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("배열 오류 발생");
      }
    }
  }
}

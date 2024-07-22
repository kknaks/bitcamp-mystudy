package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.HistoryCommand;
import bitcamp.myapp.command.board.*;
import bitcamp.myapp.command.project.*;
import bitcamp.myapp.command.user.*;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  MenuGroup mainMenu = new MenuGroup("메인");
  List<User> userList = new ArrayList<>();
  List<Project> projectList = new ArrayList<>();
  List<Board> boardList = new ArrayList<>();

  public App() {
    loadData();

    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userList)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userList)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userList)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userList)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userList)));
    mainMenu.add(userMenu);


    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardList)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardList)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardList)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardList)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userList);
    projectMenu.add(new MenuItem("등록", new ProjectAddCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectList)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectList)));
    projectMenu.add(new MenuItem("변경", new ProjectUpdateCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectList)));
    mainMenu.add(projectMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));
    mainMenu.add(new MenuItem("명령내역", new HistoryCommand()));

    mainMenu.setExitMenuTitle("종료");
  }


  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    String appTitle = "[프로젝트 관리 시스템]";
    String line = "----------------------------------";

    try {
      mainMenu.execute();
    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    } finally {
      saveData();
    }

    System.out.println("종료합니다.");

    Prompt.close();
  }

  private void loadData() {
    loadUsers();
    loadProjects();
    loadBoards();
    System.out.println("데이터를 로딩 했습니다");

  }

  private void loadUsers() {
    try (Scanner in = new Scanner(new FileReader("user.csv"))) {
      while (true) {
        try {
          String csv = in.nextLine();
          userList.add(User.valueOf(csv));
        } catch (Exception e) {
          break;
        }
      }

      int maxUserNo = 0;
      for (User user : userList) {
        if (user.getNo() > maxUserNo) {
          maxUserNo = user.getNo();
        }
      }
      User.initSeqNo(maxUserNo);
    } catch (IOException e) {
      System.out.println("회원 정보 로딩 중 오류 발생!");
    }
  }

  private void loadProjects() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("project.data"))) {

      projectList = (List<Project>) in.readObject();

      int maxProjectNo = 0;
      for (Project project : projectList) {
        if (project.getNo() > maxProjectNo) {
          maxProjectNo = project.getNo();
        }
      }
      Project.initSeqNo(maxProjectNo);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("프로젝트 정보 로딩 중 오류 발생!");
      projectList = new ArrayList<>();
      //      e.printStackTrace();
    }
  }


  private void loadBoards() {
    try (Scanner in = new Scanner(new FileReader("board.csv"))) {
      while (true) {
        try {
          String csv = in.nextLine();
          boardList.add(Board.valueOf(csv));
        } catch (Exception e) {
          break;
        }
      }
      int maxBoardNo = 0;
      for (Board board : boardList) {
        if (board.getNo() > maxBoardNo) {
          maxBoardNo = board.getNo();
        }
      }
      Board.initSeqNo(maxBoardNo);
    } catch (IOException e) {
      System.out.println("게시판 정보 로딩 중 오류 발생!");
      //      e.printStackTrace();
    }
  }

  private void saveData() {
    saveUsers();
    saveProjects();
    saveBoards();
    System.out.println("데이터를 저장 했습니다");
  }

  private void saveUsers() {
    try (FileWriter out = new FileWriter("user.csv")) {
      for (User user : userList) {
        out.write(user.toCsvString() + '\n');
      }
    } catch (IOException e) {
      System.out.println("회원정보 저장중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveBoards() {
    try (FileWriter out = new FileWriter("board.csv")) {
      for (Board board : boardList) {
        out.write(board.toCsvString() + '\n');
      }
    } catch (IOException e) {
      System.out.println("게시판 저장중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveProjects() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("project.data"))) {
      out.writeObject(projectList);
    } catch (IOException e) {
      System.out.println("프로젝트 저장중 오류 발생!");
      e.printStackTrace();
    }
  }
}

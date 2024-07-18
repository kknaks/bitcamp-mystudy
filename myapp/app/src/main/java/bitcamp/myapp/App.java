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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
  MenuGroup mainMenu = new MenuGroup("메인");
  List<User> userList = new ArrayList<>();
  List<Project> projectList = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();

  public App() {

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
      loadData();
      mainMenu.execute();
    } catch (Exception ex) {
      System.out.println("실행 오류!");
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
    try (FileInputStream in = new FileInputStream("user.data")) {
      int userLength = (in.read() << 8 | in.read());
      int maxUserNo = 0;
      for (int i = 0; i < userLength; i++) {
        int len = (in.read() << 8) | in.read();
        byte[] bytes = new byte[len];
        in.read(bytes);

        User user = User.valueOf(bytes);
        userList.add(user);

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
    try (FileInputStream in = new FileInputStream("project.data")) {
      int projectLength = (in.read() << 8 | in.read());
      int maxProjectNo = 0;
      for (int i = 0; i < projectLength; i++) {
        int len = (in.read() << 8) | in.read();
        byte[] bytes = new byte[len];
        in.read(bytes);

        Project project = Project.valueOf(bytes);
        projectList.add(project);

        if (project.getNo() > maxProjectNo) {
          maxProjectNo = project.getNo();
        }
      }
      Board.initSeqNo(maxProjectNo);
    } catch (IOException e) {
      System.out.println("프로젝트 정보 로딩 중 오류 발생!");
    }
  }

  private void loadBoards() {
    try (FileInputStream in = new FileInputStream("board.data")) {
      int boardLength = (in.read() << 8 | in.read());
      int maxBoardNo = 0;
      for (int i = 0; i < boardLength; i++) {
        int len = (in.read() << 8) | in.read();
        byte[] bytes = new byte[len];
        in.read(bytes);

        Board board = Board.valueOf(bytes);
        boardList.add(board);

        if (board.getNo() > maxBoardNo) {
          maxBoardNo = board.getNo();
        }
      }
      Board.initSeqNo(maxBoardNo);
    } catch (IOException e) {
      System.out.println("게시판 정보 로딩 중 오류 발생!");
    }
  }

  private void saveData() {
    saveUsers();
    saveProjects();
    saveBoards();
    System.out.println("데이터를 저장 했습니다");
  }

  private void saveUsers() {
    try (FileOutputStream out = new FileOutputStream("user.data")) {

      out.write(userList.size() >> 8);
      out.write(userList.size());

      for (User user : userList) {
        byte[] bytes = user.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }
    } catch (IOException ex) {
      System.out.println("회원정보 저장중 오류 발생!");
    }
    // finally 대신에 try()으로 대체
    //    finally {
    //      try {
    //        out.close();
    //      } catch (IOException e) {
    //        //무시
    //      }
    //    }
  }

  private void saveBoards() {
    try (FileOutputStream out = new FileOutputStream("board.data")) {
      out.write(boardList.size() >> 8);
      out.write(boardList.size());

      for (Board board : boardList) {
        byte[] bytes = board.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }
    } catch (IOException e) {
      System.out.println("게시판 저장중 오류 발생!");
    }
  }

  private void saveProjects() {
    try (FileOutputStream out = new FileOutputStream("project.data")) {
      out.write(projectList.size() >> 8);
      out.write(projectList.size());

      for (Project project : projectList) {
        byte[] bytes = project.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }
    } catch (IOException e) {
      System.out.println("프로젝트 저장중 오류 발생!");
    }
  }
}

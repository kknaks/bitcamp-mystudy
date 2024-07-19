package bitcamp.myapp;

import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.HistoryCommand;
import bitcamp.myapp.command.board.*;
import bitcamp.myapp.command.project.*;
import bitcamp.myapp.command.user.*;
import bitcamp.myapp.menu.MenuGroup;
import bitcamp.myapp.menu.MenuItem;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
  private MenuGroup mainMenu = new MenuGroup("메인");
  private List<User> userList = new ArrayList<>();
  private List<Project> projectList = new LinkedList<>();
  private List<Board> boardList = new LinkedList<>();

  public App() {
    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userList)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userList)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userList)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userList)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userList)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userList);
    projectMenu.add(new MenuItem("등록", new ProjectAddCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectList)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectList)));
    projectMenu.add(new MenuItem("변경", new ProjectUpdateCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectList)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardList)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardList)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardList)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardList)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardList)));
    mainMenu.add(boardMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));
    mainMenu.add(new MenuItem("명령내역", new HistoryCommand()));

    mainMenu.setExitMenuTitle("종료");
  }


  public static void main(String[] args) {
    new App().execute();
  }

  private void loadData() {
    loadUser();
    loadProject();
    loadBoard();
  }

  private void loadUser() {
    try (FileInputStream in = new FileInputStream("user.data")) {
      int userLength = in.read() << 8 | in.read();
      int maxUserNum = 0;
      for (int i = 0; i < userLength; i++) {
        int len = (in.read() << 8) | in.read();
        byte[] bytes = new byte[len];
        in.read(bytes);

        User user = User.valueOf(bytes);
        userList.add(user);

        maxUserNum = Math.max(maxUserNum, user.getNo());
      }
      User.initSeqNo(maxUserNum);
    } catch (IOException e) {
      System.out.println("회원 정보 로딩 중 오류 발생" + e.getMessage());
    }
  }

  private void loadProject() {

  }

  private void loadBoard() {

  }

  private void saveData() {
    saveUser();
    saveProject();
    saveBoard();
  }

  private void saveUser() {
    try (FileOutputStream out = new FileOutputStream("user.data")) {
      int userLength = userList.size();
      out.write(userLength >> 8);
      out.write(userLength);

      for (User user : userList) {
        byte[] bytes = user.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }
    } catch (IOException e) {
      System.out.println("회원 정보 저장 중 오류 발생" + e.getMessage());
    }
  }

  private void saveProject() {
  }

  private void saveBoard() {
  }


  void execute() {
    try {
      loadData();
      mainMenu.execute();
    } catch (NumberFormatException ex) {
      System.out.println("오류발생!.");
    } finally {
      saveData();
    }

    System.out.println("종료합니다.");
    Prompt.close();
  }
}


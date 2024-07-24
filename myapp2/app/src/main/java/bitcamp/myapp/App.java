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
import bitcamp.myapp.vo.InitSeqNo;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {


  MenuGroup mainMenu = new MenuGroup("메인");

  List<User> userList = new ArrayList<>();
  List<Project> projectList = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();

  public App() {

    loadData();

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
    loadJson(userList, "user.json", User.class);
    loadJson(projectList, "porject.json", Project.class);
    loadJson(boardList, "board.json", Board.class);
    System.out.println("데이터를 로딩 했습니다.");
  }

  private <E> void loadJson(List<E> list, String filename, Class<E> elementType) {
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      StringBuilder strBuilder = new StringBuilder();
      String line;
      int maxSeq = 0;
      while ((line = in.readLine()) != null) {
        strBuilder.append(line);
      }

      list.addAll((List<E>) new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
          .fromJson(strBuilder.toString(), TypeToken.getParameterized(List.class, elementType)));
      for (Class<?> type : elementType.getInterfaces()) {
        if (type.equals(InitSeqNo.class)) {
          intintSeqNo(list, elementType);
        }
      }

    } catch (Exception e) {
      System.out.printf("%s 정보 로딩 중 오류 발생!\n", filename);
      e.printStackTrace();
    }
  }

  private <E> void intintSeqNo(List<E> list, Class<E> elemnetType) throws Exception {
    int maxSeqNo = 0;
    for (Object element : list) {
      InitSeqNo seqNo = (InitSeqNo) element;
      maxSeqNo = Math.max(seqNo.getNo(), maxSeqNo);
    }
    Method method = elemnetType.getMethod("initSeqNo", int.class);
    method.invoke(null, maxSeqNo);
  }

  private void saveData() {
    saveJson(userList, "user.json");
    saveJson(projectList, "porject.json");
    saveJson(boardList, "board.json");
    System.out.println("데이터를 저장 했습니다.");
  }

  private <E> void saveJson(List<E> list, String filename) {
    try (FileWriter out = new FileWriter(filename)) {
      out.write(new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create().toJson(list));
    } catch (IOException e) {
      System.out.printf("%s 저장 중 오류발생!", filename);
    }
  }
}

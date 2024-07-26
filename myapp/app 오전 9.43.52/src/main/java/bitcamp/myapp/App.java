package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.HistoryCommand;
import bitcamp.myapp.command.board.BoardAddCommand;
import bitcamp.myapp.command.board.BoardDeleteCommand;
import bitcamp.myapp.command.board.BoardListCommand;
import bitcamp.myapp.command.board.BoardUpdateCommand;
import bitcamp.myapp.command.board.BoardViewCommand;
import bitcamp.myapp.command.project.ProjectAddCommand;
import bitcamp.myapp.command.project.ProjectDeleteCommand;
import bitcamp.myapp.command.project.ProjectListCommand;
import bitcamp.myapp.command.project.ProjectMemberHandler;
import bitcamp.myapp.command.project.ProjectUpdateCommand;
import bitcamp.myapp.command.project.ProjectViewCommand;
import bitcamp.myapp.command.user.UserAddCommand;
import bitcamp.myapp.command.user.UserDeleteCommand;
import bitcamp.myapp.command.user.UserListCommand;
import bitcamp.myapp.command.user.UserUpdateCommand;
import bitcamp.myapp.command.user.UserViewCommand;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MapBoardDao;
import bitcamp.myapp.dao.MapUserDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {


  MenuGroup mainMenu = new MenuGroup("메인");


  Map<Integer, Board> boardMap = new HashMap<>();
  List<Integer> boardNoList = new ArrayList<>();

  Map<Integer, Project> projectMap = new HashMap<>();
  List<Integer> projectNoList = new ArrayList<>();

  UserDao userDao;
  BoardDao boardDao;

  public App() {

    //loadData();

    userDao = new MapUserDao("data.xlsx");
    boardDao = new MapBoardDao("data.xlsx");

    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userDao)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userDao)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userDao)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userDao)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userDao)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(null);
    projectMenu.add(
        new MenuItem("등록", new ProjectAddCommand(projectMap, projectNoList, memberHandler)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectMap, projectNoList)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectMap)));
    projectMenu.add(new MenuItem("변경", new ProjectUpdateCommand(projectMap, memberHandler)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectMap, projectNoList)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardDao)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardDao)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardDao)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardDao)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardDao)));
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
      //saveData();
      try {
        ((MapUserDao) userDao).save();
      } catch (Exception e) {
        System.out.println("회원 데이터 저장 중 오류 발생!");
        e.printStackTrace();
        System.out.println();
      }

      try {
        ((MapBoardDao) boardDao).save();
      } catch (Exception e) {
        System.out.println("게시글 데이터 저장 중 오류 발생!");
        e.printStackTrace();
        System.out.println();
      }
    }

    System.out.println("종료합니다.");

    Prompt.close();
  }

  private void loadData() {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx");

      loadProjects(workbook);

      System.out.println("데이터를 로딩 했습니다.");

    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void loadProjects(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.getSheet("projects");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);

      try {
        Project project = new Project();
        project.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
        project.setTitle(row.getCell(1).getStringCellValue());
        project.setDescription(row.getCell(2).getStringCellValue());
        project.setStartDate(row.getCell(3).getStringCellValue());
        project.setEndDate(row.getCell(4).getStringCellValue());

        String[] members = row.getCell(5).getStringCellValue().split(",");
        for (String memberNo : members) {
          User member = null; //userMap.get(Integer.valueOf(memberNo));
          if (member != null) {
            project.getMembers().add(member);
          }
        }
        projectMap.put(project.getNo(), project);
        projectNoList.add(project.getNo());

      } catch (Exception e) {
        System.out.printf("%s 번 프로젝트의 데이터 형식이 맞지 않습니다.\n", row.getCell(0).getStringCellValue());
      }
    }

    Project.initSeqNo(projectNoList.getLast());
  }

  private void saveData() {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();

      saveProjects(workbook);

      try (FileOutputStream out = new FileOutputStream("data.xlsx")) {
        workbook.write(out);
      }
      System.out.println("데이터를 저장 했습니다.");

    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }


  private void saveProjects(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("projects");

    // 셀 이름 출력
    String[] cellHeaders = {"no", "title", "description", "start_date", "end_date", "members"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < cellHeaders.length; i++) {
      headerRow.createCell(i).setCellValue(cellHeaders[i]);
    }

    // 데이터 저장
    int rowNo = 1;
    for (Integer projectNo : projectNoList) {
      Project project = projectMap.get(projectNo);
      Row dataRow = sheet.createRow(rowNo++);
      dataRow.createCell(0).setCellValue(String.valueOf(project.getNo()));
      dataRow.createCell(1).setCellValue(project.getTitle());
      dataRow.createCell(2).setCellValue(project.getDescription());
      dataRow.createCell(3).setCellValue(project.getStartDate());
      dataRow.createCell(4).setCellValue(project.getEndDate());

      StringBuilder strBuilder = new StringBuilder();
      for (User member : project.getMembers()) {
        if (strBuilder.length() > 0) {
          strBuilder.append(",");
        }
        strBuilder.append(member.getNo());
      }
      dataRow.createCell(5).setCellValue(strBuilder.toString());
    }
  }

}

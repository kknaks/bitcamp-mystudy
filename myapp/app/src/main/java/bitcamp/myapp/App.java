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
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.SequenceNo;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    try {
      XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx");

      loadUsers(workbook);
      loadBoards(workbook);
      loadProjects(workbook);

      System.out.println("데이터를 로딩 했습니다.");

    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void loadUsers(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.getSheet("users");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);
      try {
        User user = new User();
        user.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
        user.setName(row.getCell(1).getStringCellValue());
        user.setEmail(row.getCell(2).getStringCellValue());
        user.setPassword(row.getCell(3).getStringCellValue());
        user.setTel(row.getCell(4).getStringCellValue());
        userList.add(user);

      } catch (Exception e) {
        System.out.printf("%s 번 회원의 데이터 형식이 맞지 않습니다.\n", row.getCell(0).getStringCellValue());
      }
    }

    try {
      initSeqNo(userList, User.class);
    } catch (Exception e) {
      System.out.println("회원 일련 번호 초기화 오류!");
    }

  }

  private void loadBoards(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.getSheet("boards");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);

      try {
        Board board = new Board();
        board.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
        board.setTitle(row.getCell(1).getStringCellValue());
        board.setContent(row.getCell(2).getStringCellValue());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        board.setCreatedDate(formatter.parse(row.getCell(3).getStringCellValue()));

        board.setViewCount(Integer.parseInt(row.getCell(4).getStringCellValue()));

        boardList.add(board);

      } catch (Exception e) {
        System.out.printf("%s 번 게시글의 데이터 형식이 맞지 않습니다.\n", row.getCell(0).getStringCellValue());
      }
    }

    try {
      initSeqNo(boardList, Board.class);
    } catch (Exception e) {
      System.out.println("게시글 일련 번호 초기화 오류!");
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
          User member = findUserByNo(Integer.parseInt(memberNo));
          if (member != null) {
            project.getMembers().add(member);
          }
        }
        projectList.add(project);

      } catch (Exception e) {
        System.out.printf("%s 번 프로젝트의 데이터 형식이 맞지 않습니다.\n", row.getCell(0).getStringCellValue());
      }
    }

    try {
      initSeqNo(projectList, Project.class);
    } catch (Exception e) {
      System.out.println("프로젝트 일련 번호 초기화 오류!");
    }
  }

  private User findUserByNo(int no) {
    for (User user : userList) {
      if (user.getNo() == no) {
        return user;
      }
    }
    return null;
  }

  private <E> void initSeqNo(List<E> list, Class<E> elementType) throws Exception {
    int maxSeqNo = 0;
    for (Object element : list) {
      SequenceNo seqObj = (SequenceNo) element;
      if (seqObj.getNo() > maxSeqNo) {
        maxSeqNo = seqObj.getNo();
      }
    }

    Method method = elementType.getMethod("initSeqNo", int.class);
    method.invoke(null, maxSeqNo);
    // 위 코드는 다음과 같다.
    // 예) User.initSeqNo(maxSeqNo);
  }

  private void saveData() {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();

      saveUsers(workbook);
      saveBoards(workbook);
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

  private void saveUsers(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("users");

    // 셀 이름 출력
    String[] cellHeaders = {"no", "name", "email", "password", "tel"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < cellHeaders.length; i++) {
      headerRow.createCell(i).setCellValue(cellHeaders[i]);
    }

    // 데이터 저장
    for (int i = 0; i < userList.size(); i++) {
      User user = userList.get(i);
      Row dataRow = sheet.createRow(i + 1);
      dataRow.createCell(0).setCellValue(String.valueOf(user.getNo()));
      dataRow.createCell(1).setCellValue(user.getName());
      dataRow.createCell(2).setCellValue(user.getEmail());
      dataRow.createCell(3).setCellValue(user.getPassword());
      dataRow.createCell(4).setCellValue(user.getTel());
    }
  }

  private void saveBoards(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("boards");

    // 셀 이름 출력
    String[] cellHeaders = {"no", "title", "content", "created_date", "view_count"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < cellHeaders.length; i++) {
      headerRow.createCell(i).setCellValue(cellHeaders[i]);
    }

    // 데이터 저장
    for (int i = 0; i < boardList.size(); i++) {
      Board board = boardList.get(i);
      Row dataRow = sheet.createRow(i + 1);
      dataRow.createCell(0).setCellValue(String.valueOf(board.getNo()));
      dataRow.createCell(1).setCellValue(board.getTitle());
      dataRow.createCell(2).setCellValue(board.getContent());

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      dataRow.createCell(3).setCellValue(formatter.format(board.getCreatedDate()));

      dataRow.createCell(4).setCellValue(String.valueOf(board.getViewCount()));
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
    for (int i = 0; i < projectList.size(); i++) {
      Project project = projectList.get(i);
      Row dataRow = sheet.createRow(i + 1);
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

package bitcamp.myapp2;

public class App {
  static java.util.Scanner keyboardScanner = new java.util.Scanner(System.in);
  static String[] mainMenus = new String[] {"회원", "팀", "프로젝트", "게시판", "도움말", "종료"};
  static String[][] subMenus =
      new String[][] {{"등록", "목록", "조회", "변경", "삭제"}, {"등록", "목록", "조회", "변경", "삭제"},
          {"등록", "목록", "조회", "변경", "삭제"}, {"등록", "목록", "조회", "변경", "삭제"}};

  static final int MAX_SIZE = 10;
  static String[] teams = new String[MAX_SIZE];
  static String[][] teamMember = new String[MAX_SIZE][MAX_SIZE];

  static int teamLength = 0;
  static int teamMemberLength = 0;

  public static void main(String[] args) {
    String command;
    printMainMenu();
    while (true) {
      try {
        command = prompt.input("메인> ");
        if (command.equals("menu")) {
          printMainMenu();
          continue;
        }
        int menuNo = Integer.parseInt(command);
        String menuTitle = getMenuTitle(menuNo, mainMenus);
        if (menuTitle == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
        } else if (menuTitle.equals("종료")) {
          break;
        } else {
          if (menuNo >= 1 && menuNo <= 4) {
            processSubMenu(menuTitle, subMenus[menuNo - 1]);
          } else {
            System.out.println(mainMenus[menuNo - 1]);
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
    System.out.println("종료합니다.");
    keyboardScanner.close();
  }

  static void printMainMenu() {
    String boldAnsi = "\033[1m";
    String redAnsi = "\033[31m";
    String resetAnsi = "\033[0m";
    String appTitle = "[팀 프로젝트 관리 시스템]";
    String line = "----------------------------------";
    System.out.println(boldAnsi + line + resetAnsi);
    System.out.println(boldAnsi + appTitle + resetAnsi);
    for (int i = 0; i < mainMenus.length; i++) {
      if (mainMenus[i].equals("종료")) {
        System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), mainMenus[i], resetAnsi);
      } else {
        System.out.printf("%d. %s\n", (i + 1), mainMenus[i]);
      }
    }
    System.out.println(boldAnsi + line + resetAnsi);
  }

  static void printSubMenu(String menuTitle, String[] menus) {
    System.out.printf("[%s]\n", menuTitle);
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("%d. %s\n", i + 1, menus[i]);
    }
    System.out.println("9. 이전");
  }

  static boolean isValidateMenu(int menuNo, String[] menus) {
    return menuNo >= 1 && menuNo <= menus.length;
  }

  static String getMenuTitle(int menuNo, String[] menus) {
    return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
  }

  static void processSubMenu(String menuTitle, String[] menus) {
    printSubMenu(menuTitle, menus);
    while (true) {
      String command = prompt.input(String.format("메인/%s> ", menuTitle));
      if (command.equals("menu")) {
        printSubMenu(menuTitle, menus);
        continue;
      } else if (command.equals("9")) {
        break;
      }
      try {
        int subMenuNo = Integer.parseInt(command);
        String subMenuTitle = getMenuTitle(subMenuNo, menus);
        if (subMenuTitle == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
        } else {
          switch (menuTitle) {
            case "회원":
              UserCommand.excuteUserCommand(subMenuTitle);
              break;
            case "팀":
              excuteTeamCommand(subMenuTitle);
              break;
            case "프로젝트":
              excuteProjectCommand(subMenuTitle);
              break;
            case "게시판":
              excuteBoardCommand(subMenuTitle);
              break;
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  static void excuteTeamCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        addTeam();
        break;
      case "목록":
        listTeam();
        break;
      case "조회":
        viewTeam();
        break;
      case "변경":
        updateTeam();
        break;
      case "삭제":
        deleteTeam();
        break;
    }
  }

  static void addTeam(){
    teams[teamLength] = prompt.input("팀명? ");
    int teamMemberNo = Integer.parseInt(prompt.input("추가할 팀원 번호?(종료 : 0) "));
    if(teamMemberNo < 1 || teamMemberNo > UserCommand.userLength){
      System.out.println("없는 팀원입니다.");
      return;
    }

    if (){
      System.out.printf("'%s'은 현재팀원입니다.",UserCommand.users[teamMemberNo - 1]);}
    }else{teamMember[teamLength][teamMemberNo] = UserCommand.users[teamMemberNo - 1];
    System.out.printf("'%s'을 추가했습니다.");}
  };
  static void listTeam(){};
  static void viewTeam(){};
  static void updateTeam(){};
  static void deleteTeam(){};



  static void excuteProjectCommand(String command) {
  }

  static void excuteBoardCommand(String command) {
  }


}




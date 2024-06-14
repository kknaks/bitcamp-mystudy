package bitcamp.myapp;

public class UserCommand {
  static final int MAX_SIZE = 100;
  static String[] name = new String[MAX_SIZE];
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static String[] tel = new String[MAX_SIZE];
  static int memberLength = 0;

  static void excuteUserCommand(String command) {
    System.out.printf("[%s]\n", command);
    int userNo = 0;
    switch (command) {
      case "등록":
        addUser();
        break;
      case "목록":
        viewUser();
        break;
      case "조회":
        listUser();
        break;
      case "변경":
        updateUser();
        break;
      case "삭제":
        deleteUser();
        break;
    }
  }

  static void addUser() {
    name[memberLength] = Prompt.prompt("이름?");
    email[memberLength] = Prompt.prompt("이메일?");
    password[memberLength] = Prompt.prompt("암호?");
    tel[memberLength] = Prompt.prompt("연락처?");
    memberLength++;
  }

  static void listUser() {
    int userNo = Integer.parseInt(Prompt.prompt("회원번호"));
    if (userNo < 1 || userNo > memberLength) {
      System.out.println("없는 회원입니다.");
      return;
    }
    System.out.printf("%s\n", name[userNo - 1]);
    System.out.printf("%s\n", email[userNo - 1]);
    System.out.printf("%s\n", tel[userNo - 1]);
  }

  static void viewUser() {
    System.out.println("번호 이름 이메일");
    for (int i = 0; i < memberLength; i++) {
      System.out.printf("%d %s %s\n", i + 1, name[i], email[i]);
    }
  }

  static void updateUser() {
    int userNo = Integer.parseInt(Prompt.prompt("회원번호"));
    if (userNo < 1 || userNo > memberLength) {
      System.out.println("없는 회원입니다.");
      return;
    }
    name[userNo - 1] = Prompt.prompt(String.format("이름(%s)", name[userNo - 1]));
    email[userNo - 1] = Prompt.prompt(String.format("이름(%s)", email[userNo - 1]));
    password[userNo - 1] = Prompt.prompt("암호?");
    tel[userNo - 1] = Prompt.prompt(String.format("연락처(%s)", tel[userNo - 1]));
    System.out.println("변경했습니다.");
  }

  static void deleteUser() {
    int userNo = Integer.parseInt(Prompt.prompt("회원번호"));
    if (userNo < 1 || userNo > memberLength) {
      System.out.println("없는 회원입니다.");
      return;
    }
    for (int i = userNo; i < memberLength; i++) {
      name[i - 1] = name[i];
      email[i - 1] = email[i];
      password[i - 1] = password[i];
      tel[i - 1] = tel[i];
    }
    memberLength--;
    name[memberLength] = null;
    email[memberLength] = null;
    password[memberLength] = null;
    tel[memberLength] = null;
    System.out.println("삭제하였습니다.");
  }
}

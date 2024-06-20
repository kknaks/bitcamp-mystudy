package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.User;

public class UserCommand {

  public static void executeUserCommand(String command) {
    System.out.printf("[%s]\n", command);
    int userNo = 0;
    switch (command) {
      case "등록":
        addUser();
        break;
      case "목록":
        listUser();
        break;
      case "조회":
        viewUser();
        break;
      case "변경":
        updateUser();
        break;
      case "삭제":
        deleteUser();
        break;
    }
  }

  private static void addUser() {
    User user = new User();
    user.setName(Prompt.input("이름?"));
    user.setEmail(Prompt.input("이메일?"));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처?"));
    user.setNo(User.getNextSeqNo());
    UserList.add(user);
  }

  private static void listUser() {
    System.out.println("번호 이름 이메일");
    for (User user : UserList.toArray()) {
      System.out.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
    }
  }

  private static void viewUser() {
    int userNo = Prompt.inputInt("회원번호");
    User user = UserList.findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("연락처: %s\n", user.getTel());
  }

  private static void updateUser() {
    int userNo = Prompt.inputInt("회원번호");
    User user = UserList.findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    user.setName(Prompt.input("이름(%s)", user.getName()));
    user.setEmail(Prompt.input("이메일(%s)", user.getEmail()));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처(%s)", user.getTel()));
    System.out.println("변경했습니다.");
  }

  private static void deleteUser() {
    int userNo = Prompt.inputInt("회원번호");
    User deletedUser = UserList.delete(userNo);
    if (deletedUser != null) {
      System.out.printf("'%s'회원을 삭제했습니다.\n", deletedUser.getName());
    } else {
      System.out.println("없는 회원입니다.");
    }
  }
}

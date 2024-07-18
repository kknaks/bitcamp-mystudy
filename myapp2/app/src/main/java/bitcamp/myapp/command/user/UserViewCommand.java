package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.User;

import java.util.List;

public class UserViewCommand implements Command {

  private List<User> userList;

  public UserViewCommand(List<User> list) {
    this.userList = list;
  }

  @Override
  public void execute(String menuName) {
    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User user = userList.get(index);

    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("연락처: %s\n", user.getTel());
  }
}

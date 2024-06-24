package bitcamp.myapp2.command;

import bitcamp.myapp2.vo.User;

public class UserList extends ArrayList {

  public User findByNo(int userNo) {
    for (int i = 0; i < size(); i++) {
      User user = (User) get(i);
      if (user.getNo() == userNo) {
        return user;
      }
    }
    return null;
  }
}

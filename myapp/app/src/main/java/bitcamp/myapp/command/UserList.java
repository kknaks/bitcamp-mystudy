package bitcamp.myapp.command;

import bitcamp.myapp.vo.User;

public class UserList {
  private static final int MAX_SIZE = 100;
  private static User[] users = new User[MAX_SIZE];
  private static int userLength = 0;

  public static void add(User user) {
    users[userLength++] = user;
  }

  public static User delete(int userNo) {
    User deleteuser = findByNo(userNo);
    if (deleteuser == null) {
      return null;
    }
    int index = indexOf(deleteuser);
    for (int i = index + 1; i < userLength; i++) {
      users[i - 1] = users[i];
    }
    users[--userLength] = null;
    return deleteuser;
  }

  public static User[] toArray() {
    User[] arr = new User[userLength];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = users[i];
    }
    return arr;
  }

  public static User findByNo(int userNo) {
    for (int i = 0; i < userLength; i++) {
      User user = users[i];
      if (user.getNo() == userNo) {
        return user;
      }
    }
    return null;
  }

  public static int indexOf(User user) {
    for (int i = 0; i < userLength; i++) {
      if (users[i] == user) {
        return i;
      }
    }
    return -1;
  }
}

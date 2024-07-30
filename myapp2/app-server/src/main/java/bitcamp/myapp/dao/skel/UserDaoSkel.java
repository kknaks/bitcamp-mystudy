package bitcamp.myapp.dao.skel;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class UserDaoSkel {
  private UserDao userDao;

  public UserDaoSkel(UserDao userDao) {
    this.userDao = userDao;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    String command = in.readUTF();
    User user = null;
    int no = 0;
    switch (command) {
      case "insert":
        user = (User) in.readObject();
        userDao.insert(user);
        out.writeUTF("success");
        break;
      case "list":
        List<User> list = (List<User>) userDao.list();
        out.writeUTF("success");
        out.writeObject(list);
        break;
      case "get":
        no = in.readInt();
        user = userDao.findBy(no);
        if (user != null) {
          out.writeUTF("success");
          out.writeObject(user);
        } else {
          out.writeUTF("fail");
        }
        break;
      case "update":
        user = (User) in.readObject();
        if (userDao.update(user)) {
          out.writeUTF("success");
        } else {
          out.writeUTF("fail");
        }
      case "delete":
        no = in.readInt();
        if (userDao.delete(no)) {
          out.writeUTF("success");
        } else {
          out.writeUTF("fail");
        }
        break;
      default:
        out.writeUTF("error");
    }
  }
}

package bitcamp.myapp.dao.stub;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class UserDaoStub implements UserDao {
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private String dataName;

  public UserDaoStub(ObjectOutputStream out, ObjectInputStream in, String dataName) {
    this.out = out;
    this.in = in;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(User user) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    out.writeObject(user);
    out.flush();
    return in.readUTF().equals("success");
  }

  @Override
  public List<User> list() throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("list");
    out.flush();
    if (in.readUTF().equals("success")) {
      return (List<User>) in.readObject();
    }
    return null;
  }

  @Override
  public User findBy(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("get");
    out.writeInt(no);
    if (in.readUTF().equals("success")) {
      return (User) in.readObject();
    }
    return null;
  }

  @Override
  public boolean update(User user) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("update");
    out.writeObject(user);
    if (in.readUTF().equals("success")) {
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("delete");
    out.writeInt(no);
    if (in.readUTF().equals("success")) {
      return true;
    }
    return false;
  }
}

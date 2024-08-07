package bitcamp.myapp.dao.stub;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static bitcamp.net.ResponseStatus.SUCCESS;

public class ProjectDaoStub implements ProjectDao {

  private ObjectInputStream in;
  private ObjectOutputStream out;
  private String dataName;
  private UserDao userDao;

  public ProjectDaoStub(ObjectOutputStream out, ObjectInputStream in, String dataName)
      throws Exception {
    this.out = out;
    this.in = in;
    this.dataName = dataName;
    //    this.userDao = userDao;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    out.writeObject(project);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }

    return false;
  }

  @Override
  public List<Project> list() throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("list");
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (List<Project>) in.readObject();
    }
    return null;
  }

  @Override
  public Project findBy(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("get");
    out.writeInt(no);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (Project) in.readObject();
    }
    return null;
  }

  @Override
  public boolean update(Project project) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("update");
    out.writeObject(project);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("delete");
    out.writeInt(no);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }
    return false;
  }
}

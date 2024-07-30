package bitcamp.myapp.dao.stub;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ProjectDaoStub implements ProjectDao {
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private String dataName;

  public ProjectDaoStub(ObjectOutputStream out, ObjectInputStream in, String dataName) {
    this.out = out;
    this.in = in;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    return false;
  }

  @Override
  public List<Project> list() throws Exception {
    return List.of();
  }

  @Override
  public Project findBy(int no) throws Exception {
    return null;
  }

  @Override
  public boolean update(Project project) throws Exception {
    return false;
  }

  @Override
  public boolean delete(int no) throws Exception {
    return false;
  }
}

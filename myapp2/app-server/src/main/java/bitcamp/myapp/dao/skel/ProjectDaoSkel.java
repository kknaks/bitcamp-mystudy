package bitcamp.myapp.dao.skel;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ProjectDaoSkel {
  private ProjectDao projectDao;

  public ProjectDaoSkel(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    String command = in.readUTF();
    Project project = null;
    int no = 0;
    switch (command) {
      case "insert":
        project = (Project) in.readObject();
        projectDao.insert(project);
        out.writeUTF("success");
        break;
      case "list":
        List<Project> list = projectDao.list();
        out.writeUTF("success");
        out.writeObject(list);
        break;
      case "get":
        no = in.readInt();
        project = projectDao.findBy(no);
        if (project != null) {
          out.writeUTF("success");
          out.writeObject(project);
        } else {
          out.writeUTF("fail");
        }
        break;
      case "update":
        project = (Project) in.readObject();
        if (projectDao.update(project)) {
          out.writeUTF("success");
        } else {
          out.writeUTF("fail");
        }
        break;
      case "delete":
        no = in.readInt();
        if (projectDao.delete(no)) {
          out.writeUTF("success");
        } else {
          out.writeUTF("fail");
        }
        break;
      default:
        out.writeUTF("error");
        out.writeUTF("무효한 명령입니다.");
    }

    out.flush();
  }
}

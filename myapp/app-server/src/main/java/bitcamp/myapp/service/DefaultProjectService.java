package bitcamp.myapp.service;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DefaultProjectService implements ProjectService {

  private ProjectDao projectDao;

  public DefaultProjectService(ProjectDao projectDao, SqlSessionFactory sqlSessionFactory) {
    this.projectDao = projectDao;
  }

  @Transactional
  @Override
  public void add(Project project) throws Exception {
    projectDao.insert(project);

    if (project.getMembers() != null && project.getMembers().size() > 0) {
      projectDao.insertMembers(project.getNo(), project.getMembers());
    }

  }

  @Override
  public List<Project> list() throws Exception {
    return projectDao.list();
  }

  @Override
  public Project get(int projectNo) throws Exception {
    return projectDao.findBy(projectNo);
  }

  @Transactional
  @Override
  public boolean update(Project project) throws Exception {
    if (!projectDao.update(project)) {
      return false;
    }

    projectDao.deleteMembers(project.getNo());
    if (project.getMembers() != null && project.getMembers().size() > 0) {
      projectDao.insertMembers(project.getNo(), project.getMembers());
    }
    return true;

  }

  @Transactional
  @Override
  public boolean delete(int projectNo) throws Exception {
    projectDao.deleteMembers(projectNo);
    if (!projectDao.delete(projectNo)) {
      return false;
    }
    return true;
  }
}

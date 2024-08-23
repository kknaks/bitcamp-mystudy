package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import bitcamp.net.Prompt;

public class ProjectAddCommand implements Command {

  private ProjectDao projectDao;
  private ProjectMemberHandler memberHandler;
  private SqlSessionFactoryProxy sqlSessionFactoryProxy;

  public ProjectAddCommand(ProjectDao projectDao, ProjectMemberHandler memberHandler,
      SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.projectDao = projectDao;
    this.memberHandler = memberHandler;
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);

      Project project = new Project();
      project.setTitle(prompt.input("프로젝트명?"));
      project.setDescription(prompt.input("설명?"));
      project.setStartDate(prompt.inputDate("시작일(예 : 2024-01-24)?"));
      project.setEndDate(prompt.inputDate("종료일(예 : 2024-01-24)?"));

      prompt.println("팀원:");
      memberHandler.addMembers(project, prompt);


      projectDao.insert(project);
      Thread.sleep(30000);
      if (project.getMembers() != null && !project.getMembers().isEmpty()) {
        projectDao.insertMembers(project.getNo(), project.getMembers());
        //        throw new Exception("그냥오류 발생");
      }
      sqlSessionFactoryProxy.openSession(false).commit();

      prompt.println("등록했습니다.");

    } catch (Exception e) {
      sqlSessionFactoryProxy.openSession(false).rollback();
      prompt.println("등록 중 오류 발생!");
      e.printStackTrace();
    }
  }
}


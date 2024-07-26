package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.util.List;
import java.util.Map;

public class ProjectAddCommand implements Command {

  private Map<Integer, Project> projectMap;
  private List<Integer> projectNoList;
  private ProjectMemberHandler memberHandler;

  public ProjectAddCommand(Map<Integer, Project> projectMap, List<Integer> projectNoList,
      ProjectMemberHandler memberHandler) {
    this.projectMap = projectMap;
    this.projectNoList = projectNoList;
    this.memberHandler = memberHandler;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    Project project = new Project();
    project.setTitle(Prompt.input("프로젝트명?"));
    project.setDescription(Prompt.input("설명?"));
    project.setStartDate(Prompt.input("시작일?"));
    project.setEndDate(Prompt.input("종료일?"));

    System.out.println("팀원:");
    memberHandler.addMembers(project);

    project.setNo(Project.getNextSeqNo());

    projectMap.put(project.getNo(), project);
    projectNoList.add(project.getNo());

    System.out.println("등록했습니다.");
  }
}

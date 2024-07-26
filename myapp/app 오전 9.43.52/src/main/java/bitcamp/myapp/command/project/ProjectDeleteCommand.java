package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.util.List;
import java.util.Map;

public class ProjectDeleteCommand implements Command {

  private Map<Integer, Project> projectMap;
  private List<Integer> projectNoList;

  public ProjectDeleteCommand(Map<Integer, Project> projectMap, List<Integer> projectNoList) {
    this.projectMap = projectMap;
    this.projectNoList = projectNoList;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);
    int projectNo = Prompt.inputInt("프로젝트 번호?");

    Project deletedProject = projectMap.remove(projectNo);
    if (deletedProject == null) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }

    projectNoList.remove(Integer.valueOf(projectNo));
    System.out.printf("%d번 프로젝트를 삭제 했습니다.\n", deletedProject.getNo());
  }
}

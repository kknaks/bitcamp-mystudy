package bitcamp.myapp2.command;

import bitcamp.myapp2.util.Prompt;
import bitcamp.myapp2.vo.Project;
import bitcamp.myapp2.vo.User;

public class ProjectCommand {
  private static final int MAX_SIZE = 10;
  private static final Project[] projects = new Project[MAX_SIZE];
  private static int projectLength = 0;


  public static void excuteProjectCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        addProject();
        break;
      case "목록":
        listProject();
        break;
      case "조회":
        viewProject();
        break;
      case "변경":
        updateProject();
        break;
      case "삭제":
        deleteProject();
        break;
    }
  }

  static void addProject() {
    Project project = new Project();
    project.setTitle(Prompt.input("프로젝트명?"));
    project.setDescription(Prompt.input("설명?"));
    project.setStartDate(Prompt.input("시작일?"));
    project.setEndDate(Prompt.input("종료일?"));
    addMembers(project);
    projects[projectLength++] = project;
  }

  static void listProject() {
    System.out.println("번호 프로젝트명 기간");
    for (int i = 0; i < projectLength; i++) {
      Project project = projects[i];
      System.out.printf("%d %s %s~%s\n", i + 1, project.getTitle(), project.getStartDate(),
          project.getEndDate());
    }
  }

  static void viewProject() {
    int projectNo = Prompt.inputInt("프로젝트번호?");
    if (projectNo < 1 || projectNo >= projectLength) {
      System.out.println("없는 프로젝트번호 입니다.");
      return;
    }
    Project project = projects[projectNo - 1];
    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("설명: %s\n", project.getDescription());
    System.out.printf("기간: %s ~ %s\n", project.getStartDate(), project.getEndDate());
    System.out.println("팀원");
    for (int i = 0; i < project.getMemberSize(); i++) {
      User user = project.getMember(i);
      System.out.printf("- %s\n", user.getName());
    }
    System.out.println("프로젝트 조회");
  }

  static void updateProject() {
    int projectNo = Prompt.inputInt("프로젝트번호?");
    if (projectNo < 1 || projectNo >= projectLength) {
      System.out.println("없는 프로젝트번호 입니다.");
      return;
    }
    Project project = projects[projectNo - 1];
    project.setTitle(Prompt.input("프로젝트명(%s)?", project.getTitle()));
    project.setDescription(Prompt.input("설명(%s)?", project.getDescription()));
    project.setStartDate(Prompt.input("시작일(%s)?", project.getStartDate()));
    project.setEndDate(Prompt.input("종료일(%s)?", project.getEndDate()));
    System.out.println("팀원:");
    deleteMembers(project);
    addMembers(project);
  }

  static void deleteProject() {
    int projectNo = Prompt.inputInt("프로젝트번호?");
    if (projectNo < 1 || projectNo >= projectLength) {
      System.out.println("없는 프로젝트번호 입니다.");
      return;
    }
    for (int i = projectNo; i < projectLength; i++) {
      projects[i - 1] = projects[i];
    }
    projects[--projectLength] = null;
  }

  static void addMembers(Project project) {
    while (true) {
      int userNo = Prompt.inputInt("추가할 팀원 번호?(종료:0)");
      if (userNo == 0) {
        break;
      }
      User user = UserCommand.findByNo(userNo);
      if (user == null) {
        System.out.println("없는 팀원입니다.");
        continue;
      }
      if (project.contain(user)) {
        System.out.printf("'%s'은 현재 팀원입니다.", user.getName());
        continue;
      }
      project.addMember(user);
    }
  }

  static void deleteMembers(Project project) {
    for (int i = project.getMemberSize() - 1; i >= 0; i--) {
      User user = project.getMember(i);
      String str = Prompt.input("팀원(%s) 삭제?", user.getName());
      if (str.equalsIgnoreCase("y")) {
        project.deleteMember(i);
        System.out.printf("'%s' 팀원을 삭제합니다.", user.getName());
      } else {
        System.out.printf("'%s' 팀원을 유지합니다.", user.getName());
      }
    }
  }
}

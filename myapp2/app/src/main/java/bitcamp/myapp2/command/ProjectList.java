package bitcamp.myapp2.command;

import bitcamp.myapp2.vo.Project;

public class ProjectList {
  private static final int MAX_SIZE = 10;
  private static final Project[] projects = new Project[MAX_SIZE];
  private static int projectLength = 0;

  public static void add(Project project) {
    projects[projectLength++] = project;
  }

  public static Project delete(int projectNo) {
    Project deleteProject = findByNo(projectNo);
    if (deleteProject == null) {
      return null;
    }
    int index = indexOf(deleteProject);
    for (int i = index + 1; i < projectLength; i++) {
      projects[i - 1] = projects[i];
    }
    projects[--projectLength] = null;
    return deleteProject;
  }

  public static Project[] toArray() {
    Project[] arr = new Project[projectLength];
    System.arraycopy(projects, 0, arr, 0, projectLength);
    return arr;
  }

  static Project findByNo(int projectNo) {
    for (int i = projectNo; i < projectLength; i++) {
      Project project = projects[i];
      if (project.getNo() == projectNo) {
        return project;
      }
    }
    return null;
  }

  static int indexOf(Project project) {
    for (int i = 0; i < projectLength; i++) {
      if (projects[i] == project) {
        return i;
      }
    }
    return -1;
  }

}

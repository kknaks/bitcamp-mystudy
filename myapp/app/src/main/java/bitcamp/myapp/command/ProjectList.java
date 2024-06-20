package bitcamp.myapp.command;

import bitcamp.myapp.vo.Project;

public class ProjectList {
  private static final int MAX_SIZE = 100;
  private static Project[] projects = new Project[MAX_SIZE];
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

  public static Project[] arr() {
    Project arr[] = new Project[projectLength];
    for (int i = 0; i < arr().length; i++) {
      arr[i] = projects[i];
    }
    return arr;
  }

  static Project findByNo(int projectNo) {
    for (int i = 0; i < projectLength; i++) {
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

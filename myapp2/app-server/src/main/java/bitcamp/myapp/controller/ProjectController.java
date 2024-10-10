package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;
import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectController {

  private ProjectService projectService;
  private UserService userService;

  public ProjectController(ProjectService projectService, UserService userService) {
    this.projectService = projectService;
    this.userService = userService;
  }

  @RequestMapping("/project/form1")
  public String form1() throws Exception {
    return "/project/form1.jsp";
  }

  @RequestMapping("/project/form2")
  public String form2(Project project, Map<String, Object> map, HttpSession session)
      throws Exception {
    session.setAttribute("project", project);

    List<User> users = userService.list();
    map.put("users", users);
    return "/project/form2.jsp";
  }

  @RequestMapping("/project/form3")
  public String form3(@RequestParam("member") int[] memberNos, HttpSession session)
      throws Exception {

    Project project = (Project) session.getAttribute("project");

    if (memberNos.length > 0) {
      ArrayList<User> members = new ArrayList<>();
      for (int memberNo : memberNos) {
        User user = userService.get(memberNo);
        members.add(user);
      }
      project.setMembers(members);
    }

    return "/project/form3.jsp";
  }

  @RequestMapping("/project/add")
  public String add(HttpSession session) throws Exception {
    Project project = (Project) session.getAttribute("project");
    projectService.add(project);
    session.removeAttribute("project");
    return "redirect:list";
  }

  @RequestMapping("/project/list")
  public String list(Map<String, Object> map) throws Exception {
    List<Project> list = projectService.list();
    map.put("list", list);
    return "/project/list.jsp";
  }

  @RequestMapping("/project/view")
  public String view(@RequestParam("no") int projectNo, Map<String, Object> map) throws Exception {
    Project project = projectService.get(projectNo);
    map.put("project", project);

    List<User> users = userService.list();
    map.put("users", users);
    return "/project/view.jsp";
  }

  @RequestMapping("/project/update")
  public String update(Project project, @RequestParam("member") int[] membersNos) throws Exception {
    Project project = new Project();
    project.setNo(Integer.parseInt(req.getParameter("no")));
    project.setTitle(req.getParameter("title"));
    project.setDescription(req.getParameter("description"));
    project.setStartDate(Date.valueOf(req.getParameter("startDate")));
    project.setEndDate(Date.valueOf(req.getParameter("endDate")));

    String[] memberNos = req.getParameterValues("member");
    if (memberNos != null) {
      ArrayList<User> members = new ArrayList<>();
      for (String memberNo : memberNos) {
        members.add(new User(Integer.parseInt(memberNo)));
      }
      project.setMembers(members);
    }

    if (!projectService.update(project)) {
      throw new Exception("없는 프로젝트입니다!");
    }
    return "redirect:list";
  }

  @RequestMapping("/project/delete")
  public String delete(HttpServletRequest req, HttpServletResponse res) throws Exception {
    int projectNo = Integer.parseInt(req.getParameter("no"));

    if (projectService.delete(projectNo)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 프로젝트입니다.");
    }
  }
}

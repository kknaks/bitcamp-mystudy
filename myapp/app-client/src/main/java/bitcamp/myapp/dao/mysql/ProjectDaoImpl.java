package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
  private Connection con;
  private UserDao userDao;

  public ProjectDaoImpl(Connection con, UserDao userDao) {
    this.con = con;
    this.userDao = userDao;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    StringBuilder strBuilder = new StringBuilder();
    for (User user : project.getMembers()) {
      if (strBuilder.length() > 0) {
        strBuilder.append(",");
      }
      strBuilder.append(user.getNo());
    }
    try (Statement stmt = con.createStatement()) {
      stmt.executeUpdate(String.format(
          "insert into myapp_projects(title, description, start_date, end_date, members) values ('%s','%s','%s','%s','%s')",
          project.getTitle(), project.getDescription(), project.getStartDate(),
          project.getEndDate(), strBuilder));
      return true;
    }
  }

  @Override
  public List<Project> list() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_projects order by project_id asc")) {

      ArrayList<Project> list = new ArrayList<>();

      while (rs.next()) {
        Project project = new Project();
        project.setNo(rs.getInt("project_id"));
        project.setTitle(rs.getString("title"));
        project.setStartDate(rs.getString("start_date"));
        project.setEndDate(rs.getString("end_date"));

        list.add(project);
      }
      return list;
    }
  }

  @Override
  public Project findBy(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_projects where project_id = " + no)) {

      if (rs.next()) {
        Project project = new Project();
        project.setNo(rs.getInt("project_id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setStartDate(rs.getString("start_date"));
        project.setEndDate(rs.getString("end_date"));
        String members = rs.getString("members");
        for (String memberNo : members.split(",")) {
          User member = userDao.findBy(Integer.parseInt(memberNo));
          project.getMembers().add(member);
        }
        return project;
      }
    }
    return null;
  }

  @Override
  public boolean update(Project project) throws Exception {
    StringBuilder strBuilder = new StringBuilder();
    for (User user : project.getMembers()) {
      if (strBuilder.length() > 0) {
        strBuilder.append(",");
      }
      strBuilder.append(user.getNo());
    }
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(String.format(
          "update myapp_projects set" + " title='%s'," + " description='%s'," + " start_date='%s'," + " end_date='%s'" + " members='%s'" + " where user_id=%d",
          project.getTitle(), project.getDescription(), project.getStartDate(),
          project.getEndDate(), strBuilder));
      return count > 0;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(String.format("delete from myapp_projects where project_id=%d", no));
      return count > 0;
    }
  }
}

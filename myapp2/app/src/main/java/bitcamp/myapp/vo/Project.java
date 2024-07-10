package bitcamp.myapp.vo;

import java.util.ArrayList;

public class Project {

  private static int seqNo;
  private final ArrayList members = new ArrayList();
  private int no;
  private String title;
  private String description;
  private String startDate;
  private String endDate;

  public Project() {
  }

  public Project(int projectNo) {
    this.no = projectNo;
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public ArrayList getMembers() {
    return members;
  }
}

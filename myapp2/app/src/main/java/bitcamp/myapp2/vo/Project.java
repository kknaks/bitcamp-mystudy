package bitcamp.myapp2.vo;

import bitcamp.myapp2.command.ArrayList;

public class Project {
  private static int seqNo;
  private ArrayList members = new ArrayList();
  private int no;
  private String title;
  private String description;
  private String startDate;
  private String endDate;

  //  private final User[] users = new User[10];
  //  private int memberSize;

  public static int getSeqNo() {
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
  //  public int getMemberSize() {
  //    return members.size();
  //  }
  //
  //  public Object getMember(int index) {
  //    return members.get(index);
  //  }
  //
  //  public void addMember(User user) {
  //    members.add(user);
  //  }
  //
  //  public void deleteMember(int index) {
  //    members.remove(index);
  //  }
}

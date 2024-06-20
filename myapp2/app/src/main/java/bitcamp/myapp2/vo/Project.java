package bitcamp.myapp2.vo;

public class Project {
  private static int seqNo;
  private final User[] users = new User[10];
  private int no;
  private String title;
  private String description;
  private String startDate;
  private String endDate;
  private int memberSize;


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

  public int getMemberSize() {
    return memberSize;
  }

  public void setMemberSize(int memberSize) {
    this.memberSize = memberSize;
  }

  public User[] getUsers() {
    return users;
  }

  public User getMember(int index) {
    return users[index];
  }

  public boolean contain(User user) {
    for (int i = 0; i < memberSize; i++) {
      User member = users[i];
      if (user.getName().equals(member.getName())) {
        return true;
      }
    }
    return false;
  }

  public void addMember(User user) {
    users[memberSize++] = user;
  }

  public void deleteMember(int index) {
    for (int i = index + 1; i < memberSize; i++) {
      users[i - 1] = users[i];
    }
    users[--memberSize] = null;
  }
}

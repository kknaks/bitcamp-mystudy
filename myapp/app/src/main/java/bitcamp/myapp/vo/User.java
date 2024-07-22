package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

// 메모리 설계도
public class User implements Serializable {

  private static int seqNo;

  private int no;
  private String name;
  private String email;
  private String password;
  private String tel;

  public User() {
  }

  public User(int no) {
    this.no = no;
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public static void initSeqNo(int no) {
    seqNo = no;
  }

  public static int getSeqNo() {
    return seqNo;
  }

  public static User valueOf(String csv) {
    String[] values = csv.split(",");
    User user = new User();
    user.setNo(Integer.valueOf(values[0]));
    user.setName(values[1]);
    user.setEmail(values[2]);
    user.setPassword(values[3]);
    user.setTel(values[4]);
    return user;
  }

  @Override
  public String toString() {
    return "User{" + "no=" + no + ", name='" + name + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", tel='" + tel + '\'' + '}';
  }

  public String toCsvString() {
    return new StringBuilder().append(no).append(",").append(name).append(",").append(email)
        .append(",").append(password).append(",").append(tel).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return no == user.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }
}

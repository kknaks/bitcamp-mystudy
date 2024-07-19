package bitcamp.myapp.vo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

// 메모리 설계도
public class User {

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

  public static void initSeqNo(int maxUserNo) {
    seqNo = maxUserNo;
  }


  public static User valueOf(byte[] bytes) throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
      User user = new User();
      user.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

      byte[] buffer = new byte[10000];
      int len = in.read() << 8 | in.read();
      in.read(buffer, 0, len);
      user.setName(new String(buffer, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buffer, 0, len);
      user.setEmail(new String(buffer, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buffer, 0, len);
      user.setPassword(new String(buffer, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buffer, 0, len);
      user.setTel(new String(buffer, 0, len, StandardCharsets.UTF_8));
      return user;
    }
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

  public byte[] getBytes() throws IOException {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      //회원번호 넣기
      out.write(no >> 24);
      out.write(no >> 16);
      out.write(no >> 8);
      out.write(no);

      //이름 넣기
      byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(bytes);

      //이메일 넣기
      bytes = email.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(bytes);

      //password
      bytes = password.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(bytes);

      //tel
      bytes = tel.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(bytes);

      return out.toByteArray();
    }
  }
}

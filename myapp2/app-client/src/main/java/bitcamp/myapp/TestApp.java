package bitcamp.myapp;

import java.util.List;
import java.util.stream.Collectors;

public class TestApp {

  public static void main(String[] args) {
    // 예제 User 객체 리스트 생성
    List<User> users = List.of(
        new User(1, "Alice"),
        new User(2, "Bob"),
        new User(3, "Charlie")
    );

    // Stream API를 사용하여 no 필드 값을 CSV 형식의 문자열로 변환
    String csvString = users.stream()
        .map(user -> String.valueOf(user.getNo()))  // User의 no 필드 값을 문자열로 변환
        .collect(Collectors.joining(","));          // 문자열을 ,로 구분하여 연결

    // 결과 출력
    System.out.println(csvString);  // 출력: "1,2,3"
  }

  static class User {

    int no;
    String name;

    public User(int no, String name) {
      this.no = no;
      this.name = name;
    }

    public int getNo() {
      return no;
    }

    public String getName() {
      return name;
    }
  }

}

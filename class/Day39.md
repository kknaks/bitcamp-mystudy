<h1> Decorator 설계 패턴 </h1>
    <h2 style="margin-left: 10px;"> 1. Decorator 설계 패턴의 개념</h2>
        <ul>
            <li> 객체에 동적으로 새로운 행동(기능)을 추가할 수 있는 패턴이다. 주로 상속 대신 사용되며, 원래 객체를 수정하지 않고도 행동을 확장할 수 있다. </li>
            <li> 구성요소</li>
                <ol style="padding-left: 10px">
                    <li><b>컴포넌트(Component)</b> : 기본 인터페이스나 추상 클래스로, 구체적인 객체들과 데코레이터들이 구현할 공통 메서드를 정의합니다.</li>
                    <li><b>구체 컴포넌트(ConcreteComponent)</b> : 실제로 기능을 구현하는 클래스입니다. 기본 컴포넌트의 구체적인 형태입니다.</li>
                    <li><b>데코레이터(Decorator)</b> : 기본 컴포넌트의 인터페이스를 구현하면서, 추가된 행동을 제공하는 추상 클래스입니다. 이 클래스는 컴포넌트 객체를 래핑(wrapping)하여 동적으로 행동을 추가합니다.</li>
                    <li><b>구체 데코레이터(ConcreteDecorator)</b>: 데코레이터의 구체적인 구현으로, 추가된 행동을 정의합니다.</li>
                </ol>
        </ul>
        <h3 style="margin-left: 20px;"> 1) Decorator 사용 전</h3>
            <ul>
            <li> 여러기능을 추가하기 위해서는 상속을 사용해서 추가하여야한다.</li>
            <li> 3가지 기능을 여러 조합으로 사용하면 무수히 많은 클래스가 나온다. </li>
            <li> 순서에 상관 없는 경우 8가지 클래스를 생성해야 하며 순서가 상관있는 경우 27가지의 클래스를 생성해야한다.</li>
            <li> 각클래스마다 생성자의 매개변수도 다양해서 일관성도 저해된다.</li>
            <img width="1158" alt="image" src="https://github.com/user-attachments/assets/87a6e829-6ed2-47fc-a110-6fc226f8b88a">
            </ul>
        <h3 style="margin-left: 20px;"> 2) Decorator 사용 후</h3>
            <ul>
            <li> 필요한 기능을 선택적으로 사용 가능하다.</li>
            <img width="911" alt="image" src="https://github.com/user-attachments/assets/564b0033-d156-457c-a3ce-5e32f4f3eb8e">
<li>인터페이스와 컴포넌트</li>

```java
public interface Printer {
  void print(String s);
}

public class ContentPrinter implements Printer {
  @Override
  public void print(String s) {
    System.out.println(s);
  }
}
```

<li>데코레이터와 구현데코레이터</li>
<details>
<summary> 코드 접기/펴기</summary>

```java
public abstract class PrinterDecorator implements Printer {
  protected Printer origin;

  public PrinterDecorator(Printer printer) {
    this.origin = printer;
  }
}

public class HeaderPrinter extends PrinterDecorator {
  String header;

  public HeaderPrinter(Printer printer, String header) {
    super(printer);
    this.header = header;
  }

  @Override
  public void print(String s) {
    System.out.println(header);
    origin.print(s);
  }
}

public class FooterPrinter extends PrinterDecorator {
  String footer;

  public FooterPrinter(Printer printer, String footer) {
    super(printer);
    this.footer = footer;
  }

  @Override
  public void print(String s) {
    origin.print(s);
    System.out.println(footer);
  }
}

public class SignPrinter extends PrinterDecorator {
  String sign;

  public SignPrinter(Printer printer, String sign) {
    super(printer);
    this.sign = sign;
  }

  @Override
  public void print(String s) {
    origin.print(s);
    System.out.println("   by   " + sign);
  }
}

```
</details>

<li>예제</li>
<details>
<summary> 코드 접기/펴기</summary>

```java
public class Test01 {
  public static void main(String[] args) {
    ContentPrinter printer = new ContentPrinter();
    printer.print("안녕하세요");
    System.out.println("---------printer 종료");

    ContentPrinter printer2 = new ContentPrinter();
    HeaderPrinter printer2H = new HeaderPrinter(printer2, "머릿말");
    printer2H.print("안녕하세요");
    System.out.println("---------printer2 종료");

    ContentPrinter printer3 = new ContentPrinter();
    HeaderPrinter printer3H = new HeaderPrinter(printer3, "머릿말");
    FooterPrinter printer3F = new FooterPrinter(printer3H, "꼬릿말");
    printer3F.print("안녕하세요");
    System.out.println("---------printer3 종료");

    ContentPrinter printer4 = new ContentPrinter();
    FooterPrinter printer4F = new FooterPrinter(printer4, "꼬릿말");
    printer4F.print("안녕하세요");
    System.out.println("---------printer4 종료");

    ContentPrinter printer5 = new ContentPrinter();
    SignPrinter printer5S = new SignPrinter(printer5, "naknak");
    printer5S.print("안녕하세요");
    System.out.println("---------printer5 종료");

    ContentPrinter printer6 = new ContentPrinter();
    SignPrinter printer6S = new SignPrinter(printer5, "naknak");
    HeaderPrinter printer6H = new HeaderPrinter(printer6S, "머릿말");
    printer6H.print("안녕하세요");
    System.out.println("---------printer6 종료");
  }
}
```
</details>
<li>결과</li>
<img width="1008" alt="image" src="https://github.com/user-attachments/assets/eec121bd-beaf-4e17-809b-114e1d04707d">
 </ul>

<h1> myApp에 적용하기 </h1>
    <h2 style="margin-left: 10px;"> 1. File I/O Stream</h2>
    <ul>
    <li>File I/O Stream을 Dataa I/O Stream에 장착하여 Int,UTF,IEE-754를 읽어온다. </li>

![image](https://github.com/user-attachments/assets/179f515e-154e-4ca2-b88c-4a786948addc)
<details>
<summary> 코드 접기/펴기</summary>

```java
  private void loadUser() {
    try (FileInputStream in0 = new FileInputStream("user.data");
        DataInputStream in = new DataInputStream(in0)) {
      int userLength = in.readInt();
      int maxUserNum = 0;
      for (int i = 0; i < userLength; i++) {
        User user = new User();
        user.setNo(in.readInt());
        user.setName(in.readUTF());
        user.setEmail(in.readUTF());
        user.setPassword(in.readUTF());
        user.setTel(in.readUTF());
        userList.add(user);

        maxUserNum = Math.max(maxUserNum, user.getNo());
      }
      User.initSeqNo(maxUserNum);
    } catch (IOException e) {
      System.out.println("회원 정보 로딩 중 오류 발생" + e.getMessage());
    }
  }

  private void saveUser() {
    try (FileOutputStream out0 = new FileOutputStream("user.data");
        DataOutputStream out = new DataOutputStream(out0)) {

      out.writeInt(userList.size());

      for (User user : userList) {
        out.writeInt(user.getNo());
        out.writeUTF(user.getName());
        out.writeUTF(user.getEmail());
        out.writeUTF(user.getPassword());
        out.writeUTF(user.getTel());
      }
    } catch (IOException e) {
      System.out.println("회원 정보 저장 중 오류 발생" + e.getMessage());
    }
  }
  //user.java에 valueOf, getBytes는 삭제
```
</details>
    </ul>
    <h2 style="margin-left: 10px;"> 2. 파일 직렬화/역직렬화(Object I/O Stream)</h2>
        <ul>
        <li> 객체가 Serializble 인터페이스를 받아 구현체로 역할을 한다. </li>
        <li> 객체가 Serializble를 구현하면 Object I/O Stream을 통해 인스턴스된 객체를 byte[]로 변환 할 수 있다.</li>

![image](https://github.com/user-attachments/assets/3a620f2c-e06c-4755-a3a0-ea2f2dd06cc2)
        <li> Data I/O Stream처럼 데코레이터 패턴을 통해 File I/O Stream으로 내보낼 수 있다.</li>

![image](https://github.com/user-attachments/assets/dca9378a-9550-40b0-b0b0-17938c9e4748)
<details>
<summary> 코드 접기/펴기</summary>

```java
    private void loadUser() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.data"))) {

        userList = (List<User>) in.readObject();
        int maxUserNum = 0;
        for (User user : userList) {
            maxUserNum = Math.max(maxUserNum, user.getNo());
        }
        User.initSeqNo(maxUserNum);
        } catch (IOException  | ClassNotFoundException e) {
        System.out.println("회원 정보 로딩 중 오류 발생" + e.getMessage());
        userList = new ArrayList<>();
        }
    }

    private void saveUser() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.data"))) {
        out.writeObject(userList);
        } catch (IOException e) {
        System.out.println("회원 정보 저장 중 오류 발생" + e.getMessage());
        }
    }

  //User.java에서 implement를 통해 구현체로 만들기
  public class User implements Serializable{/*생략*/}

```
</details>
    </ul>
    <h2 style="margin-left: 10px;"> 3. File I/O API : csv포맷으로 출력하기  </h2>
        <ul>
        <li> 객체 정보를 ","로 구분한다. </li>

![image](https://github.com/user-attachments/assets/307f0b0a-9a63-4499-be7d-a5f541b0721c)
<details>
<summary> 코드 접기/펴기</summary>

```java
  private void loadUsers() {
    try (Scanner in = new Scanner(new FileReader("user.csv"))) {
      while (true) {
        try {
          String csv = in.nextLine();
          userList.add(User.valueOf(csv));
        } catch (Exception e) {
          break;
        }
      }

      int maxUserNo = 0;
      for (User user : userList) {
        if (user.getNo() > maxUserNo) {
          maxUserNo = user.getNo();
        }
      }

      User.initSeqNo(maxUserNo);

    } catch (IOException e) {
      System.out.println("회원 정보 로딩 중 오류 발생!");
      // e.printStackTrace();
    }
  }

  private void saveUsers() {
    try (FileWriter out = new FileWriter("user.csv")) {

      for (User user : userList) {
        out.write(user.toCsvString() + "\n");
      }

    } catch (IOException e) {
      System.out.println("회원 정보 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }
  ```
  </details>


<details>
<summary> 유저클래스 수정 접기/펴기</summary>

```java
  public static User valueOf(String csv) {
    String[] values = csv.split(","); // csv: "1,홍길동,hong@test.com,1111,010-1111-2222"
    User user = new User();
    user.setNo(Integer.parseInt(values[0]));
    user.setName(values[1]);
    user.setEmail(values[2]);
    user.setPassword(values[3]);
    user.setTel(values[4]);
    return user;
  }

  public String toCsvString() {
    return new StringBuilder().append(no).append(",").append(name).append(",").append(email)
        .append(",").append(password).append(",").append(tel).toString();
  }
  ```
  </details>
        </ul>


	
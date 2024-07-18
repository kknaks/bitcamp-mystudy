<h1> Stack 리팩토링 - menuPath </h1>
<h2 style="margin-left: 10px;"> 1. 기존 스택의 문제점</h2>
<ul>

```java
//기존 menuGroup에서 Stack이 사용되는 소스코드
  public MenuGroup(String title) {
    super(title);
    this.menuPath = new Stack<>();
  }

  public void setParent(MenuGroup parent) {
    this.parent = parent;
    this.menuPath = parent.menuPath;
  }

  private String getMenuPathTitle(Stack<String> menuPath) {
    StringBuilder strBuilder = new StringBuilder();
    for (String s : menuPath) {
      if (!strBuilder.isEmpty()) {
        strBuilder.append("/");
      }
      strBuilder.append(s);
    }
    return strBuilder.toString();
  }
```
<li> 기존 소스코드에서 Stack은 menuGroup이 인스턴스 될 때마다 새로운 Stack을 생성한다.</li>
<li> 부모의 menuGroup이 있다면 인스턴스된 Stack은 Garbage가 된다.</li>
<li> menuPath를 호출하는 메서드는 Stack의 구조로 꺼내는 것이 아니라 List타입으로 탐색을 한다.</li>
</ul>
<h2 style="margin-left: 10px;"> 2. 스택 구조 변경</h2>
<ol>
<li> 현재 위치의 menuTitle을 push한다.</li>
<li> 부모가 있다면 부모의 menuPath로 이동한다.</li>
<li> root의 부모인 null를 만날때 까지 1~2를 반복한다.</li>

![image](https://github.com/user-attachments/assets/26272a60-dbc7-4c51-8476-a1dc86d8296c)

```java
  public MenuGroup(String title) {
    super(title);
  }

  public void setParent(MenuGroup parent) {
    this.parent = parent;
  }

  private String getMenuPath() {
    Stack<String> menuPath = new Stack<>();
    MenuGroup menuGroup = this;
    while (menuGroup != null) {
      menuPath.push(menuGroup.title);
      menuGroup = menuGroup.parent;
    }
    
    StringBuilder strBuilder = new StringBuilder();
    while (!menuPath.empty()) {
      if (!strBuilder.isEmpty()){
        strBuilder.append("/");
      }
      strBuilder.append(menuPath.pop());
    }
    return strBuilder.toString();
  }
```
</ol>


<h1> 파일 입출력 FILE I/O </h1>
<h2 style="margin-left: 10px;"> 1. 바이너리 데이터 입출력</h2>
<ul>
<li> 바이너리 타입의 입력은 텍스트 타입 보다 메모리를 적게사용하고, 속도가 빠르다.</li>
<li> 타입별로 인코딩하는 방식이 다르다.</li>
<ul>
<li>"abc" --> UTF-8 문자코드 바이트</li>
<li> 20   --> 2의 보수</li>
<li> 3.14 --> IEEE-754 규칙</li>
<li> true --> 2의 보수</li>
</ul>
<li> 설정 방법이 복잡하다.</li>
<li> PDF, PPT, DOC, GIF, JPEG, MP3, AVI 등이 있다.</li>
</ul>

<h2 style="margin-left: 10px;"> 2. 데이터 입출력 적용</h2>
<h3 style="margin-left: 20px;"> 1) 데이터 입출력 흐름</h3>
<ul>

![image](https://github.com/user-attachments/assets/be6ce308-8c67-4675-af86-250592781bd7)
</ul>
<h3 style="margin-left: 20px;"> 2) byte[] 만들기 </h3>
<ul>
<li> 객체를 byete[] 배열에 1바이트씩 담아야한다.</li>
<li> ByteArrayOutputStream 클래스를 이용하여 바이트배열 담을 저장소를 만든다.</li>
<li> 이후 toByteArray()메서드로 통해 바이트 배열을 리턴 할 수 있다.</li>

```java
  public byte[] getBytes() throws IOException {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      //객체의 정보들을 byte로 담는다.
      return out.toByteArray();
    }
  }
```
</ul>

<h3 style="margin-left: 20px;"> 3) write(int) 메소드</h3>
<ul>
<li> 객체를 byete[] 배열에 1바이트씩 담을때 write 메서드를 사용한다.</li>
<li> write(int) int 타입을 받지만 1바이트만 읽고 저장할 수 있다.</li>
<li> 비트 연산자를 통해 바이트를 1바이트까지 이동 해야한다.</li>

```java
    ByteArrayOutputStream out = new ByteArrayOutputStream()
    out.write(no >> 24);
    out.write(no >> 16);
    out.write(no >> 8);
    out.write(no);
```
</ul>
<h3 style="margin-left: 20px;"> 4) 데이터 구조</h3>
<ul>
<li> 데이터의 구조에 따라 바이트의 할당규칙을 정해야 load과정에서 해당규칙을 통해 값을 읽을 수 있다.</li>

![image](https://github.com/user-attachments/assets/e11fcf04-17e0-4b12-8dae-4e084d615720)
</ul>
<h3 style="margin-left: 20px;"> 5) 파일로 출력</h3>
<ul>
<li> 데이터의 구조에 따라 바이트배열들을 병합하고 객체의 전체 개수를 구한다 .</li>
<li> FileOutputStream에 바이트의 배열들 개수를 넣고 저장한 바이트 배열들을 넣는다. </li>

![image](https://github.com/user-attachments/assets/2f897cc4-b7b7-4242-bd02-10ec8e4501df)
</ul>
<h3 style="margin-left: 20px;"> 6) 파일로 입력</h3>
<ul>
<li> 입력은 출력과정의 역순으로 진행한다.</li>

![image](https://github.com/user-attachments/assets/4cbd2d51-9a64-4f10-a28f-bceea9c786c7)
</ul>






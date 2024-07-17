<h1> Composit Pattern </h1>
<h2 style="margin-left: 10px;"> 1.컴포짓 패턴의 개념 </h2>
<h3 style="margin-left: 20px;"> 1) 정의 </h3>
<ul>
<li> 객체들의 트리를 구성하여 부분-전체 계층 구조를 나타내는 패턴이다.</li>
<li> <b>Component</b> : 공통 인터페이스를 정의하여 단일 객체와 복합 객체가 동일한 방식으로 처리될 수 있도록 한다. </li>
<li> <b>Leaf</b> : 트리의 말단 요소로 더이상 하위 요소를 가지지 않는 객체를 나타낸다.</li>
<li> <b>Composite</b> : 하위 요소를 가지는 복합 객체로, 하위 요소들을 관리하고 해당 요소들에게 작업을 전달한다.</li>
</ul>
<h3 style="margin-left: 20px;"> 2) UML으로 이해하기 </h3>
<ul>
<li> 패턴 적용 전 : 여러객체에서 중복된 코드가 발생하며, 하나의 객체가 여러개의 역할을 한다.</li>

![image](https://github.com/user-attachments/assets/a1f77d26-abd6-484f-8496-240b1b081da5)

<li> 패턴 적용 후 : 기능별/역할별로 분리</li>

![image](https://github.com/user-attachments/assets/a2a18ae5-f1ce-4d6a-8fb5-ca42e2d33a0d)
</ul>

<h2 style="margin-left: 10px;"> 2.MyApp에 적용하기 </h2>
<h3 style="margin-left: 20px;"> 1) Menu생성하기 </h3>
<ol>
<li> 메뉴 인터페이스만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다.</li>
<li> 메뉴들의 title을 불러올 규칙이 필요하다.</li>

```java
public interface Menu {
  void execute();

  String getTitle();
}
```

</ul>
<li> 메뉴 추상클래스 만들기</li>
<ul style="padding-left: 10px">
<li> 메뉴그룹과 메뉴아이템의 제목이 들어 올때 저장 할 필드 값 title 필요하다.</li>
<li> title을 불러올 getter가 필요하다.</li>
<li> 트리노드에서 부모노드의 객체와 같은 확인하는 메서드 equals()와 hash()가 필요하다.</li>
<details>
<summary> 코드 접기/펼치기</summary>

```java
import java.util.Objects;

public abstract class AbstractMenu implements Menu {
  protected String title;

  public AbstractMenu(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (!(object instanceof AbstractMenu that))
      return false;
    return Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(title);
  }

  @Override
  public String getTitle() {
    return title;
  }
}
```
</details>

</ul>
<li> 메뉴 그룹만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()를 구현하는 메서드가 필요하다.</li>
<li> 메뉴아이템의 값들을 List로 담을 addMenu()메서드가 필요하다.</li>
<li> 메뉴아이템의 값들을 List에서 제거할 removeMenu()메서드가 필요하다.</li>
<li> 메뉴아이템의 값들을 List에서 얻어올 getMenu()메서드가 필요하다.</li>
<li> 메뉴아이템의 갯수를 세는 countMenu()메서드가 필요하다.</li>
<ol style="padding-left: 10px">
<li> root menuGroup</li>
<li> tree menuGroup</li>
</ol>
<details>
<summary> 코드 접기/펼치기</summary>

```java
import bitcamp.myapp.util.Prompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MenuGroup extends AbstractMenu {
  private MenuGroup parent;
  private Stack<String> menuPath;
  private List<Menu> children = new ArrayList<>();

  public MenuGroup(String title) {
    super(title);
    this.menuPath = new Stack<>();
  }

  public void setParent(MenuGroup parent) {
    this.parent = parent;
    this.menuPath = parent.menuPath;
  }

  @Override
  public void execute() {
    menuPath.push(title);
    printMenus();
    while (true) {
      String command = Prompt.input("%s>", getMenuPathTitle(menuPath));
      if (command.equals("menu")) {
        printMenus();
        continue;
      } else if (command.equals("9")) { // 이전 메뉴 선택
        menuPath.pop();
        return;
      }

      try {
        int menuNo = Integer.parseInt(command);
        Menu menu = getMenu(menuNo);
        if (menu == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
          continue;
        }

        menu.execute();

      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  private void printMenus() {
    System.out.printf("[%s]\n", title);
    int i = 1;
    for (Menu child : children) {
      System.out.printf("%d. %s\n", i++, child.getTitle());
    }
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

  public void addMenu(Menu child) {
    if (child instanceof MenuGroup) {
      ((MenuGroup) child).parent.setParent(this);
    }
    children.add(child);
  }

  public void removeMenu(Menu child) {
    children.remove(child);
  }

  public Menu getMenu(int index) {
    if (index < 0 || index >= children.size()) {
      return null;
    }
    return children.get(index);
  }

  public int countMenu() {
    return children.size();
  }
}
```
</details>
</ul>
<li> 메뉴 아이템만들기</li>
<ul style="padding-left: 10px">
<li> command인터페이스의 인스턴스를 받을 필드값이 필요하다.</li>
<li> 종류에 따른 command를 구현체를 설정하는 setCommand()가 필요하다.</li>
<li> 추상메서드 execute()를 구현하는 메서드가 필요하다.</li>
</ul>
</ol>
<h3 style="margin-left: 20px;"> 2) Command수정하기 </h3>
<ol>
<li> 메뉴 인터페이스만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
<li> 메뉴 추상클래스 만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
<li> 메뉴 그룹만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
<li> 메뉴 아이템만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
</ol>
<h3 style="margin-left: 20px;"> 3) App수정하기 </h3>
<ol>
<li> 메뉴 인터페이스만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
<li> 메뉴 추상클래스 만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
<li> 메뉴 그룹만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
<li> 메뉴 아이템만들기</li>
<ul style="padding-left: 10px">
<li> 추상메서드 execute()가 필요하다</li>
</ul>
</ol>
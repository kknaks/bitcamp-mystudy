package bitcamp.myapp.menu;

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

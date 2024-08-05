package bitcamp.context;

import bitcamp.menu.MenuGroup;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
  private Map<String, Object> objContainer = new HashMap<>();
  private MenuGroup mainMenu = new MenuGroup("메인 메뉴");

  public void setAttribute(String name, Object value) {
    objContainer.put(name, value);
  }

  public Object getAttribute(String name) {
    return objContainer.get(name);
  }

  public MenuGroup getMainMenu() {
    return mainMenu;
  }
}

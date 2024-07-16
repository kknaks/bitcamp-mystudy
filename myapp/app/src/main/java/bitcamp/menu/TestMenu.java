package bitcamp.menu;

public class TestMenu {
  public static void main(String[] args) {
    MenuGroup root = new MenuGroup("main");

    MenuGroup file = new MenuGroup("file");
    root.add(file);

    MenuGroup edit = new MenuGroup("edit");
    root.add(edit);

    MenuItem help = new MenuItem("help");
    root.add(help);

    root.execute();

  }
}

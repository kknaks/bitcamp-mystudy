package bitcamp.menu;


import bitcamp.myapp.command.Command;

public class MenuItem extends AbstractMenu {
  Command command;

  public MenuItem(String title, Command command) {
    super(title);
    this.command = command;
  }

  @Override
  public void execute() {
    if (command != null) {

    }
    System.out.println(title);
  }
}

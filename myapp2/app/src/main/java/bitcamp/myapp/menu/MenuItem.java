package bitcamp.myapp.menu;

import bitcamp.myapp.command.Command;

public class MenuItem extends AbstractMenu {
  Command command;

  public MenuItem(String title) {
    super(title);
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  @Override
  public void execute() {
    if (command != null) {
      command.execute(title);
    } else {
      System.out.println(title);
    }
  }
}

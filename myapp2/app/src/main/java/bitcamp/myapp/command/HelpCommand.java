package bitcamp.myapp.command;

import java.util.Stack;

public class HelpCommand implements Command {
  public void execute(Stack menuPath) {

    System.out.println("도움말 입니다.");
  }
}

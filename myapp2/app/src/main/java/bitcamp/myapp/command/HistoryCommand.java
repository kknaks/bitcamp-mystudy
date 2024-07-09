package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.util.Stack;

public class HistoryCommand implements Command {
  @Override
  public void execute(Stack menuTitle) {
    Prompt.printHistory();
  }
}

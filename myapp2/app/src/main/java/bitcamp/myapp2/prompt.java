package bitcamp.myapp2;

public class prompt {
  static String input(String title) {
    System.out.print(title);
    return App.keyboardScanner.nextLine();
  }
}

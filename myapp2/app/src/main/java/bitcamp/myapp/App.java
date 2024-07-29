package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.listener.InitApplicationListener;
import bitcamp.util.Prompt;

import java.util.ArrayList;
import java.util.List;

public class App {
  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    App app = new App();
    app.addListener(new InitApplicationListener());
    app.execute();
  }

  void execute() {
    for (ApplicationListener listener : listeners) {
      try {
        listener.onStart(appCtx);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생");
      }
    }
    String appTitle = "[프로젝트 관리 시스템]";
    String line = "----------------------------------";

    try {
      appCtx.getMainMenu().execute();

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();

    }

    System.out.println("종료합니다.");

    Prompt.close();

    for (ApplicationListener listener : listeners) {
      try {
        listener.onShutdown(appCtx);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생");
      }
    }
  }

  public void addListener(ApplicationListener listner) {
    listeners.add(listner);
  }

  public void removeListener(ApplicationListener listner) {
    listeners.remove(listner);
  }
}

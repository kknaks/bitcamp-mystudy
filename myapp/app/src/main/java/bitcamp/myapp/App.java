package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.listener.initApplicationListener;
import bitcamp.util.Prompt;

import java.util.ArrayList;
import java.util.List;

public class App {

  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    App app = new App();
    app.addApplivationListener(new initApplicationListener());
    app.execute();
  }

  public void init() {

    //loadData();

  }

  private void addApplivationListener(ApplicationListener listener) {
    listeners.add(listener);
  }

  private void removeApplivationListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  void execute() {
    for (ApplicationListener listener : listeners) {
      try {
        listener.onStart(appCtx);
      } catch (Exception e) {
        System.out.println("리스터 실행 중 오류 발생");
      }
    }


    String appTitle = "[프로젝트 관리 시스템]";
    String line = "----------------------------------";

    try {
      appCtx.getMainMenu().execute();

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();

    } finally {
    }

    System.out.println("종료합니다.");

    Prompt.close();

    for (ApplicationListener listener : listeners) {
      try {
        listener.onShutdown(appCtx);
      } catch (Exception e) {
        System.out.println("리스터 실행 중 오류 발생");

      }
    }
  }
}

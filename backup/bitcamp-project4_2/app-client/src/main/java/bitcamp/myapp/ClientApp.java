package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.menu.MenuGroup;
import bitcamp.myapp.listener.InitApplicationListener;
import bitcamp.util.Prompt;

import java.util.ArrayList;
import java.util.List;

public class ClientApp {
  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    ClientApp app = new ClientApp();

    app.addApplicationListener(new InitApplicationListener());

    app.execute();
  }

  // 애플리케이션 리스너를 리스트에 추가하는 메서드
  private void addApplicationListener(ApplicationListener listener) {
    listeners.add(listener);
  }
  // 애플리케이션 리스너를 리스트에서 제거하는 메서드
  private void removeApplicationListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  void execute() {
    try {
      // 애플리케이션이 시작될 때 리스너에게 알린다.
      for (ApplicationListener listener : listeners) {
        try {
          listener.onStart(appCtx);
        } catch (Exception e) {
          System.out.println("리스너 실행 중 오류 발생!");
        }
      }

      System.out.println("[5목]");

      // 메인 메뉴를 가져와 실행
      MenuGroup mainMenu = (MenuGroup) appCtx.getAttribute("mainMenu");
      if (mainMenu != null) {
        mainMenu.execute();
      } else {
        System.out.println("메인 메뉴를 초기화하지 못했습니다.");
      }

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    }

    System.out.println("종료합니다.");

    // 프롬프트 종료
    Prompt.close();

    // 애플리케이션이 종료될 때 리스너에게 알린다.
    for (ApplicationListener listener : listeners) {
      try {
        listener.onShutdown(appCtx);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생!");
      }
    }
  }
}


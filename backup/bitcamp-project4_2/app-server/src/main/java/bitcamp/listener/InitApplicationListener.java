package bitcamp.listener;

import bitcamp.context.ApplicationContext;

public class InitApplicationListener implements ApplicationListener {
  @Override
  public void onStart(ApplicationContext ctx) throws Exception {
    System.out.println("서버 어플리케이션을 시작합니다.");
  }

  @Override
  public void onShutdown(ApplicationContext ctx) throws Exception {
    System.out.println("서버 어플리케이션을 종료합니다");
  }
}

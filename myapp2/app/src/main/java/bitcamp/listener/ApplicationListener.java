package bitcamp.listener;

import bitcamp.context.ApplicationContext;

public interface ApplicationListener {
  void onStart(ApplicationContext ctx);

  void onShutdown(ApplicationContext ctx);
}

package bitcamp.myapp.listener;

import bitcamp.myapp.config.AppConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.EnumSet;

@WebListener // 서블릿 컨테이너에 이 클래스를 배치하는 태그다.
public class ContextLoaderListener implements ServletContextListener {



  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 서블릿 컨테이너가 실행될 때 호출된다.
    try {
      System.out.println("서비스 관련 객체 준비!");

      ServletContext ctx = sce.getServletContext();

      AnnotationConfigWebApplicationContext iocContainer =
          new AnnotationConfigWebApplicationContext();
      iocContainer.register(AppConfig.class);
      iocContainer.setServletContext(ctx);
      iocContainer.refresh();

      ctx.setAttribute("sqlSessionFactory", iocContainer.getBean(SqlSessionFactory.class));

      DispatcherServlet dispatcherServlet = new DispatcherServlet(iocContainer);

      ServletRegistration.Dynamic servletRegistration = ctx.addServlet("app", dispatcherServlet);

      servletRegistration.addMapping("/app/*");
      servletRegistration.setLoadOnStartup(1);
      servletRegistration.setMultipartConfig(new MultipartConfigElement(
          new File("./temp").getAbsolutePath(),
          1024 * 1024 * 20,
          1024 * 1024 * 100,
          1024 * 1024 * 1));

      CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
      FilterRegistration.Dynamic filterRegistration =
          ctx.addFilter("characterEncodingFilter", characterEncodingFilter);
      filterRegistration.addMappingForServletNames(
          EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD),
          false,
          "app");
    } catch (Exception e) {
      System.out.println("서비스 객체 준비 중 오류 발생");
      e.printStackTrace();
    }
  }


}

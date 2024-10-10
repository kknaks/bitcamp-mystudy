package bitcamp.myapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// web.xml
//   <filter>
//        <filter-name>CharacterEncodingFilter</filter-name>
//        <filter-class>bitcamp.myapp.filter.CharacterEncodingFilter</filter-class>
//        <init-param>
//            <param-name>encoding</param-name>
//            <param-value>UTF-8</param-value>
//        </init-param>
//    </filter>
//    <filter-mapping>
//        <filter-name>CharacterEncodingFilter</filter-name>
//        <url-pattern>/*</url-pattern>
//    </filter-mapping>


@WebFilter(
        urlPatterns = "/*",
        initParams = @WebInitParam(name = "encoding", value = "UTF-8")
)
public class CharacterEncodingFilter implements Filter {

  private String encoding = "UTF-8";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    if (filterConfig.getInitParameter("encoding") != null) {
      encoding = filterConfig.getInitParameter("encoding");
    }
    System.out.println("CharacterEncodingFilter 객체 준비!");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (httpRequest.getMethod().equals("POST")) {
      request.setCharacterEncoding(this.encoding);
    }

    chain.doFilter(request, response); // 다음 필터 또는 서블릿 실행
  }
}

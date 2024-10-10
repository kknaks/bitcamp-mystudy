package bitcamp.myapp.servlet;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig(
    maxFileSize = 1024 * 1024 * 60,
    maxRequestSize = 1024 * 1024 * 100)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  private List<Object> controllers;

  @Override
  public void init() throws ServletException {
    controllers = (List<Object>) this.getServletContext().getAttribute("controllers");
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    try {
      String controllPath = req.getPathInfo();

      Object pageController = null;
      Method requestHandler = null;

      loop:
      for (Object controller : controllers) {
        Method[] methods = controller.getClass().getDeclaredMethods();
        for (Method m : methods) {
          RequestMapping requsetMapping = m.getAnnotation(RequestMapping.class);
          if (requsetMapping == null || !requsetMapping.value().equals(controllPath)) {
            continue;
          }
          requestHandler = m;
          pageController = controller;
          break loop;
        }
      }

      if (pageController == null) {
        throw new Exception("URL을 처리할 수 없습니다.");
      }
      Map<String, Object> map = new HashMap<>();
      Object[] arguments = prepareRequestHandlerArguments(requestHandler, req, res, map);

      if (requestHandler.getReturnType() == void.class) {
        requestHandler.invoke(pageController, arguments);
        return;
      }
      String viewName = (String) requestHandler.invoke(pageController, arguments);

      if (!map.isEmpty()) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
          req.setAttribute(entry.getKey(), entry.getValue());
        }
      }

      if (viewName.startsWith("redirect:")) {
        res.sendRedirect(viewName.substring(9));
      } else {
        req.getRequestDispatcher(viewName).forward(req, res);
      }
    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }

  private Object[] prepareRequestHandlerArguments(Method requestHandler, HttpServletRequest req,
      HttpServletResponse res, Map<String, Object> requestAttributesMap) throws Exception {

    Parameter[] params = requestHandler.getParameters();
    ArrayList<Object> args = new ArrayList<>();

    for (Parameter param : params) {
      Class<?> paramType = param.getType();
      if (paramType == HttpSession.class) {
        args.add(req.getSession());
      } else if (paramType == Map.class) {
        args.add(requestAttributesMap);
      } else if (paramType == int.class || paramType == int[].class) {
        RequestParam paramAnno = param.getAnnotation(RequestParam.class);
        args.add(getDefaultTypeValueFromRequestParameter(req, paramType, paramAnno.value()));
      } else {
        args.add(createDomainObject(req, paramType));
      }
    }
    return args.toArray();
  }

  private Object getDefaultTypeValueFromRequestParameter(
      HttpServletRequest req,
      Class<?> paramType,
      String paramName) {

    String paramValue = req.getParameter(paramName);
    if (paramType != int[].class && paramValue == null) {
      return null;
    }

    if (paramType == int.class) {
      return Integer.parseInt(paramValue);
    } else if (paramType == java.sql.Date.class) {
      return java.sql.Date.valueOf(paramValue);
    } else if (paramType == int[].class) {
      String[] paramValues = req.getParameterValues(paramName);
      if (paramValues == null) {
        return new int[0];
      }
      int[] values = new int[paramValues.length];
      for (int i = 0; i < paramValues.length; i++) {
        values[i] = Integer.parseInt(paramValues[i]);
      }
      return values;
    } else {
      return paramValue;
    }
  }

  private Object createDomainObject(HttpServletRequest req, Class<?> paramType) throws Exception {
    Object domainObject = paramType.getConstructor().newInstance();

    Method[] methods = paramType.getDeclaredMethods();
    for (Method m : methods) {
      if (!(Modifier.isPublic(m.getModifiers()) && m.getName().startsWith("set"))) {
        continue;
      }

      String propertyName = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);
      Class<?> propertyType = m.getParameterTypes()[0];

      Object value = getDefaultTypeValueFromRequestParameter(req, propertyType, propertyName);
      if (value == null) {
        continue;
      }
      m.invoke(domainObject, value);
    }
    return domainObject;
  }
}

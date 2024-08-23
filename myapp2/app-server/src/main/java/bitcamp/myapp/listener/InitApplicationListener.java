package bitcamp.myapp.listener;


import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.HistoryCommand;
import bitcamp.myapp.command.board.*;
import bitcamp.myapp.command.project.*;
import bitcamp.myapp.command.user.*;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.ProjectDaoImpl;
import bitcamp.myapp.dao.mysql.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class InitApplicationListener implements ApplicationListener {

  Connection con;

  @Override
  public boolean onStart(ApplicationContext ctx) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(inputStream);
    SqlSession sqlSession = factory.openSession(false);

    Properties props = new Properties();
    props.load(new FileReader("app.properties"));

    String url = props.getProperty("jdbc.url");
    String username = props.getProperty("jdbc.username");
    String password = props.getProperty("jdbc.password");

    // JDBC Connection 객체 준비
    // => DBMS에 연결
    con = DriverManager.getConnection(url, username, password);

    UserDao userDao = new UserDaoImpl(sqlSession);
    BoardDao boardDao = new BoardDaoImpl(null);
    ProjectDao projectDao = new ProjectDaoImpl(null);

    ctx.setAttribute("userDao", userDao);
    ctx.setAttribute("boardDao", boardDao);
    ctx.setAttribute("projectDao", projectDao);

    MenuGroup mainMenu = ctx.getMainMenu();

    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userDao, sqlSession)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userDao)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userDao)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userDao, sqlSession)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userDao, sqlSession)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userDao);
    projectMenu.add(
        new MenuItem("등록", new ProjectAddCommand(projectDao, memberHandler, sqlSession)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectDao)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectDao)));
    projectMenu.add(
        new MenuItem("변경", new ProjectUpdateCommand(projectDao, memberHandler, sqlSession)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectDao, sqlSession)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardDao, ctx, sqlSession)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardDao)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardDao, sqlSession)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardDao, ctx, sqlSession)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardDao, ctx, sqlSession)));
    mainMenu.add(boardMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));
    mainMenu.add(new MenuItem("명령내역", new HistoryCommand()));

    mainMenu.setExitMenuTitle("종료");

    return true;
  }

  @Override
  public void onShutdown(ApplicationContext ctx) throws Exception {
    try {
      con.close();
    } catch (Exception e) {
      // DBMS에 연결을 끊는 중에 오류가 발생하면 그냥 무시한다!
    }
  }
}

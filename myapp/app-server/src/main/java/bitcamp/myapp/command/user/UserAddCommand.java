package bitcamp.myapp.command.user;


import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import bitcamp.net.Prompt;

public class UserAddCommand implements Command {

  private UserDao userDao;
  private SqlSessionFactoryProxy sqlSessionFactoryProxy;

  public UserAddCommand(UserDao userDao, SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.userDao = userDao;
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);
      User user = new User();
      user.setName(prompt.input("이름?"));
      user.setEmail(prompt.input("이메일?"));
      user.setPassword(prompt.input("암호?"));
      user.setTel(prompt.input("연락처?"));

      userDao.insert(user);
      sqlSessionFactoryProxy.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactoryProxy.openSession(false).rollback();
      prompt.println("등록 중 오류 발생!");
      e.printStackTrace();
    }
  }
}

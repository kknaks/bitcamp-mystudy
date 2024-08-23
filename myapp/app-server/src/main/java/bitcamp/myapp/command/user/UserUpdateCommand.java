package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import bitcamp.net.Prompt;

public class UserUpdateCommand implements Command {
  private UserDao userDao;
  private SqlSessionFactoryProxy sqlSessionFactoryProxy;

  public UserUpdateCommand(UserDao userDao, SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.userDao = userDao;
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);
      int userNo = prompt.inputInt("회원번호?");

      User user = userDao.findBy(userNo);
      if (user == null) {
        prompt.println("없는 회원입니다.");
        return;
      }

      user.setName(prompt.input("이름(%s)?", user.getName()));
      user.setEmail(prompt.input("이메일(%s)?", user.getEmail()));
      user.setPassword(prompt.input("암호?"));
      user.setTel(prompt.input("연락처(%s)?", user.getTel()));

      userDao.update(user);
      sqlSessionFactoryProxy.openSession(false).commit();
      prompt.println("변경 했습니다.");

    } catch (Exception e) {
      sqlSessionFactoryProxy.openSession(false).rollback();

      prompt.println("변경 중 오류 발생!");
    }
  }

}

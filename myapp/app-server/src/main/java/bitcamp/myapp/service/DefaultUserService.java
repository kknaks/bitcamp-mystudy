package bitcamp.myapp.service;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

  private UserDao userDao;

  public DefaultUserService(UserDao userDao, SqlSessionFactory sqlSessionFactory) {
    this.userDao = userDao;
  }

  @Transactional
  public void add(User user) throws Exception {
    userDao.insert(user);
  }

  public List<User> list() throws Exception {
    return userDao.list();
  }

  public User get(int userNo) throws Exception {
    return userDao.findBy(userNo);
  }

  @Override
  public User exists(String email, String password) throws Exception {
    return userDao.findByEmailAndPassword(email, password);
  }

  @Transactional
  public boolean update(User user) throws Exception {
    if (userDao.update(user)) {
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public boolean delete(int userNo) throws Exception {
    if (userDao.delete(userNo)) {
      return true;
    } else {
      return false;
    }
  }
}

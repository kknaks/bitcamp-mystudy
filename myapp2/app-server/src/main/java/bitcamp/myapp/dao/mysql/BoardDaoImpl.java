package bitcamp.myapp.dao.mysql;


import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDaoImpl implements BoardDao {

  private SqlSession sqlSession;

  public BoardDaoImpl(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    int count = sqlSession.insert("BoardDao.insert", board);
    return count > 0;
  }

  @Override
  public List<Board> list() throws Exception {
    return sqlSession.selectList("BoardDao.list");
  }

  @Override
  public Board findBy(int no) throws Exception {
    return sqlSession.selectOne("BoardDao.findBy", no);
  }

  @Override
  public boolean update(Board board) throws Exception {
    int count = sqlSession.update("BoardDao.update", board);
    return count > 0;
  }

  @Override
  public boolean delete(int no) throws Exception {
    int count = sqlSession.delete("BoardDao.delete", no);
    return count > 0;
  }

  @Override
  public void updateViewCount(int boardNo, int count) throws Exception {
    Map<String, Object> value = new HashMap<>();
    value.put("boardNo", boardNo);
    value.put("count", count);
    sqlSession.update("BaordDao.updateViewCount", value);
  }
}

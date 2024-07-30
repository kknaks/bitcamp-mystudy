package bitcamp.myapp.dao.stub;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class BoardDaoStub implements BoardDao {
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private String dataName;

  public BoardDaoStub(ObjectOutputStream out, ObjectInputStream in, String dataName) {
    this.out = out;
    this.in = in;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    return false;
  }

  @Override
  public List<Board> list() throws Exception {
    return List.of();
  }

  @Override
  public Board findBy(int no) throws Exception {
    return null;
  }

  @Override
  public boolean update(Board board) throws Exception {
    return false;
  }

  @Override
  public boolean delete(int no) throws Exception {
    return false;
  }
}

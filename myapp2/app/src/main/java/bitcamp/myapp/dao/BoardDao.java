package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;

import java.util.List;

public interface BoardDao {
  void insert(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board findBy(int no) throws Exception;

  boolean update(Board project) throws Exception;

  boolean delete(int no) throws Exception;
}

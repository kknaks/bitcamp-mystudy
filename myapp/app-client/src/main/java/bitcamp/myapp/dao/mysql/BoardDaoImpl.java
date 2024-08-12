package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
  private Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    try (Statement stmt = con.createStatement()) {
      stmt.executeUpdate(
          String.format("insert into myapp_boards(title,content,view_count) values ('%s','%s',%d)",
              board.getTitle(), board.getContent(), board.getViewCount()));
      return true;
    }
  }

  @Override
  public List<Board> list() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_boards order by board_id asc")) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("title"));
        board.setCreatedDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));
        list.add(board);
      }

      return list;
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_boards where board_id = " + no)) {

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        board.setCreatedDate(formatter.parse(rs.getString("created_date")));
        board.setViewCount(rs.getInt("view_count"));
        update(board);

        return board;
      }
      return null;
    }
  }

  @Override
  public boolean update(Board board) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(String.format(
          "update myapp_boards set" + " title='%s'," + " content='%s'," + " view_count=(%d+1) where board_id=%d",
          board.getTitle(), board.getContent(), board.getViewCount(), board.getNo()));
      return count > 0;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count =
          stmt.executeUpdate(String.format("delete from myapp_users where board_id=%d", no));
      return count > 0;
    }
  }
}

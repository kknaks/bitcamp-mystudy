package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import bitcamp.net.Prompt;

public class BoardAddCommand implements Command {

  private BoardDao boardDao;
  private SqlSessionFactoryProxy sqlSessionFactoryProxy;

  public BoardAddCommand(BoardDao boardDao, SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.boardDao = boardDao;
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);
    try {
      Board board = new Board();
      board.setTitle(prompt.input("제목?"));
      board.setContent(prompt.input("내용?"));
      board.setWriter((User) prompt.getAttribute("loginUser"));

      boardDao.insert(board);
      sqlSessionFactoryProxy.openSession(false).commit();
    } catch (Exception e) {
      prompt.println("등록 중 오류 발생!");
      sqlSessionFactoryProxy.openSession(false).rollback();
      e.printStackTrace();
    }
  }

}

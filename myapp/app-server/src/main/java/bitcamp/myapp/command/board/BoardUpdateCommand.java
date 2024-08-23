package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import bitcamp.net.Prompt;

public class BoardUpdateCommand implements Command {

  private BoardDao boardDao;
  private SqlSessionFactoryProxy sqlSessionFactoryProxy;

  public BoardUpdateCommand(BoardDao boardDao, SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.boardDao = boardDao;
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {

    try {
      User loginUser = (User) prompt.getAttribute("loginUser");

      prompt.printf("[%s]\n", menuName);
      int boardNo = prompt.inputInt("게시글 번호?");

      Board board = boardDao.findBy(boardNo);
      if (board == null) {
        prompt.println("없는 게시글입니다.");
        return;
      } else if (loginUser.getNo() != 10 && board.getWriter().getNo() != loginUser.getNo()) {
        prompt.println("변경권한이 없습니다.");
        return;
      }

      board.setTitle(prompt.input("제목(%s)?", board.getTitle()));
      board.setContent(prompt.input("내용(%s)?", board.getContent()));

      boardDao.update(board);
      sqlSessionFactoryProxy.openSession(false).commit();
      prompt.println("변경 했습니다.");

    } catch (Exception e) {
      prompt.println("변경 중 오류 발생!");
      sqlSessionFactoryProxy.openSession(false).rollback();
      e.printStackTrace();
    }
  }
}

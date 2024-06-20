package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Board;

import java.util.Date;

public class BoardCommand {

  public static void excuteBoardCommand(String command) {
    System.out.printf("[%s]\n", command);
    int userNo = 0;
    switch (command) {
      case "등록":
        addBoard();
        break;
      case "목록":
        listBoard();
        break;
      case "조회":
        viewBoard();
        break;
      case "변경":
        updateBoard();
        break;
      case "삭제":
        deleteBoard();
        break;
    }
  }

  private static void addBoard() {
    Board board = new Board();
    board.setTitle(Prompt.input("제목?"));
    board.setContent(Prompt.input("내용?"));
    board.setCreatedDate(new Date());
    board.setNo(Board.getNextSeqNo());
    BoardList.add(board);
  }

  private static void listBoard() {
    System.out.println("번호 제목 작성일 조회수");
    for (Board board : BoardList.toArray()) {
      System.out.printf("%d %s %tY-%3$tm-%3$td %d\n", board.getNo(), board.getTitle(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

  private static void viewBoard() {
    int boardNo = Prompt.inputInt("게시글 번호");
    Board board = BoardList.findByNo(boardNo);
    if (board == null) {
      System.out.println("없는 게시글입니다.");
      return;
    }
    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("이름: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  private static void updateBoard() {
    int boardNo = Prompt.inputInt("게시글 번호");
    Board board = BoardList.findByNo(boardNo);
    if (board == null) {
      System.out.println("없는 게시글입니다.");
      return;
    }
    board.setViewCount(board.getViewCount() + 1);
    board.setTitle(Prompt.input("제목(%s)", board.getTitle()));
    board.setContent(Prompt.input("내용(%s)", board.getContent()));
    System.out.println("변경했습니다.");
  }

  private static void deleteBoard() {
    int boardNo = Prompt.inputInt("게시글 번호");
    Board deleteboard = BoardList.deleteBoard(boardNo);
    if (deleteboard != null) {
      System.out.printf("%d번 게시글을 삭제하였습니다.\n", deleteboard.getNo());
    } else {
      System.out.println("없는 게시글입니다.");
    }
  }
}

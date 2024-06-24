package bitcamp.myapp2.command;

import bitcamp.myapp2.vo.Board;

public class BoardList extends ArrayList {
  public Board findByNo(int boardNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board) get(i);
      if (board.getNo() == boardNo) {
        return board;
      }
    }
    return null;
  }
}

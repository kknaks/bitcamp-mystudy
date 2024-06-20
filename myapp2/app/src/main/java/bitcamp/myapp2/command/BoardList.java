package bitcamp.myapp2.command;

import bitcamp.myapp2.vo.Board;

public class BoardList {
  private static final int MAX_SIZE = 10;
  private static final Board[] boards = new Board[MAX_SIZE];
  private static int boardLength = 0;

  public static void add(Board board) {
    boards[boardLength++] = board;
  }

  public static Board delete(int boardNo) {
    Board deleteboard = findByNo(boardNo);
    if (deleteboard == null) {
      return null;
    }
    int index = indexOf(deleteboard);
    for (int i = index + 1; i < boardLength; i++) {
      boards[i - 1] = boards[i];
    }
    boards[--boardLength] = null;
    return deleteboard;
  }

  public static Board[] toArray() {
    Board[] arr = new Board[boardLength];
    System.arraycopy(boards, 0, arr, 0, boardLength);
    return arr;
  }

  static Board findByNo(int boardNo) {
    for (int i = 0; i < boardLength; i++) {
      Board board = boards[i];
      if (board.getNo() == boardNo) {
        return board;
      }
    }
    return null;
  }

  static int indexOf(Board board) {
    for (int i = 0; i < boardLength; i++) {
      if (boards[i] == board) {
        return i;
      }
    }
    return -1;
  }
}

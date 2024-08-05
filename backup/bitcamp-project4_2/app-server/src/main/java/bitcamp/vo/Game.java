package bitcamp.vo;

public class Game {
  private int size = 5;
  private int winCount = 5;
  private String[][] arr = new String[size][size];
  private char whiteStone = '\u26AA';
  private char blackStone = '\u26AB';
  private boolean gameOver = false;


  public Game(){
    String[][] array = new String[size][size];
    int start = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        arr[i][j] = String.format("%03d", start++);
      }
    }
  }
  public boolean isGameOver() {
    return gameOver;
  }

  public String[][] getArr(){
    String[][] temp = new String[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        temp[i][j] = arr[i][j];
      }
    }
    return temp;
  }

  public void put(int point, int player){
    int input_x = point / size;
    int input_y = point % size;
    String stone = String.valueOf(player == 1 ? whiteStone : blackStone);
    arr[input_x][input_y] = stone;

    if (bfs(input_x,input_y,stone)){
      gameOver = true;
    }
  }

  public boolean bfs(int x, int y, String stone) {
    int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
    for (int[] direction : directions) {
      int count = 1;
      count += countInDirection(x, y, direction[0], direction[1], stone);
      count += countInDirection(x, y, -direction[0], -direction[1], stone);
      if (count >= winCount) {
        return true;
      }
    }
    return false;
  }

  private int countInDirection(int x, int y, int dx, int dy, String stone) {
    int count = 0;
    int nx = x + dx;
    int ny = y + dy;
    while (nx >= 0 && nx < size && ny >= 0 && ny < size && arr[nx][ny].equals(stone)) {
      count++;
      nx += dx;
      ny += dy;
    }
    return count;
  }
}

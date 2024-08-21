package study.lang.variable;

import java.util.Scanner;

public class Test03 {
  public static void main(String[] args) {
    // static String[][] subMenus = new String[][]{
    // new String[]{ "등록", "목록", "조회", "변경", "삭제"},
    // new String[]{ "등록", "목록", "조회", "변경", "삭제"},
    // new String[]{ "등록", "목록", "조회", "변경", "삭제"},
    // new String[]{ "등록", "목록", "조회", "변경", "삭제"}
    // };
     int[][] arr = new int[3][4];
     arr[0] = new int {11,21};
     arr[1] = new int {21,22,23};
     arr[2] = new int {31,32,33,34};

     System.out.println(arr[0].length);

    Scanner key = new Scanner(System.in);
    int a = key.nextInt();
    int[] arr = new int[a];
    System.out.println(arr.length);
  }
}

/*
 * This source file was generated by the Gradle 'init' task
 */
package bitcamp.myapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //ANSI 코드 입력
        String resetAnsi = "\033[0m";   // Text Reset
        String redAnsi = "\033[31m"; // Red Bold
        String boldAnsi = "\033[1m";

        //초기화면 변수할당
        String line = "--------------------------";
        String appTitle = "[팀 프로젝트 관리 시스템]";
        String menu1 = "1. 회원";
        String menu2 = "2. 팀";
        String menu3 = "3. 프로젝트";
        String menu4 = "4. 게시판" ;
        String menu5 = "5. 도움말";
        String menu6 = "6. 종료";

        //초기화면 출력
        System.out.println(boldAnsi + line + resetAnsi);
        System.out.println(boldAnsi + appTitle + resetAnsi);
        System.out.println();
        System.out.println(menu1);
        System.out.println(menu2);
        System.out.println(menu3);
        System.out.println(menu4);
        System.out.println(menu5);
        System.out.println(redAnsi + boldAnsi + menu6 + resetAnsi);
        System.out.println(boldAnsi + line + resetAnsi);


        java.io.InputStream keyboard = System.in;
        java.util.Scanner keyboardScanner = new java.util.Scanner(keyboard);

        int menuNo;
        do {
            System.out.print("> ");
            menuNo = keyboardScanner.nextInt();
            switch (menuNo){
                case 1:
                    System.out.println("회원");
                    break;
                case 2:
                    System.out.println("팀");
                    break;
                case 3:
                    System.out.println("프로젝트");
                    break;
                case 4:
                    System.out.println("게시판");
                    break;
                case 5:
                    System.out.println("도움말");
                    break;
                case 6:
                    System.out.println("종료합니다.");
                    keyboardScanner.close();
                    break;

                default:
                    System.out.println("메뉴 번호가 옳지 않습니다.");
            }
        } while (menuNo != 6);
    }
}


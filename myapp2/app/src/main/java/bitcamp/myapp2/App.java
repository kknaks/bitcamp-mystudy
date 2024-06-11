package bitcamp.myapp2;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //글자색,굵기 설정
        String redAnsi = "\033[31m";
        String boldAnsi = "\033[1m";
        String resetAnsi = "\033[0m";

        //메뉴초기 설정
        String appTitle = "[팀프로젝트 관리 시스템]";
        String line = "---------------------------";
        String[] menus = new String[]{
                "회원",
                "팀",
                "프로젝트",
                "게시판",
                "도움말",
                "종료",
        };

        //메뉴화면 출력
        System.out.printf("%s%s%s\n",boldAnsi,line,resetAnsi);
        System.out.printf("%s%s%s\n",boldAnsi,appTitle,resetAnsi);
        for (String menu : menus){
            if (menu.equals("종료")) {
                System.out.printf("%s%s%s\n",(boldAnsi+redAnsi),menu,resetAnsi);
            }else {
                System.out.printf("%s\n",menu);
            }
        }
        System.out.printf("%s%s%s\n",boldAnsi,line,resetAnsi);

        //메뉴선택하기
        Scanner keyboard = new Scanner(System.in);
        int menuNo;

        while (true){
            System.out.print("> ");
            try{
                menuNo = keyboard.nextInt();
                if (menuNo >= 1 && menuNo <= menus.length){
                    if (menus[menuNo - 1].equals("종료")){
                        break;
                    }
                    System.out.printf("%d. %s\n",menuNo, menus[menuNo -1]);
                } else {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                }
            } catch(InputMismatchException ex){
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
                keyboard.next();
            }
        }
        System.out.println("종료합니다");
        keyboard.close();
    }
}

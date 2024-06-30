package bitcamp.myAccount;

import bitcamp.myAccount.util.Prompt1;

public class App {
    static String[] mainMenus = new String[] {"수입입력", "지출입력", "통계", "종료"};
    static String[][] subMenus = {{"등록", "목록", "조회", "변경", "삭제"}, {"등록", "목록", "조회", "변경", "삭제"},
        {}};

    public static void main(String[] args) {
        String command;
        printMainMenu();
        while (true){
            try {
                command = Prompt.input("메인> ");
                if (command.equals("menu")) {
                    printMainMenu();
                    continue;
                }
                int menuNo = Integer.parseInt(command);
                String menuTitle = getMenuTitle(menuNo, mainMenus);
                if (menuTitle == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                } else if (menuTitle.equals("종료")) {
                    break;
                } else {
                    processSubMenu(menuTitle, subMenus[menuNo - 1]);
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
        System.out.println("종료합니다.");
        Prompt.close();
    }

    static void printMainMenu(){
        String boldAnsi = "\033[1m";
        String redAnsi = "\033[31m";
        String resetAnsi = "\033[0m";
        String appTitle = "        [가계부 프로그램]";
        String line = "----------------------------------";
        System.out.println(boldAnsi + line + resetAnsi);
        System.out.println(boldAnsi + appTitle + resetAnsi);
        for (int i = 0; i < mainMenus.length; i++) {
            if (mainMenus[i].equals("종료")) {
                System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), mainMenus[i], resetAnsi);
            } else {
                System.out.printf("%d. %s\n", (i + 1), mainMenus[i]);
            }
        }
        System.out.println(boldAnsi + line + resetAnsi);
    }

    static void printSubMenu(String menuTitle, String[] menus) {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", i + 1, menus[i]);
        }
        System.out.println("9. 이전");
    }

    static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
}

    static void processSubMenu(String menuTitle, String[] menus) {
        printSubMenu(menuTitle, menus);
        while (true) {
            String command = Prompt.input(String.format("메인/%s> ", menuTitle));
            if (command.equals("menu")) {
                printSubMenu(menuTitle, menus);
                continue;
            } else if (command.equals("9")) {
                break;
            }
            try {
                int subMenuNo = Integer.parseInt(command);
                String subMenuTitle = getMenuTitle(subMenuNo, menus);
                if (subMenuTitle == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                } else {
                    switch (menuTitle) {
                        case "수입입력":
                            System.out.println(subMenuTitle);
                            break;
                        case "지출입력":
                            System.out.println(subMenuTitle);
                            break;
                        case "통계":
                            System.out.println(subMenuTitle);
                            break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }
}

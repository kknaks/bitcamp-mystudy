package bitcamp.myapp2;
import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static class User{
        String name;
        String email;
        String password;
        String tel;
    }
    static List<User> userList = new ArrayList<>();

    static String[] mainMenus = {"회원","팀","프로젝트","게시판","도움말","종료"};
    static String[][] subMenus = {
            { "등록", "목록", "조회", "변경", "삭제"},
            { "등록", "목록", "조회", "변경", "삭제"},
            { "등록", "목록", "조회", "변경", "삭제"},
            { "등록", "목록", "조회", "변경", "삭제"}
    };

    static Scanner keyboardScanner = new Scanner(System.in);
    public static void main(String[] args) {
        printMainMenu();
        String command;
        while (true){
            try {
                command = prompt("메인");
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
                } else if ((menuTitle.equals("회원"))){
                    if (menuNo >= 1 && menuNo <= 4){
                        processUserMenu(menuTitle, menuNo);
                    } else {
                        System.out.println(menuTitle);
                    }
                }else {
                    if (menuNo >= 1 && menuNo <= 4){
                        processSubMenu(menuTitle, menuNo);
                    } else {
                        System.out.println(menuTitle);
                    }
                }
            }catch (NumberFormatException ex){
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
        System.out.println("종료합니다.");
        keyboardScanner.close();
    }
    static void printMainMenu(){
        //글자 양식
        String redAnsi = "\033[31m";
        String boldAnsi = "\033[1m";
        String resetAnsi = "\033[0m";

        //초기화면 출력
        String title = "팀 프로젝트 관리 시스템";
        String line = "---------------------------";

        System.out.printf("%s%s%s\n",boldAnsi,line,resetAnsi);
        System.out.printf("%s%s%s\n",boldAnsi,title,resetAnsi);
        for (int i = 0; i < mainMenus.length; i++){
            if (mainMenus[i].equals("종료")){
                System.out.printf("%d. %s%s%s\n",i + 1,(boldAnsi+redAnsi),mainMenus[i],resetAnsi);
            } else{
                System.out.printf("%d. %s\n",i + 1,mainMenus[i]);
            }
        }
        System.out.printf("%s%s%s\n",boldAnsi,line,resetAnsi);
    }
    static void printSubMenu(String menuTitle, int menuNo){
        System.out.printf("[%s]\n",menuTitle);
        for (int i = 0; i < subMenus[menuNo-1].length; i++) {
            System.out.printf("%d. %s\n",i+1,subMenus[menuNo-1][i]);
        }
        System.out.println("9. 이전");
    }
    static String prompt(String title){
        System.out.printf("%s> ",title);
        return keyboardScanner.nextLine();
    }
    static boolean isValidateMenu(int menuNo, String[] menus){
        return menuNo >= 1 && menuNo <= menus.length;
    }
    static String getMenuTitle(int menuNo,String[] menus){
        return isValidateMenu(menuNo,menus)? menus[menuNo-1] : null;
    }
    static void processSubMenu(String menuTitle, int menuNo){
        printSubMenu(menuTitle, menuNo);
        while (true) {
            try {
                String command = prompt("메인/" + menuTitle);
                if (command.equals("menu")) {
                    printSubMenu(menuTitle, menuNo);
                    continue;
                } else if (command.equals("9")) {
                    break;
                }
                int subMenuNo = Integer.parseInt(command);
                String subMenuTilte = getMenuTitle(subMenuNo, subMenus[menuNo - 1]);
                if (subMenuTilte == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                } else {
                        System.out.printf("%s\n",subMenuTilte);
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }
    static void processUserMenu(String menuTitle, int menuNo){
        printSubMenu(menuTitle, menuNo);
        while (true) {
            try {
                String command = prompt("메인/" + menuTitle);
                if (command.equals("menu")) {
                    printSubMenu(menuTitle, menuNo);
                    continue;
                } else if (command.equals("9")) {
                    break;
                }
                int subMenuNo = Integer.parseInt(command);
                String subMenuTilte = getMenuTitle(subMenuNo, subMenus[menuNo - 1]);
                if (subMenuTilte == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                } else {
                    switch (subMenuTilte) {
                        case "등록" : addUser(); break;
                        case "목록" : showUser(); break;
                        case "조회" : searchUser(); break;
                        case "변경" : changeUser(); break;
                        case "삭제" : deleteUser(); break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }
    static void addUser(){
        System.out.println("[등록]");
        User user = new User();
        System.out.print("이름: ");
        user.name = keyboardScanner.nextLine();
        System.out.print("이메일: ");
        user.email = keyboardScanner.nextLine();
        System.out.print("암호: ");
        user.password = keyboardScanner.nextLine();
        System.out.print("전화: ");
        user.tel = keyboardScanner.nextLine();
        userList.add(user);
    }

    static void showUser(){
        System.out.println("[목록]");
        System.out.println("번호 이름 이메일");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("%d %s %s\n",i+1,userList.get(i).name,userList.get(i).email);
        }
    }

    static void searchUser() {
        System.out.println("[조회]");
        while (true) {
            try {
                System.out.print("회원번호: ");
                int num = Integer.parseInt(keyboardScanner.nextLine()) - 1;
                if (num >= 0 && num < userList.size() && userList.get(num).name != null) {
                    System.out.println(userList.get(num).name);
                    System.out.println(userList.get(num).email);
                    System.out.println(userList.get(num).tel);
                    break;
                } else {
                    System.out.println("없는 회원입니다.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("없는 회원입니다.");
            }
        }
    }

    static void changeUser() {
        System.out.println("[변경]");
        while (true) {
            try {
                System.out.print("회원번호: ");
                int num = Integer.parseInt(keyboardScanner.nextLine()) - 1;
                if (num >= 0 && num < userList.size() && userList.get(num).name != null) {
                    System.out.print("이름: ");
                    userList.get(num).name = keyboardScanner.nextLine();
                    System.out.print("이메일: ");
                    userList.get(num).email = keyboardScanner.nextLine();
                    System.out.print("암호: ");
                    userList.get(num).password = keyboardScanner.nextLine();
                    System.out.print("전화: ");
                    userList.get(num).tel = keyboardScanner.nextLine();
                    break;
                } else {
                    System.out.println("없는 회원입니다.");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("없는 회원입니다.");
            }
        }
    }

    static void deleteUser() {
        System.out.println("[삭제]");
        while (true) {
            try {
                System.out.print("회원번호: ");
                int num = Integer.parseInt(keyboardScanner.nextLine()) - 1;
                if (num >= 0 && num < userList.size() && userList.get(num).name != null) {
                    userList.get(num).name = null;
                    userList.get(num).email = null;
                    userList.get(num).password = null;
                    userList.get(num).tel = null;
                    System.out.println("회원 정보를 삭제했습니다.");
                    break;
                } else {
                    System.out.println("없는 회원입니다.");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("없는 회원입니다.");
            }
        }
    }
}

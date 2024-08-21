package study.lang;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

public class Test {
  public static void main(String[] args) {
    System.out.print(
        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMMMMMMMMMMWXK00OOO00KXNWMMMMMMMMMMMMMMMMMMWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMMMMMMWKOxdolllllllllodxkOXWMMMMMMWXK0Okkxxxxxxxk0XWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMMMMXOdllooooooooooooooollldkKNXOxdolllloooooooollod0WMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMWXklloooooooooooooooooooooolllcloooooooooooooooooooldKMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMMMMMMMNklloooooooooooollllllloooooool:looooooooooooooooooooloKMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMMWKdlooooooooolccccccclccclcccclool:looooooooooooooooooooldXMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMMW0llooooooolccclloooodooooollcccccc::cccccccclllllcccccccccxXWMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMMWOllooooooollloooooolcccccccccccccc:,,;:cllllooooooooolllllcldxxOKWMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMWOlloooooooooooooolccccloollccccccccllcclccccoooooooolcccccccccc:::dKWMMMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMMMMMKolooooooooooooolc:clolclodxkOOOxoccloddolc::coolcccc:::::::c:::;;:cldONMMMMMMMMMMMMMMMMMMMM\n"
            + "MMMNKOxlloooooooooooool:col:cdOXWMMMMMWx. ..':d0KOx;':c:::lddxkkOkl;,,,,:loc;:kWMMMMMMMMMMMMMMMMMMM\n"
            + "MMNOdllc:looooooooooool:loclkXMMMMMWWWMMNl      'kNWXl,:lx0NWMMMMMWx.    .,oOkloXMMMMMMMMMMMMMMMMMMM\n"
            + "MXdloooccooooooooooool:cl:dNMMMMMMWx;cxXWX:      .cOMNolXMMMMMMX0NMWx.      :XNkkWMMMMMMMMMMMMMMMMMM\n"
            + "Koloooocloooooooooooc:locdNMMMMMMMN:   .:do.     .'xMWOkWMMMMMWo.,lOKc      .kMKkXMMMMMMMMMMMMMMMMMM\n"
            + "olooooooooooooooooo:;clc:oKWMMMMMMWd.            ,o0MKd0MMMMMMMO.   '.      '0M0kNMMMMMMMMMMMMMMMMMM\n"
            + "loooooooooooooooooolcclooccd0NMMMMMNd.          :0WW0cc0WMMMMMMWx.         ;OWKx0MMMMMMMMMMMMMMMMMMM\n"
            + "oooooooooooooooooooooooooolloxOKNWWWKo;.....,cdOxoo:clldxOXNWMMWKd:''.';lOXKOkKMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooooooooooooolclodxxxkkdlcclooc,,;cooooocclodxxxkkkkxxkkxxoo0WMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooooooooooooooooooolllllllllc::cooooooooooooollccccccc::coONMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooooooooooooooooooooooooolccclooooooooooooooooooooool:lkKWMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooooooooooooooooooooooooolloooooooooooooooooooooooolccld0NMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooolldXWMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooloKMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooolloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooolxWMMMMMMMMMMMMMMMMMMMMMM\n"
            + "oooooooooooooccooooooooooooooooooooooloooooooooooooooooooooooooooooooooooooloXMMMMMXdoOWMWOdkNMMXdlk\n"
            + "oooooooooooooccoooooooooooooooooooooclolcccccccccccccccccccclllllloooooooooloKMMMMWk..cNMK; '0MMO'.c\n"
            + "oooooooooooooolcloooooooooooooooooooc;:::cc:ccccc::::::::::::::::::cc:cooooocxNMMMMMWK0NMMWKk0WMMWX0N\n"
            + "oooooooooooooollooooooooooooooooooo;,cc::::cccccccccccccccllllllllllc;:ooocdXMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooolloooooooooooooooooo::lc:::cc:::::::::::::::::::c:;;:l:;olcxNMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "oooooooooooooooolloooooooooooooooool:::cllllllllllllllllllllllllollc::cco0WMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooloooooooooooooooooolcccc:::cccccccccccccccccccc:::cldONMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooocokKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "cclooooooooooooooooooooooooooooooooooooooooooooolcccccloooooooooccxXMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + ",;:ccccllooooooooooooooooooooooooooooooooooooooooolloooooooollloxKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "'',,;;:cccccccllllloooooooooooooooooooooooooooooooooooolllodkOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + ",''''',;:cccllcccccccccccccccccllllllllllllllcccccc:;cxOKXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "''''''''',,;:::ccccccccclllcccccccllcccccccc::;;,'.,lkKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "'''''''''''''''''''',,,,,,;;;;;;;;;;;;;;;;,,,''''''''';lxKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"
            + "''''''''''''''''''''',,,,,,'''''''''''''''''''''''''''''';kNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String[] dateStrings =
        {"1111-02-03", "1111-02-04", "1111-02-05", "1111-02-04", "1111-02-03", "1111-02-06"};
    LocalDate[] dates = new LocalDate[dateStrings.length];
    HashSet<LocalDate> set = new HashSet<>();
    for (int i = 0; i < dateStrings.length; i++) {
      dates[i] = LocalDate.parse(dateStrings[i], formatter);
      set.add(dates[i]);
    }

    // 배열 출력
    for (LocalDate date : dates) {
      System.out.println(date);
    }

    System.out.println("------");
    Object[] arr = set.toArray();
    Arrays.sort(arr);

    for (Object obj : arr) {
      LocalDate date = (LocalDate) obj;
      System.out.println(date);
    }

    System.out.printf("총 수입 : %d원 ", 500);
    for (int i = 0; i < 5; i++) {
      System.out.print("\u25AE" + " ");
    }

    System.out.println();

    System.out.printf("총 지출 : %d원 ", 800);
    for (int i = 0; i < 8; i++) {
      System.out.print("\u25A0");
    }
    System.out.println("");
  }
}

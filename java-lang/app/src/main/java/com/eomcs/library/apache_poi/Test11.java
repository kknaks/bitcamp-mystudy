package study.library.apache_poi;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test11 {
  public static void main(String[] args) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("user");

    List<Object[]> data = new ArrayList<>();
    data.add(new Object[] {"no", "name", "email", "password", "tel"});
    data.add(new Object[] {"1", "user1", "user1@test.com", "1111", "010-1111-0001"});
    data.add(new Object[] {"2", "user2", "user2@test.com", "2222", "010-1111-0002"});
    data.add(new Object[] {"3", "user3", "user3@test.com", "3333", "010-1111-0003"});
    data.add(new Object[] {"4", "user4", "user4@test.com", "4444", "010-1111-0004"});
    data.add(new Object[] {"5", "user5", "user5@test.com", "5555", "010-1111-0005"});

    for (int i = 0; i < data.size(); i++) {
      Object[] values = data.get(i);
      Row row = sheet.createRow(i);
      for (int j = 0; j < values.length; j++) {
        row.createCell(j).setCellValue((String) values[j]);
      }
    }


    try (FileOutputStream out = new FileOutputStream("./temp/test.xlsx")) {
      workbook.write(out);

    }
    System.out.println("done------log");
  }
}

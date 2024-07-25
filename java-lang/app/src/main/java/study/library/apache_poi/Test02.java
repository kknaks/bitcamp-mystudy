package study.library.apache_poi;

import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test02 {
  public static void main(String[] args) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    workbook.createSheet("users");

    try (FileOutputStream out = new FileOutputStream("./temp/test.xlsx")) {
      workbook.write(out);

    }
    System.out.println("done------log");
  }
}

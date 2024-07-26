package study.library.apache_poi;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test03 {
  public static void main(String[] args) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook("temp/test.xlsx");
    XSSFSheet sheet = workbook.getSheet("user");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);
      Cell cell = row.getCell(i);
      System.out.printf("%s %s %s %s %s \n", row.getCell(0).getNumericCellValue(),
          row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue(),
          row.getCell(3).getNumericCellValue(), row.getCell(4).getNumericCellValue());
    }
    try (FileOutputStream out = new FileOutputStream("./temp/test.xlsx")) {
      workbook.write(out);
    }
    System.out.println("done------log");
  }
}

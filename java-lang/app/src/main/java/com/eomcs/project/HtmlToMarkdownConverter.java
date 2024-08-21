package study.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jsoup.Jsoup;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

public class HtmlToMarkdownConverter {

  public static void main(String[] args) throws IOException {
    // HTML 파일 경로
    String htmlFilePath = "your_html_file_path.html";
    String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

    // Jsoup을 사용하여 HTML 파싱
    Document doc = Jsoup.parse(htmlContent);

    // CSS, JS, 광고 스크립트 등 불필요한 태그 제거
    doc.select("script, style, iframe, noscript, link, meta").remove();

    // 본문 내용을 선택하고 마크다운으로 변환
    Element bodyElement = doc.body();
    String cleanedHtml = bodyElement.html();

    // Flexmark로 HTML을 마크다운으로 변환
    String markdown = FlexmarkHtmlConverter.builder().build().convert(cleanedHtml);

    // 마크다운 파일로 저장
    String markdownFilePath = "output.md";
    Files.write(Paths.get(markdownFilePath), markdown.getBytes());

    System.out.println("HTML이 마크다운으로 변환되었습니다: " + markdownFilePath);
  }
}

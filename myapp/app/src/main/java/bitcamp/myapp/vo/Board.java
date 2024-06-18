package bitcamp.myapp.vo;

import java.time.LocalDate;

public class Board {
  private String title;
  private String content;
  private LocalDate wirtingDate;
  private int viewCount;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDate getWirtingDate() {
    return wirtingDate;
  }

  public void setWirtingDate(LocalDate wirtingDate) {
    this.wirtingDate = wirtingDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
}

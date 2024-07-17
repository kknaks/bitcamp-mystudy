package bitcamp.myapp.menu;

import java.util.Objects;

public abstract class AbstractMenu implements Menu {
  protected String title;

  public AbstractMenu(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (!(object instanceof AbstractMenu that))
      return false;
    return Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(title);
  }

  @Override
  public String getTitle() {
    return title;
  }
}

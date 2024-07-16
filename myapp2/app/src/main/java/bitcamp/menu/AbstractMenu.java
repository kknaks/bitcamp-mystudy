package bitcamp.menu;

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
    if (object == null || getClass() != object.getClass())
      return false;
    AbstractMenu that = (AbstractMenu) object;
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

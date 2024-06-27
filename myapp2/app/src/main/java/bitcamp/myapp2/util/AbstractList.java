package bitcamp.myapp2.util;

public abstract class AbstractList implements List {
  protected int size;

  @Override
  public int size() {
    return size;
  }
}

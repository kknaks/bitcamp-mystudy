package study.oop.nested;

public class Test01 {
  interface Printer {
    public abstract void print();
  }

  public static void main(String[] args) {

    Printer obj3;
    (obj3 = () -> {
      System.out.println("Hello");
    }).print();
    obj3.print();

    new Printer() {
      @Override
      public void print() {
        System.out.println("Hello");
      }
    }.print();


    Printer obj2 = new Printer() {
      @Override
      public void print() {
        System.out.println("Hello");
      }
    };
    obj2.print();

    class PrinterImpl implements Printer {
      @Override
      public void print() {
        System.out.println("hello");
      }
    }
    Printer obj1 = new PrinterImpl();
    obj1.print();



  }
  // new Printer() {
  // @Override
  // public void print() {
  // System.out.println("Hello");
  // }
  // }.print();
  //
  //
  // Printer obj2 = new Printer() {
  // @Override
  // public void print() {
  // System.out.println("Hello");
  // }
  // };
  // obj2.print();
  //
  // class PrinterImpl implements Printer {
  // @Override
  // public void print() {
  // System.out.println("Hello");
  // }
  // }
  //
  // Printer obj1 = new PrinterImpl();
  // obj1.print();
  // }

}

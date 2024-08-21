package study.lang.variable;

public class Test01 {
  public static void main(String[] args) {
    // 정수부
    byte b1 = Byte.MAX_VALUE;
    byte b2 = Byte.MIN_VALUE;
    System.out.println("--- byte ----");
    System.out.println("max : " + b1 + ", min : " + b2);
    System.out.println("overflow : " + ++b1 + ", underflow : " + --b2);

    System.out.println("--- short ----");
    short s1 = Short.MAX_VALUE;
    short s2 = Short.MIN_VALUE;
    System.out.println("max : " + s1 + ", min : " + s2);
    System.out.println("overflow : " + ++s1 + ", underflow : " + --s2);

    System.out.println("--- int ----");
    int i1 = Integer.MAX_VALUE;
    int i2 = Integer.MIN_VALUE;
    System.out.println("max : " + i1 + ", min : " + i2);
    System.out.println("overflow : " + ++i1 + ", underflow : " + --i2);

    System.out.println("--- long ----");
    long l1 = Long.MAX_VALUE;
    long l2 = Long.MIN_VALUE;
    System.out.println("max : " + l1 + ", min : " + l2);
    System.out.println("overflow : " + ++l1 + ", underflow : " + --l2);

    System.out.println("--- float ----");
    float f1 = Float.MAX_VALUE;
    float f2 = Float.MIN_VALUE;
    System.out.println("max : " + f1 + ", min : " + f2);
    System.out.println("overflow : " + ++f1 + ", underflow : " + --f2);

    System.out.println("--- double ----");
    double d1 = Double.MAX_VALUE;
    double d2 = Double.MIN_VALUE;
    System.out.println("max : " + d1 + ", min : " + d2);
    System.out.println("overflow : " + ++d1 + ", underflow : " + --d2);

    System.out.println("--- character ----");
    char c1 = Character.MAX_VALUE;
    char c2 = Character.MIN_VALUE;
    System.out.println("max : " + (int) c1 + ", min : " + (int) c2);
    System.out.println("overflow : " + (int) ++c1 + ", underflow : " + (int) --c2);

  }
}

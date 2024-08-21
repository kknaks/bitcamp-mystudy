package study.lang.literal;

public class Test02 {
    public static void main(String[] args) {
        int x = 100;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toOctalString(x));
        System.out.println(Integer.toHexString(x));
        System.out.println(x);

        System.out.println(x);
        System.out.println(0b01100100);
        System.out.println(0144);
        System.out.println(0x64);
    }
}

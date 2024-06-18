package study.oop.clazz;

public class Calculator {
  // static int result = 0;
  int result = 0;

  void plus(int a) {
    this.result += a;
  }

  void minus(int a) {
    this.result -= a;
  }

  void multiple(int a) {
    this.result *= a;
  }

  void divide(int a) {
    this.result /= a;
  }

  int getResult() {
    return this.result;
  }

  void clear() {
    this.result = 0;
  }
}

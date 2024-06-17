package study.oop.clazz.vo;

public class Score {
  private String name;
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float aver;

  public Score() {
    this.name = null;
    this.kor = 0;
    this.eng = 0;
    this.math = 0;
  }

  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  private void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setKor(int kor) {
    this.kor = kor;
  }

  public void setEng(int eng) {
    this.eng = eng;
  }

  public void setMath(int math) {
    this.math = math;
  }

  public String getName() {
    return this.name;
  }

  public int getKor() {
    return this.kor;
  }

  public int getEng() {
    return this.eng;
  }

  public int getMath() {
    return this.math;
  }

  public int getSum() {
    return this.sum;
  }

  public float getAver() {
    return this.aver;
  }
}

# 파일 입출력
<div style="margin-left: 20px;">
<h2>1.java.io.File 클래스</h2>
<div style="margin-left: 10px;">
<li> File클래스는 디렉토리나 파일을 다룰 때 사용하는 클래스이다.</li>
<h3> 현재디렉토리 조회</h3>
<div style="margin-left: 10px;">
<ul>
<li> 현재디렉토리 조회</li>

```java
public class Test{
  public static void main(String[] agrs){
    File currentDir = new File("./src/main/jave");
    System.out.printf("폴더명: %s\n", currentDir.getName());
    System.out.printf("경로: %s\n", currentDir.getPath());
    System.out.printf("절대경로: %s\n", currentDir.getAbsolutePath());
    System.out.printf("계산된 절대경로: %s\n", currentDir.getCanonicalPath());

    //디렉토리에 전체 크기를 나타낸다.
    // 없는 디렉토리의 경우 0
    System.out.printf("총크기: %d\n", currentDir.getTotalSpace());
    System.out.printf("남은크기: %d\n", currentDir.getFreeSpace());
    System.out.printf("가용크기: %d\n", currentDir.getUsableSpace());
    
    
    // 디렉토리(폴더), 파일 등의 설정을 true/false로 반환함
    // 없는 디렉토리/파일일 경우 false를 return
    System.out.printf("디렉토리여부: %b\n", currentDir.isDirectory());
    System.out.printf("파일여부: %b\n", currentDir.isFile());
    System.out.printf("감춤폴더: %b\n", currentDir.isHidden());
    System.out.printf("존재여부: %b\n", currentDir.exists());
    System.out.printf("실행가능여부: %b\n", currentDir.canExecute());
  }
}
```
</ul>
</div>
<h3> 생성 및 삭제</h3>
<div style="margin-left: 10px;">
<ul>
<li> 디렉토리 생성 및 삭제</li>

```java
public class Test{
  public static void main(String[] agrs){
    File dir = new File("temp2");
    if(dir.mkdir()) { /* 디렉토리 생성 : 지정된 경로가 존재해야함 */ }
    else if (dir.mkdirs()){ /*디렉토리 생성 : 지정된 경로가 없으면 경로를 만들고 생성 */}
    else { /* false */ }
    
    if (dir.delete()){ /* 디렉토리 삭제 */ }
  }
}
```
<li> 파일 생성은 createNewFile() 메서드를 사용한다.</li>
<li> 파일 생성시 이미 존재하는 파일이면 false를 반환한다.</li>
<li> 파일 생성경로에 디렉토리가 없다면 false를 반환한다.</li>
<li> 파일 삭제는은 delete() 메서드를 사용한다.</li>

```java
public class Test{
  public static void main(String[] agrs){
    File file = new File("temp2/b/test.txt");
    
    File dir = file.getParentFile();
    // 디렉토리가 없으면 먼저 생성
    dir.mkdirs();
    
    // 이후 파일을 생성
    file.createNewFile();
    
    //이후 파일을 지우기
    file.delete();
  }
}
```
</ul>

</div>
<h3> 디렉토리 조회하기 </h3>
<div style="margin-left: 10px">
<ul>
<li> 현재 폴더의 디렉토리 탐색은 생성자에 "."를 추가한다.</li>

```java
public class Test{
  public static void main(Stirng[] args){
    File dir = new File(".");
    
    //현재 디렉토리 정보 전체 출력
    File[] files = dir.listFiles();
    for (File file : files) {
      System.out.printf("%s   %s %12d %s\n", file.isDirectory() ? "d" : "-", 
          new Date(file.lastModified()), file.length(), file.getName());
    }
  }
}

```
<li> 필터를 적용할 수 있다.</li>

```java
public class Test {
  public static void main(Stirng[] args) {
    File dir = new File(".");
    //
    //    class JavaFilter implements FilenameFilter{
    //      public boolean accet(File file){
    //        return file.getName().endsWith(".java") && file.isfile();
    //      }
    //    }
    //현재 디렉토리 정보 전체 출력
    File[] files = dir.listFiles(file -> file.getName().endWith(".java") && file.isFile());

    for (File file : files) {
      System.out.printf("%s %12d %s\n", file.isDirectory() ? "d" : "-", file.length(), file.getName());
    }
  }
}
```
</ul>
</div>
</div>

<h2>2.Byte Stream</h2>
<div style="margin-left: 10px;">
<h3> FileOutputStream </h3>
<div style="margin-left: 10px">
<ul>
<li> 객체를 인스턴스 할 때 설정한 파일명으로 파일이 만들어진다.</li>

```java
FileOutputStream 객체명 = new FileOutputStream("파일명");
```
<li> write(int): 1바이트를 출력한다.</li>
<li> write(byte[]) : 1바이트 배열을 출력한다.</li>
<li> write(byte[], offset, len) : offset 지점 부터 len 까지 byte[]을 출력한다.</li>

```java
public class Test{
  public static void main(String[] args){
    FileOutputStream out = new FileOutputStream();
    byte[] bytes = {0x7a, 0x6b, 0x5c, 0x4d, 0x3e, 0x2f, 0x30};
    out.write(0x7a6b5c4d); // 0X4d만 출력(1byte)만 출력한다.
    out.write(bytes);      // 7a 6b 5c 4d 3e 2f 30
    out.write(bytes, 2, 3);// 5c 4d 3e
    out.close();
  }
}
```
<li> 문자열을 출력할 때는 인코딩에 주의를 해야한다.</li>
<li> 문자열 인코딩 설정을 String.getBytes() 단계에서 설정한다.</li>
<li> 문자열 인코딩은 UCS2 -> UTF-8이다.(단, 윈도우는 UCS2 -> MS949)</li>

```java
public class Test{
  public static void main(String[] args){
    String str = new String("AB가각");
    
    byte[] bytes_default = str.getBytes();            //41 42 ea b0 80 ea b0 81 
    byte[] bytes_UTF_8 = str.getBytes("UTF-8");       //41 42 ea b0 80 ea b0 81 
    byte[] bytes_MS949 = str.getBytes("MS949");       //41 42 b0 a1 b0 a2 
    byte[] bytes_UTF_16BE= str.getBytes("UTF-16BE");  //00 41 00 42 ac 00 ac 01 
    byte[] bytes_UTF_16LE = str.getBytes("UTF-16LE"); //41 00 42 00 00 ac 01 ac 
    
    FileOutputStream out = new FileOutputStream(test.txt);
    out.write(bytes /* 필요한 인코딩 타입*/);
    out.close();
  }
}
```
</ul>
</div>

<h3> FileInputStream </h3>
<div style="margin-left: 10px">
<ul>
<li> 객체를 인스턴스 할 때 설정한 파일명으로 입력을 한다.</li>
<li> 더 이상 읽을 것이 없으면 -1을 return한다.</li>

```java
FileInputStream 객체명 = new FileInputStream("파일명");
```
<li> read(int): 1바이트를 입력한다.</li>
<li> read(byte[]) : 1바이트 배열을 입력한다.</li>
<li> read(byte[], offset, len) : offset 지점 부터 len 까지 byte[]에 입력한다.</li>

```java
public class Test{
  public static void main(String[] args){
    // test.text = 7a 6b 5c 4d 3e 2f 30
    FileInputStream in = new FileInputStream();
    byte[] buf = new byte[10000];
    
    int b = in.read(); // 7a
    in.read(buf,0,2);  // 6b 5c
    in.read(buf);      // 4d 3e 2f 30 
    in.close();
  }
}
```
<li> 문자열을 입력할 때는 디코딩에 주의를 해야한다.</li>
<li> 문자열 디코딩을 read단계에서 실행하면, 인코더에 따라 읽어야하는 바이트 숫작가 달라진다.</li>
<li> 문자열을 생성할 때 인코더에 대한 매개변수를 넘겨주는 것이 편리한다.</li>

```java
String str = new String(but, 시작, 끝, 인코더정보);
```
<li> 문자열 인코딩은 UCS2 -> UTF-8이다.(단, 윈도우는 UCS2 -> MS949)</li>

```java
public class Test{
  public static void main(String[] args){
    FileInputStream in = new FileInputStream("sample/인코더정보.txt");
    byte[] buf = new byte[10000];
    int count = in.read(buf);
    String str = new String(buf, 0, count, 인코더정보);
    in.close();
  }
}
```
</ul>
</div>
</div>

<h2>3.Character Stream</h2>
<div style="margin-left: 10px;">
<h3> FileWriter </h3>
<div style="margin-left: 10px">
<ul>
<li> 객체를 인스턴스 할때 설정한 파일명으로 파일이 만들어진다.</li>
<li> JVM은 문자 데이터를 다룰 때 UCS2(UTF16BE, 2바이트) 유니코드를 사용한다.</li>

```java
FileWriter 객체명 = new FileWriter("파일명");
```
<li> write(int): 2바이트를 출력한다.</li>

```java
public class Test{
  public static void main(String[] args){
    Charset charset_MS949 = Charset.forName("MS949");
    FileWriter out = new FileWriter("temp/test2.txt", charset_MS949); 
    out.write(0xac00); //text 파일 : b0 a1 
    
    Charset charset_UTF16BE = Charset.forName("UTF-16BE");
    out = new FileWriter("temp/test2.txt", charset_UTF16BE);
    out.write(0xac00); //text 파일 : ac 00
  }
}
```
<li> write(char[]) : 2바이트 배열을 출력한다.</li>

```java
public class Test {
  public static void main(String[] args){
    FileWriter out = new FileWriter("temp/test2.txt");
    char[] chars = new char[] {'A', 'B', 'C', '0', '1', '2', '가', '각', '간', '똘', '똥'};

    // FileOutputStream 은 byte[] 을 출력하지만,
    // FileWriter 는 char[] 을 출력한다.
    out.write(chars); // 문자 배열 전체를 출력한다.

    // 당연히 UCS2를 JVM 환경 변수 'file.encoding'에 설정된 문자 코드표에 따라 변환하여 출력한다.
    // JVM이 입출력 문자 코드표로 UTF-8을 사용한다면
    // 영어는 1바이트로 변환되어 출력될 것이고,
    // 한글은 3바이트로 변환되어 출력될 것이다.
    // JVM(UCS2)    File(UTF-8)
    // 00 41    ==> 41
    // 00 42    ==> 42
    // 00 43    ==> 43
    // 00 30    ==> 30
    // 00 31    ==> 31
    // 00 32    ==> 32
    // ac 00    ==> ea b0 80
    // ac 01    ==> ea b0 81
    // ac 04    ==> ea b0 84
    // b6 18    ==> eb 98 98
    // b6 25    ==> eb 98 a5
    out.close();
  }
}
```
<li> write(char[], offset, len) : 2바이트 배열을 offset부터 len개 출력한다.</li>

```java
public class Test {
  public static void main(String[] args) throws Exception {
    FileWriter out = new FileWriter("temp/test2.txt");
    char[] chars = new char[] {'A','B','C','가','각','간','똘','똥'}; 

    out.write(chars, 2, 3); // 2번 문자부터 3 개의 문자를 출력한다.
    out.close();
  }
}
```
<li> write(str) : 문자열을 출력한다.</li>

```java
public class Test {
  public static void main(String[] args) throws Exception {
    FileWriter out = new FileWriter("temp/test2.txt");

    String str = new String("AB가각");
    out.write(str); 
    out.close();
  }
}
```
</ul>
</div>
<h3> FileReader </h3>
<div style="margin-left: 10px">
<ul>
<li> 객체를 인스턴스 할때 설정한 파일명으로 읽는다..</li>

```java
FileReader 객체명 = new FileReader("파일명","인코더 정보");
```
<li> 파일 데이터를 읽을 때 인코더 정보를 알려줘야 한다.</li>
<li> 출력 스트림 객체를 생성할 때 파일의 문자 집합을 지정하면,JVM 환경 변수 'file.encoding'에 설정된 문자집합을 무시한다. </li>

```java
public class Test {
  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("sample/ms949.txt", Charset.forName("MS949")); // 41 42 b0 a1 b0
    int ch1 = in.read(); // 41 => 0041('A')
    int ch2 = in.read(); // 42 => 0042('B')
    int ch3 = in.read(); // b0 a1 => ac00 => '가'
    int ch4 = in.read(); // b0 a2 => ac01 => '각'
    in.close();

    System.out.printf("%04x, %04x, %04x, %04x\n", ch1, ch2, ch3, ch4); // 0041 0042 ac00 ac01
  }
}
```
<li> 더이상 읽을 데이터가 없으면 -1을 return한다.</li>

```java
public class Test {
  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("sample/utf8.txt"); // 41 42 ea b0 80 ea b0 81
    int ch;
    while ((ch = in.read()) != -1) {
      System.out.printf("%04x ", ch); //0041 0042 ac00 ac01
    }
    in.close();
  }
}
```
<li> buf에 read을 하면 buf가 full상태 일때 까지 읽어드린다.</li>
<li> read에서 리턴 값은 읽은 문자의 개수이다. 바이트의 개수가 아니다</li>

```java
public class Test {

  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("temp/test2.txt");
    char[] buf = new char[100];

    // read(버퍼의주소)
    int count = in.read(buf);

    // File(UTF-8)  JVM(UCS2)
    // 41       ==> 00 41
    // 42       ==> 00 42
    // 43       ==> 00 43
    // 30       ==> 00 30
    // 31       ==> 00 31
    // 32       ==> 00 32
    // ea b0 80 ==> ac 00
    // ea b0 81 ==> ac 01
    // ea b0 84 ==> ac 04
    // eb 98 98 ==> b6 18
    // eb 98 a5 ==> b6 25

    in.close();

    System.out.printf("%d\n", count); // 4
    for (int i = 0; i < count; i++){ System.out.printf("%c(%04x)\n", buf[i], (int)buf[i]);
    //    A(0041)
    //    B(0042)
    //    가(ac00)
    //    각(ac01)
    
  }}}

```

<li> read(buffer) : 문자열을 buffer크기 만큼 읽어 드린다.</li>

```java
public class Test {
  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("temp/test2.txt");
    char[] buf = new char[100];
    int count = in.read(buf); // char 배열에 담을 때 UTF-16BE 코드 값으로 변환한다.
    String str = new String(buf, 0, count); // 그래서 String 객체를 만들 때 문자집합을 지정할 필요가 없다.
    in.close();
    System.out.printf("[%s]\n", str);
  }

}
```
<li> read(buffer, offset, count) : 문자열을 buffer배열에서 [offset,offset+count)까지 할당한다.</li>

```java
public class Test {
  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("temp/test2.txt");
    char[] buf = new char[100];

    // read(버퍼의주소, 저장할위치, 읽을바이트개수)
    // => 리턴 값은 실제 읽은 문자의 개수이다.
    int count = in.read(buf, 10, 40); // 40개의 문자를 읽어 10번 방부터 저장한다.
    in.close();
    System.out.printf("%d\n", count);
    for (int i = 0; i < 20; i++)
      System.out.printf("%c(%04x) \n", buf[i], (int) buf[i]);
    System.out.println();
  }
}
```
</ul>
</div>
</div>

<h2>4.Data Stream</h2>
<ul><li>Data I/O stream은 File I/O stream을 상속받아 사용한다.</li></ul>
<div style="margin-left: 10px;">
<h3> DataOutputStream </h3>
<div style="margin-left: 10px">
<ul>
<li> writeUTF() : UTF 문자열을 출력한다.</li>
<li> writeInt() : 숫자를 출력한다.</li>
<li> writeboolean() : 논리값을 출력한다.</li>

```java
public class Test{
  class Member{
    public String member;
    public int age;
    public boolean gender;
  }

  public static void main(String[] args) throws Exception {
    DataFileOutputStream out = new DataFileOutputStream("temp/test4_2.data");
    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // 인스턴스의 값을 출력하라!
    // 1) 이름 출력 
    out.writeUTF(member.name);
    // 2) 나이 출력 (4바이트)
    out.writeInt(member.age);
    // 3) 성별 출력 (1바이트)
    out.writeBoolean(member.gender);

    out.close();
    }
}
```

<li> readUTF() : UTF 문자열을 입력한다.</li>
<li> readInt() : 숫자를 입력한다.</li>
<li> readboolean() : 논리값을 입력한다.</li>

```java
public class Test{
  class Member{
    public String member;
    public int age;
    public boolean gender;
  }

  public static void main(String[] args) throws Exception {
    DataFileInputStream in = new DataFileInputStream("temp/test4_2.data");

    Member member = null;

    member = new Member();
    // 1) 이름 읽기
    member.name = in.readUTF();
    // 2) 나이(int) 읽기
    member.age = in.readInt();
    // 3) 성별 읽기
    member.gender = in.readBoolean();
    in.close();
  }
}
```
</ul>
</div>
</div>

<h2>5.Buffer Stream</h2>
<div style="margin-left: 10px;">
<h3> Buffer 사용개념 </h3>
<div style="margin-left: 10px">
<ul>
<li> 데이터를 입출력에 걸리는 시간은 데이터를 찾는 시간과 데이터를 입력하는 시간이다.</li>
<li> 데이터를 찾는 시간은 주기억장치와 램에 접근하는 시간이며, 입출력 시간의 대부분을 차지한다.</li>
<li> Data Stream의 read()는 1바이트 씩 찾고 가져오기 때문에 시간이 과다하게 소요된다.</li>
<li> 한번 조회할때 많은 데이터를 가져오는것이 시간절약 측면에서 유리하다.</li>
<li> 데이터 조회시간과 한번에 읽을 데이터의 양은 logN의 상관관계를 가진다.</li>
<li> 서버의 경우 1MB정도의 데이터 조회는 서버컴퓨터에 과부하를 야기한다.</li>
<li> 데이터 탐색 시간과 데이터의 양의 최적점은 8Kb정도 이다.</li>
<li> 그렇기 때문에 buffer Stream은 8kb를 한번에 가져오고, 필요하면 더 가져온다.</li>
<li> 버퍼의 기본 사용은 다음과 같다.</li>

```java
public class Test {

  public static void main(String[] args) throws Exception {
    BufferedFileInputStream in = new BufferedFileInputStream("temp/jls.pdf");
    int b;
    int callCount = 0;
    while ((b = in.read()) != -1)
      callCount++; // 파일을 끝까지 읽는다.

    // => BufferedFileInputStream의 read() 메서드는
    //    FileInputStream에서 상속 받은 메서드를 이용하여
    //    바이트 배열로 데이터를 왕창 가져온 다음
    //    그 배열에서 1바이트를 리턴한다.
    // => 그 이후에는 바이트 배열의 데이터가 떨어질 때까지
    ///   계속 바이트 배열에서 값을 꺼내 리턴해 준다.
    // => 바이트 배열의 값이 떨어지면
    //    다시 바이트 배열 단위로 데이터를 읽어 온다.
    //    그래서 1바이트 씩 읽더라도 그렇게 속도가 떨어지지 않는 것이다.
    in.close();
  }
}
```
</ul>
</div>
</div>
</div>

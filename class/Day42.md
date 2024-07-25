# Decorator 패턴
<div style="margin-left: 20px;">
<h2>데코레이터 패턴</h2>
<div style="margin-left: 10px;">
<h3> 데코레이터 패턴의 정의</h3>
<div style="margin-left: 10px;">
<ul>
<li> 정의 : 객체에 추가 요소를 동적으로 더하는 기능</li>
<li> 상속을 사용하여 객체들을 추가하면 컴파일 단계에서 결정이 되며 기능이 추가 될 수록 코드가 복잡해진다.</li>
<li> OCP : Open-Close Principle으로 클래스는 확장에는 열려 있어야 하지만 변경에는 닫혀있어야 한다.</li>
<li> 상속을 이용한 강결합은 유지보수 측면에서 OCP 원칙을 위배한다.</li>

![image](https://github.com/user-attachments/assets/d0e5a5b8-8f5f-4f72-9f9c-8ac15849a4dc)
</ul>
</div>
<h3> 데코레이터 패턴 만들기</h3>
<div style="margin-left: 10px;">
<ul>
<li> 커피 조제법 및 가격 출력하는 메뉴판 프로그램 만들기</li>
<Li> 구성요소 </Li>
<ol style="padding-left: 10px">
<li> (interface or abstract) Componet : Concrete Component와 Decorator을 모두 받을 수 있는 슈퍼 클래스</li>
<li> (concrete) Component : Decorator를 적용하기 전의 main이 되는 클래스(이 에제에서는 주 원료)</li>
<li> (interface or abstract) Decorator : Concrete Decorator을 구현하기 전에 생성하는 슈퍼 클래스(이 예제에서는 데코레이터가 어떻게 작용할지 설정)</li>
</ol>
</ul>
<div style="margin-left: 10px;">
<ul>
<li> abstract Component </li>

```java
public abstract class Beverage{
  protected String description;
  
  public String getDescription(){
    return description;
  }
  
  public abstract cost();
}
```

<li> Concrete Component </li>

```java
public class Espresso extends Beverage{
  public Espresso(){
    description = "Espresso";
  }
  
  public String getDescription(){
    return description;
  }
  
  public double cost(){
    return 100;
  }
}
```

<li> abstract Decorator </li>

```java
public abstract class Decorator extends Beverage{
  protected Beverage beverage;
  public abstract String getDescription();
}
```

<li> Concrete Decorator </li>

```java
public class Mocha extends Decorator{
  public Mocha(Beverage beverage){
    this.beverage = beverage;
  }
  
  public String getDescription(){
    return beverage.getDescription + ", Mocha";
  }
  
  public double cost(){
    return beverage.cost() + 200;
  }
      
}
```
<li> 실행하기</li>
<ol>
<li> Espresso 객체를 생성(Beverage을 상속받은 객체) </li>
<li> 그 객체를 Mocha객체에 삽입</li>
<li> Mocha 객체에 Beverage = Espresso 타입의 객체</li>
<li> cagemocha.getDescription -> Beverage(Espresso타입).getDescription + 200(모카의 가격)</li>
<li> Beverage.getDescription -> 100(에스프레소의 가격) </li>
</ol>

```java
public class App{
  public static void main(String[] args){
    //데코레이터 미사용
    Beverage espresso = new Espresso();
    System.out.printf("%s : %f원", espresso.getDescription(), espresso.cost());
    
    Beverage cafemocha = new Espresso();
    cafemocha = new Mocha(cafemocha);
    System.out.printf("%s : %f원", cafemocha.getDescription(), cafemocha.cost());
  }
}
```
</ul>
</div>
</div>
</div>
<h3> I/O Stream 데코레이터 살펴보기</h3>
<div style="margin-left: 10px;">
<ul></ul>
<ul></ul>
<ul></ul>
</div>
</div>

# 엑셀 입출력
<div style="margin-left: 20px;">
<h2> 1. XSSF 라이브러리</h2>
<div style="margin-left: 10px;">
<h3> Microsoft Excel (.xlsx) 파일을 생성, 수정, 읽기 및 쓰기에 사용되는 Java API </h3>
<div style="margin-left: 10px;">
<ul>
<li> 엑셀 파일 생성: 새로운 .xlsx 파일을 생성할 수 있다. </li>
<li> 엑셀 파일 읽기: 기존 .xlsx 파일을 읽어서 데이터를 추출할 수 있다.</li>
<li> 엑셀 파일 수정: 셀, 행, 시트 등을 추가하거나 수정할 수 있다.</li>
<li> 2007이하버전(.xls)은 HSSF라이브러리를 사용한다.</li>
<li> 주요클래스</li>
<ol style="padding-left: 10px", type="1">
<li> XSSFWorkbook: 엑셀 워크북을 나타내는 클래스입니다.</li>
<li> XSSFSheet: 엑셀 시트를 나타내는 클래스입니다.</li>
<li> XSSFRow: 엑셀 행을 나타내는 클래스입니다.</li>
<li> XSSFCell: 엑셀 셀을 나타내는 클래스입니다.</li>
</ol>
</ul>
</div>
</div>
<h2> 2. XSSF 라이브러리 설치하기</h2>
<div style="margin-left: 10px;">
<h3> 라이브러리 다운로드 </h3>
<div style="margin-left: 10px;">
<ul>
<li> maver repo에서 apachi-poi를 검색하여 필요한 버전에 gradle정보를 읽어온다. </li>
<li> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml</li>

![image](https://github.com/user-attachments/assets/6306d054-bf4c-4c7b-a58c-2450924ba905)

<li> gradlebuild에 Implementation에 해당 값을 삽입한다.</li>
<li> IntelliJ의 경우 gradle 새로고침</li>
<li> Esclipse의 경우 터미널에서 해당 폴더 까지 이동후 gradle esclipse를 실행</li>
</ul>
</div>
</div>
<h2> 3. 주요 클래스 사용</h2>
<div style="margin-left: 10px;">
<h3> XSSFWorkbook </h3>
<ul>
<li> workbook을 생성하는 메서드</li>

```java
XSSFWorkbook workbook = new XSSFWorkbook();
//이 생성자는 새로운 빈 워크북(엑셀 파일)을 생성하며, 이 워크북에는 시트가 없고, 필요한 시트는 추가할 수있다.
XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx");
//	이 생성자는 파일 경로로 지정된 data.xlsx 파일을 읽어와서 워크북을 생성한다. 
//	파일이 존재하고 유효한 .xlsx 파일 형식이라면, 해당 파일의 내용을 메모리에 로드한다.
```
</ul>
<div style="margin-left: 10px;">
</div>
</div>

<div style="margin-left: 10px;">
<h3> getSheet/ createSheet </h3>
<ul>
<li> 생성된 workbook에서 sheet를 관리 메서드</li>

```java
XSSFSheet sheet = workbook.getSheet("시트명");
// 시트명을 불러온다.
XSSFSHeet sheet = workbook.createSheet("시트명");
// 시트명을 만든다.
```
</ul>
<div style="margin-left: 10px;">
</div>
</div>

<div style="margin-left: 10px;">
<h3> getRow/ createRow </h3>
<ul>
<li> sheet의 행을 관리하는 메서드</li>

```java
Row row = sheet.getRow(int a);
// a행을 불러온다.
Row row = sheet.createRow(int a);
// a행을 만든다.
```
</ul>
<div style="margin-left: 10px;">
</div>
</div>

<div style="margin-left: 10px;">
<h3> getCell/ createCell </h3>
<ul>
<li>row의 열을 관리 하는 메서드</li>

```java
Cell cell = row.getCell(int a);
// row행 a열을 불러온다.
Cell cell = row.createCell(int a);
// row행 a열을 만든다.
```
</ul>
<div style="margin-left: 10px;">=
</div>
</div>

<h2> 4. Java에서 Excel 파일 만들기</h2>
<div style="margin-left: 10px;">
<h3> Microsoft Excel (.xlsx) 파일을 생성, 수정, 읽기 및 쓰기에 사용되는 Java API </h3>
<div style="margin-left: 10px;">
<ul>
<li> 엑셀 파일 생성: 새로운 .xlsx 파일을 생성할 수 있다. </li>
<li> 엑셀 파일 읽기: 기존 .xlsx 파일을 읽어서 데이터를 추출할 수 있다.</li>
<li> 엑셀 파일 수정: 셀, 행, 시트 등을 추가하거나 수정할 수 있다.</li>
<li> 2007이하버전(.xls)은 HSSF라이브러리를 사용한다.</li>
<li> 주요클래스</li>
<ol style="padding-left: 10px", type="1">
<li> XSSFWorkbook: 엑셀 워크북을 나타내는 클래스입니다.</li>
<li> XSSFSheet: 엑셀 시트를 나타내는 클래스입니다.</li>
<li> XSSFRow: 엑셀 행을 나타내는 클래스입니다.</li>
<li> XSSFCell: 엑셀 셀을 나타내는 클래스입니다.</li>
</ol>
</ul>
</div>
</div>
</div>

# Obsever 패턴
<div style="margin-left: 20px;">
<h2>옵저버 패턴</h2>
<div style="margin-left: 10px;">
<h3> 옵저버 패턴의 정의</h3>
<div style="margin-left: 10px;">
<ul>
<li> 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체에게 연락이 가고 자동으로 내용이 갱신되는 방식</li>
<li> 일대다 의존성(one to many)을 기반으로 주제객체의 상태를 옵저버 객체에게 알린다. </li>
<img width="407" alt="image" src="https://github.com/user-attachments/assets/ae40ffdc-6aa6-479b-a9c5-20f00cacec10">
</ul>
</div>
</div>

<h2>옵저버 패턴 만들기</h2>
<div style="margin-left: 10px;">

<h3> subject interface</h3>
<div style="margin-left: 10px;">
<ul>
<li> subject interface는 옵저버를 등록/삭제 및 알리는 메서드선언을 해준다.</li>

```java 
public interface Subject {
  public void registerObserver(Observer observer);
  public void removeObserver(Observer observer);
  public void notifyObservers();
}
```
</ul>
</div>

<h3> observer interface</h3>
<div style="margin-left: 10px;">
<ul>
<li> Subject로 부터 업데이트를 하는 메서드를 구현한다.</li>

```java
public interface Observer {
  public void update(Weather weather);
}
```
<li> Subject구현체에서 사용할 display interface도 만든다.</li>

```java
public interface DisplyElement {
  public void display();
}
```
</ul>
</div>

<h3> Subject 구현체</h3>
<div style="margin-left: 10px;">
<ul>
<li> Subject interface를 직접적으로 구현하는 구현체 </li>

```java
public class WeatherData implements Subject {
  private List<Observer> obsevers;
  private Weather weather;

  public WeatherData(Weather weather) {
    this.obsevers = new ArrayList<>();
    this.weather = new Weather();
  }

  @Override
  public void registerObserver(Observer observer) {
    obsevers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    obsevers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : obsevers) {
      observer.update(weather);
    }
  }

  public void measurementsChanged() {
    notifyObservers();
  }

  public void setMeasurements(Weather weather) {
    this.weather = weather;
    measurementsChanged();
  }
}
```
</ul>
</div>

<h3> observer구현체</h3>
<div style="margin-left: 10px;">
<ul>
<li> Observer 구현체를 만든다.</li>

```java
public class CurrentConditionsDisplay implements Observer, DisplyElement {
  private Weather weather;
  private WeatherData weatherData;

  public CurrentConditionsDisplay(WeatherData weatherData) {
    this.weatherData = weatherData;
    weatherData.registerObserver(this);
  }

  @Override
  public void display() {
    System.out.printf("현재 온도 : %f , 현재 습도 : %f", weather.getTemp(), weather.getHumidity());
  }

  @Override
  public void update(Weather weather) {
    this.weather = weather;
    display();
  }
}
```
</ul>
</div>

<h3> 실행</h3>
<div style="margin-left: 10px;">
<ul>
<li> 실행한다.</li>

```java
public class App {
  public static void main(String[] args) {
    Weather weather = new Weather();
    WeatherData weatherData = new WeatherData(weather);
    CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

    weatherData.setMeasurements(new Weather(36, 50, 50));

  }
}
```
</ul>
</div>

</div>
</div>

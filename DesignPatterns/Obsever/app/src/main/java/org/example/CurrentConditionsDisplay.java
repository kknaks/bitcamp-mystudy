package org.example;

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

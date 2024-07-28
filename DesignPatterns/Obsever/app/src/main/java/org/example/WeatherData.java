package org.example;

import java.util.ArrayList;
import java.util.List;


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

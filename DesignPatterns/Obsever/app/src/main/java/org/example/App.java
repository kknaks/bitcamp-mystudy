/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

public class App {
  public static void main(String[] args) {
    Weather weather = new Weather();
    WeatherData weatherData = new WeatherData(weather);
    CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

    weatherData.setMeasurements(new Weather(36, 50, 50));

  }
}

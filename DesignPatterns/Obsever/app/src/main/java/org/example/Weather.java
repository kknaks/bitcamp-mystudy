package org.example;

public class Weather {
  private float temp;
  private float humidity;
  private float pressure;

  public Weather() {
    this.temp = 0;
    this.humidity = 0;
    this.pressure = 0;
  }

  public Weather(float temp, float humidity, float pressure) {
    this.temp = temp;
    this.humidity = humidity;
    this.pressure = pressure;
  }

  public float getTemp() {
    return temp;
  }

  public void setTemp(float temp) {
    this.temp = temp;
  }

  public float getHumidity() {
    return humidity;
  }

  public void setHumidity(float humidity) {
    this.humidity = humidity;
  }

  public float getPressure() {
    return pressure;
  }

  public void setPressure(float pressure) {
    this.pressure = pressure;
  }
}

package org.example;

public class PersonImpl implements Person {
  String name;
  String gender;
  String interests;
  int rating;
  int ratingCount = 0;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getGender() {
    return gender;
  }

  @Override
  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String getInterests() {
    return interests;
  }

  @Override
  public void setInterests(String intersts) {
    this.interests = intersts;
  }

  @Override
  public int getGeekRating() {
    if (ratingCount == 0)
      return 0;
    return (rating / ratingCount);
  }

  @Override
  public void setGeekRating(int geekRating) {
    this.rating += rating;
    ratingCount++;
  }
}

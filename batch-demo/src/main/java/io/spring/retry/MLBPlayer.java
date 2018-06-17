package io.spring.retry;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 17/06/18
 * Time: 3:28 PM
 */
public class MLBPlayer {
  private String name, position, team;
  private int height, weight;
  private double age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public double getAge() {
    return age;
  }

  public void setAge(double age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "MLBPlayer{" +
        "name='" + name + '\'' +
        ", position='" + position + '\'' +
        ", team='" + team + '\'' +
        ", height=" + height +
        ", weight=" + weight +
        ", age=" + age +
        '}';
  }
}

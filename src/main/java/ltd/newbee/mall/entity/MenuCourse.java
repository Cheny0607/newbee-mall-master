package ltd.newbee.mall.entity;

public class MenuCourse {

  private Long id;
  private String course;
  private String drink;
  private String meal;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getDrink() {
    return drink;
  }

  public void setDrink(String drink) {
    this.drink = drink;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }

  @Override
  public String toString() {
    return "MenuCourse{" +
        "id=" + id +
        ", course='" + course + '\'' +
        ", drink='" + drink + '\'' +
        ", meal='" + meal + '\'' +
        '}';
  }
}

package ltd.newbee.mall.entity;

public class RestaurantDesc {
  private Long id;
  private String nearbyStation;
  private String nightBudget;
  private String dayBudget;
  private String restDay;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNearbyStation() {
    return nearbyStation;
  }

  public void setNearbyStation(String nearbyStation) {
    this.nearbyStation = nearbyStation;
  }

  public String getNightBudget() {
    return nightBudget;
  }

  public void setNightBudget(String nightBudget) {
    this.nightBudget = nightBudget;
  }

  public String getDayBudget() {
    return dayBudget;
  }

  public void setDayBudget(String dayBudget) {
    this.dayBudget = dayBudget;
  }

  public String getRestDay() {
    return restDay;
  }

  public void setRestDay(String restDay) {
    this.restDay = restDay;
  }

  @Override
  public String toString() {
    return "RestaurantDesc{" +
        "id=" + id +
        ", nearbyStation='" + nearbyStation + '\'' +
        ", nightBudget='" + nightBudget + '\'' +
        ", dayBudget='" + dayBudget + '\'' +
        ", restDay='" + restDay + '\'' +
        '}';
  }
}

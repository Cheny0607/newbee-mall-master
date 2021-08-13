package ltd.newbee.mall.entity;

public class TopCourse {
  private Long id;
  private String courseTitle;
  private String courseDesc;
  private String image;
  private String priceNum;
  private String priceTax;
  private String menuNum;
  private String peopleNum;
  private String timeLimit;
  private String drink;
  private String menuContent;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public String getCourseDesc() {
    return courseDesc;
  }

  public void setCourseDesc(String courseDesc) {
    this.courseDesc = courseDesc;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getPriceNum() {
    return priceNum;
  }

  public void setPriceNum(String priceNum) {
    this.priceNum = priceNum;
  }

  public String getPriceTax() {
    return priceTax;
  }

  public void setPriceTax(String priceTax) {
    this.priceTax = priceTax;
  }

  public String getMenuNum() {
    return menuNum;
  }

  public void setMenuNum(String menuNum) {
    this.menuNum = menuNum;
  }

  public String getPeopleNum() {
    return peopleNum;
  }

  public void setPeopleNum(String peopleNum) {
    this.peopleNum = peopleNum;
  }

  public String getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(String timeLimit) {
    this.timeLimit = timeLimit;
  }

  public String getDrink() {
    return drink;
  }

  public void setDrink(String drink) {
    this.drink = drink;
  }

  public String getMenuContent() {
    return menuContent;
  }

  public void setMenuContent(String menuContent) {
    this.menuContent = menuContent;
  }

  @Override
  public String toString() {
    return "TopCourse{" +
        "id=" + id +
        ", courseTitle='" + courseTitle + '\'' +
        ", courseDesc='" + courseDesc + '\'' +
        ", image='" + image + '\'' +
        ", priceNum='" + priceNum + '\'' +
        ", priceTax='" + priceTax + '\'' +
        ", menuNum='" + menuNum + '\'' +
        ", peopleNum='" + peopleNum + '\'' +
        ", timeLimit='" + timeLimit + '\'' +
        ", drink='" + drink + '\'' +
        ", menuContent='" + menuContent + '\'' +
        '}';
  }
}

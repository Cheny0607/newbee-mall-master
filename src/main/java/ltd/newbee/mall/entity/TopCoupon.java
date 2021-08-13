package ltd.newbee.mall.entity;

import java.util.Date;

public class TopCoupon {
  private Long id;
  private String restName;
  private String couponNum;
  private String couponTitle;
  private String useCondition;
  private String showCondition;
  private String note;
  private Date timeLimitDate;
  private String timeLimitDay;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRestName() {
    return restName;
  }

  public void setRestName(String restName) {
    this.restName = restName;
  }

  public String getCouponNum() {
    return couponNum;
  }

  public void setCouponNum(String couponNum) {
    this.couponNum = couponNum;
  }

  public String getCouponTitle() {
    return couponTitle;
  }

  public void setCouponTitle(String couponTitle) {
    this.couponTitle = couponTitle;
  }

  public String getUseCondition() {
    return useCondition;
  }

  public void setUseCondition(String useCondition) {
    this.useCondition = useCondition;
  }

  public String getShowCondition() {
    return showCondition;
  }

  public void setShowCondition(String showCondition) {
    this.showCondition = showCondition;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Date getTimeLimitDate() {
    return timeLimitDate;
  }

  public void setTimeLimitDate(Date timeLimitDate) {
    this.timeLimitDate = timeLimitDate;
  }

  public String getTimeLimitDay() {
    return timeLimitDay;
  }

  public void setTimeLimitDay(String timeLimitDay) {
    this.timeLimitDay = timeLimitDay;
  }

  @Override
  public String toString() {
    return "TopCoupon{" +
        "id=" + id +
        ", restName='" + restName + '\'' +
        ", couponNum='" + couponNum + '\'' +
        ", couponTitle='" + couponTitle + '\'' +
        ", useCondition='" + useCondition + '\'' +
        ", showCondition='" + showCondition + '\'' +
        ", note='" + note + '\'' +
        ", timeLimitDate=" + timeLimitDate +
        ", timeLimitDay='" + timeLimitDay + '\'' +
        '}';
  }
}

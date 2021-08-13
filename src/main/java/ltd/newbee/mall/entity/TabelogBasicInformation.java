package ltd.newbee.mall.entity;

import java.util.Date;

public class TabelogBasicInformation {
  private Long id;
  private String name;
  private String genre;
  private String phoneNumber;
  private String reservation;
  private String address;
  private String access;
  private String workTime;
  private String officialBudget;
  private String aggregateBudget;
  private String payWay;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getReservation() {
    return reservation;
  }

  public void setReservation(String reservation) {
    this.reservation = reservation;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  public String getWorkTime() {
    return workTime;
  }

  public void setWorkTime(String workTime) {
    this.workTime = workTime;
  }

  public String getOfficialBudget() {
    return officialBudget;
  }

  public void setOfficialBudget(String officialBudget) {
    this.officialBudget = officialBudget;
  }

  public String getAggregateBudget() {
    return aggregateBudget;
  }

  public void setAggregateBudget(String aggregateBudget) {
    this.aggregateBudget = aggregateBudget;
  }

  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }

  @Override
  public String toString() {
    return "TabelogBasicInformation{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", genre='" + genre + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", reservation='" + reservation + '\'' +
        ", address='" + address + '\'' +
        ", access='" + access + '\'' +
        ", workTime='" + workTime + '\'' +
        ", officialBudget='" + officialBudget + '\'' +
        ", aggregateBudget='" + aggregateBudget + '\'' +
        ", payWay='" + payWay + '\'' +
        '}';
  }
}

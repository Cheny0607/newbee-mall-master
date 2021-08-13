package ltd.newbee.mall.entity;

public class SeatFacility {
  private Long id;
  private String seatsNumber;
  private String privateRoom;
  private String reserved;
  private String smoking;
  private String parkingLot;
  private String spaceEquipment;
  private String mobilePhone;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSeatsNumber() {
    return seatsNumber;
  }

  public void setSeatsNumber(String seatsNumber) {
    this.seatsNumber = seatsNumber;
  }

  public String getPrivateRoom() {
    return privateRoom;
  }

  public void setPrivateRoom(String privateRoom) {
    this.privateRoom = privateRoom;
  }

  public String getReserved() {
    return reserved;
  }

  public void setReserved(String reserved) {
    this.reserved = reserved;
  }

  public String getSmoking() {
    return smoking;
  }

  public void setSmoking(String smoking) {
    this.smoking = smoking;
  }

  public String getParkingLot() {
    return parkingLot;
  }

  public void setParkingLot(String parkingLot) {
    this.parkingLot = parkingLot;
  }

  public String getSpaceEquipment() {
    return spaceEquipment;
  }

  public void setSpaceEquipment(String spaceEquipment) {
    this.spaceEquipment = spaceEquipment;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  @Override
  public String toString() {
    return "SeatFacility{" +
        "id=" + id +
        ", seatsNumber='" + seatsNumber + '\'' +
        ", privateRoom='" + privateRoom + '\'' +
        ", reserved='" + reserved + '\'' +
        ", smoking='" + smoking + '\'' +
        ", parkingLot='" + parkingLot + '\'' +
        ", spaceEquipment='" + spaceEquipment + '\'' +
        ", mobilePhone='" + mobilePhone + '\'' +
        '}';
  }
}

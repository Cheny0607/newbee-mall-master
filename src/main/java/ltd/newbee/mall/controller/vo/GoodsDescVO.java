package ltd.newbee.mall.controller.vo;

import java.io.Serializable;

public class GoodsDescVO implements Serializable {

  private Long goodsId;
  private String color;
  private String size;
  private String material;
  private String weight;
  private String warrantyYear;
  private String setDate;
  private String wrapSize;


  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }


  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }


  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }


  public String getWarrantyYear() {
    return warrantyYear;
  }

  public void setWarrantyYear(String warrantyYear) {
    this.warrantyYear = warrantyYear;
  }


  public String getSetDate() {
    return setDate;
  }

  public void setSetDate(String setDate) {
    this.setDate = setDate;
  }


  public String getWrapSize() {
    return wrapSize;
  }

  public void setWrapSize(String wrapSize) {
    this.wrapSize = wrapSize;
  }

}

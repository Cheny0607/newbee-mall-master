package ltd.newbee.mall.entity;

import java.util.Date;

public class GoodsReview {

  private String id;
  private Integer star;
  private String customerId;
  private String commentDate;
  private Long goodsId;
  private String title;
  private String content;
  private String picture;
  private String nickName;
  private String goodsName;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public Integer getStar() {
    return star;
  }

  public void setStar(Integer star) {
    this.star = star;
  }


  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }


  public String getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(String commentDate) {
    this.commentDate = commentDate;
  }


  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) { this.goodsName = goodsName; }


}




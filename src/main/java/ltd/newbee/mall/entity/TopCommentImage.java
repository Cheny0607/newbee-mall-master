package ltd.newbee.mall.entity;

import java.util.Date;

public class TopCommentImage {
  private Long id;
  private Long commentId;
  private String topic;
  private String image;
  private String star;
  private String sum;
  private String visitTime;
  private Date visitDate;
  private String helpedNum;
  private String comment;
  private String user;
  private String userLog;
  private String userRest;
  private String userFollow;
  private String userFollower;
  private String userCount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getStar() {
    return star;
  }

  public void setStar(String star) {
    this.star = star;
  }

  public String getSum() {
    return sum;
  }

  public void setSum(String sum) {
    this.sum = sum;
  }

  public String getVisitTime() {
    return visitTime;
  }

  public void setVisitTime(String visitTime) {
    this.visitTime = visitTime;
  }

  public Date getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(Date visitDate) {
    this.visitDate = visitDate;
  }

  public String getHelpedNum() {
    return helpedNum;
  }

  public void setHelpedNum(String helpedNum) {
    this.helpedNum = helpedNum;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getUserLog() {
    return userLog;
  }

  public void setUserLog(String userLog) {
    this.userLog = userLog;
  }

  public String getUserRest() {
    return userRest;
  }

  public void setUserRest(String userRest) {
    this.userRest = userRest;
  }

  public String getUserFollow() {
    return userFollow;
  }

  public void setUserFollow(String userFollow) {
    this.userFollow = userFollow;
  }

  public String getUserFollower() {
    return userFollower;
  }

  public void setUserFollower(String userFollower) {
    this.userFollower = userFollower;
  }

  public String getUserCount() {
    return userCount;
  }

  public void setUserCount(String userCount) {
    this.userCount = userCount;
  }

  @Override
  public String toString() {
    return "TopCommentImage{" +
        "id=" + id +
        ", commentId=" + commentId +
        ", topic='" + topic + '\'' +
        ", image='" + image + '\'' +
        ", star='" + star + '\'' +
        ", sum='" + sum + '\'' +
        ", visitTime='" + visitTime + '\'' +
        ", visitDate=" + visitDate +
        ", helpedNum='" + helpedNum + '\'' +
        ", comment='" + comment + '\'' +
        ", user='" + user + '\'' +
        ", userLog='" + userLog + '\'' +
        ", userRest='" + userRest + '\'' +
        ", userFollow='" + userFollow + '\'' +
        ", userFollower='" + userFollower + '\'' +
        ", userCount='" + userCount + '\'' +
        '}';
  }
}

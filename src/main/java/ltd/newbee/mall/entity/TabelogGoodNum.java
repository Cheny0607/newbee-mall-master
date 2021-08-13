package ltd.newbee.mall.entity;

public class TabelogGoodNum {

  private Integer commentId;
  private Long userId;

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "TabelogGoodNum{" +
        "commentId=" + commentId +
        ", userId=" + userId +
        '}';
  }
}

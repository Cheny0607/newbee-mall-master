package ltd.newbee.mall.entity;

public class GoodsReviewHelpedNum {

  private Long userId;
  private Integer reviewId;

  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }

  public Integer getReviewId() { return reviewId; }
  public void setReviewId(Integer reviewId) { this.reviewId = reviewId; }

  @Override
  public String toString() {
    return "helpedNum{" + "userId=" + userId + ", reviewId=" + reviewId + '}';
  }
}

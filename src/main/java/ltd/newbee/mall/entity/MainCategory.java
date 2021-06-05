package ltd.newbee.mall.entity;

import java.util.Date;

public class MainCategory {
  private Long categoryId;
  private Long parentId;
  private String categoryName;
  private Long id;
  private Date startDate;
  private Date endDate;

  public Long getCategoryId() { return categoryId; }
  public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

  public Long getParentId() { return parentId; }
  public void setParentId(Long parentId) { this.parentId = parentId; }

  public String getCategoryName() { return categoryName; }
  public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Date getStartDate() { return startDate; }
  public void setStartDate(Date startDate) { this.startDate = startDate; }

  public Date getEndDate() { return endDate; }
  public void setEndDate(Date endDate) { this.endDate = endDate; }

  @Override
  public String toString() {
    return "MainCategory{" + "categoryId=" + categoryId + ", parentId=" + parentId +
        ", categoryName='" + categoryName + '\'' + ", id=" + id +
        ", startDate=" + startDate + ", endDate=" + endDate + '}';
  }
}

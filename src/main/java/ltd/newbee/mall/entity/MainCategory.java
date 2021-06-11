package ltd.newbee.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class MainCategory {
  private Long categoryId;
  private Long parentId;
  private String categoryName;
  private Long id;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+9")
  private Date startDate;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+9")
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

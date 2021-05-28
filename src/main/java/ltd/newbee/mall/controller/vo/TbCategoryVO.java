package ltd.newbee.mall.controller.vo;

import java.util.Date;

public class TbCategoryVO {
  private Long id;
  private Long categoryId;
  private String name;
  private Date startDate;
  private Date endDate;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Long getCategoryId() { return categoryId; }
  public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Date getStartDate() { return startDate; }
  public void setStartDate(Date startDate) { this.startDate = startDate; }

  public Date getEndDate() { return endDate; }
  public void setEndDate(Date endDate) { this.endDate = endDate; }
}

package ltd.newbee.mall.entity;

import java.util.Date;

public class TbSale {
  private Long id;
  private Date startDate;
  private Date endDate;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Date getStartDate() { return startDate; }
  public void setStartDate(Date startDate) { this.startDate = startDate; }

  public Date getEndDate() { return endDate; }
  public void setEndDate(Date endDate) { this.endDate = endDate; }

  @Override
  public String toString() {
    return "TbSale{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + '}';
  }
}

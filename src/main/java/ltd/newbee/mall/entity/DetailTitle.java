package ltd.newbee.mall.entity;

public class DetailTitle {
  private Long id;
  private String name;
  private Double star;
  private String saveNum;
  private Integer commentNum;
  private String style;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Double getStar() { return star; }
  public void setStar(Double star) { this.star = star; }

  public String getSaveNum() { return saveNum; }
  public void setSaveNum(String saveNum) { this.saveNum = saveNum; }

  public Integer getCommentNum() {
    return commentNum;
  }

  public void setCommentNum(Integer commentNum) {
    this.commentNum = commentNum;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  @Override
  public String toString() {
    return "DetailTitle{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", star=" + star +
        ", saveNum='" + saveNum + '\'' +
        ", commentNum=" + commentNum +
        ", style='" + style + '\'' +
        '}';
  }
}

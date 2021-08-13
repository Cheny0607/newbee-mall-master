package ltd.newbee.mall.entity;

public class TopKodawari {
  private Long id;
  private String image;
  private String title;
  private String content;
  private String kodawariLabel;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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

  public String getKodawariLabel() {
    return kodawariLabel;
  }

  public void setKodawariLabel(String kodawariLabel) {
    this.kodawariLabel = kodawariLabel;
  }

  @Override
  public String toString() {
    return "TopKodawari{" +
        "id=" + id +
        ", image='" + image + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", kodawariLabel='" + kodawariLabel + '\'' +
        '}';
  }
}

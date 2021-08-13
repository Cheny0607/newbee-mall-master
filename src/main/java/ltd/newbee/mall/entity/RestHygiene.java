package ltd.newbee.mall.entity;

public class RestHygiene {
  private Long id;
  private String hygieneItemName1;
  private String hygieneItemName2;
  private String hygieneData1;
  private String hygieneData2;
  private String hygieneData3;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHygieneItemName1() {
    return hygieneItemName1;
  }

  public void setHygieneItemName1(String hygieneItemName1) {
    this.hygieneItemName1 = hygieneItemName1;
  }

  public String getHygieneItemName2() {
    return hygieneItemName2;
  }

  public void setHygieneItemName2(String hygieneItemName2) {
    this.hygieneItemName2 = hygieneItemName2;
  }

  public String getHygieneData1() {
    return hygieneData1;
  }

  public void setHygieneData1(String hygieneData1) {
    this.hygieneData1 = hygieneData1;
  }

  public String getHygieneData2() {
    return hygieneData2;
  }

  public void setHygieneData2(String hygieneData2) {
    this.hygieneData2 = hygieneData2;
  }

  public String getHygieneData3() {
    return hygieneData3;
  }

  public void setHygieneData3(String hygieneData3) {
    this.hygieneData3 = hygieneData3;
  }

  @Override
  public String toString() {
    return "RestHygiene{" +
        "id=" + id +
        ", hygieneItemName1='" + hygieneItemName1 + '\'' +
        ", hygieneItemName2='" + hygieneItemName2 + '\'' +
        ", hygieneData1='" + hygieneData1 + '\'' +
        ", hygieneData2='" + hygieneData2 + '\'' +
        ", hygieneData3='" + hygieneData3 + '\'' +
        '}';
  }
}

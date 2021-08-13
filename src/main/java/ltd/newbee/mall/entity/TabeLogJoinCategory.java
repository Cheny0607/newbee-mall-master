package ltd.newbee.mall.entity;

public class TabeLogJoinCategory {

  private Long id;
  private String stationCollectionId;
  private String genreCollectionId;
  private Long stationId;
  private String stationName;
  private Long genreId;
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStationCollectionId() {
    return stationCollectionId;
  }

  public void setStationCollectionId(String stationCollectionId) {
    this.stationCollectionId = stationCollectionId;
  }

  public String getGenreCollectionId() {
    return genreCollectionId;
  }

  public void setGenreCollectionId(String genreCollectionId) {
    this.genreCollectionId = genreCollectionId;
  }

  public Long getStationId() {
    return stationId;
  }

  public void setStationId(Long stationId) {
    this.stationId = stationId;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public Long getGenreId() {
    return genreId;
  }

  public void setGenreId(Long genreId) {
    this.genreId = genreId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "TabeLogCategory{" +
        "id=" + id +
        ", stationCollectionId='" + stationCollectionId + '\'' +
        ", genreCollectionId='" + genreCollectionId + '\'' +
        ", stationId=" + stationId +
        ", stationName='" + stationName + '\'' +
        ", genreId=" + genreId +
        ", name='" + name + '\'' +
        '}';
  }
}


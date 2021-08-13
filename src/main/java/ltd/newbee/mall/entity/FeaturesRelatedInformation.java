package ltd.newbee.mall.entity;

public class FeaturesRelatedInformation {

  private Long id;
  private String useScene;
  private String withChildren;
  private String homepage;
  private String officialAccount;
  private String phoneNum;
  private String remarks;
  private String relatedStoreInfo;
  private String firstContributor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUseScene() {
    return useScene;
  }

  public void setUseScene(String useScene) {
    this.useScene = useScene;
  }

  public String getWithChildren() {
    return withChildren;
  }

  public void setWithChildren(String withChildren) {
    this.withChildren = withChildren;
  }

  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  public String getOfficialAccount() {
    return officialAccount;
  }

  public void setOfficialAccount(String officialAccount) {
    this.officialAccount = officialAccount;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getRelatedStoreInfo() {
    return relatedStoreInfo;
  }

  public void setRelatedStoreInfo(String relatedStoreInfo) {
    this.relatedStoreInfo = relatedStoreInfo;
  }

  public String getFirstContributor() {
    return firstContributor;
  }

  public void setFirstContributor(String firstContributor) {
    this.firstContributor = firstContributor;
  }

  @Override
  public String toString() {
    return "FeaturesRelatedInformation{" +
        "id=" + id +
        ", useScene='" + useScene + '\'' +
        ", withChildren='" + withChildren + '\'' +
        ", homepage='" + homepage + '\'' +
        ", officialAccount='" + officialAccount + '\'' +
        ", phoneNum='" + phoneNum + '\'' +
        ", remarks='" + remarks + '\'' +
        ", relatedStoreInfo='" + relatedStoreInfo + '\'' +
        ", firstContributor='" + firstContributor + '\'' +
        '}';
  }
}


package ltd.newbee.mall.controller.vo;

import java.io.Serializable;

public class GoodsImageVO implements Serializable{

  private Long goodsId;
  private String path;


  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}

package ltd.newbee.mall.controller.admin;

/*import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.service.NewBeeMallGoodsService;

@SpringBootTest
class GoodsControllerTest{
  @Resource
  NewBeeMallGoodsService newBeeMallGoodsService;
  @Test
  public void testGoodsImageService(){
    long goodsId=10203L;
    List<GoodsImage> list = newBeeMallGoodsService.getImageList(goodsId);
    GoodsImage image = list.get(0);
    String path = image.getPath();
    assertEquals("/goods-img/18aca3b8-d024-47d3-a971-fb51d374b1ae.jpg",path);
  }

  @Test
  public void testGoodsReviewService(){
    long goodsId=10203L;
    List<GoodsReview> list = newBeeMallGoodsService.getReviewList(goodsId);
    GoodsReview review = list.get(0);
    String customerId = review.getCustomerId();
    assertEquals("1",customerId);

  }

  @Test
  public void testGoodsQaService(){
    long goodsId=10700L;
    List<GoodsQa> list = newBeeMallGoodsService.getQaList(goodsId);
    GoodsQa qa = list.get(0);
    String id = qa.getId();
    assertEquals("1",id);
    String question = qa.getQuestion();
    assertEquals("肌が暗くでも大丈夫ですか",question);
    String submitDate = qa.getSubmitDate();
    assertEquals("2020-01-08",submitDate);
    String answer = qa.getAnswer();
    assertEquals("大丈夫です",answer);
    String answerDate = qa.getAnswerDate();
    assertEquals("2020-1-10",answerDate);
    String helpedNum = qa.getHelpedNum();
    assertEquals("2",helpedNum);
  }

  @Test
  public void testGoodsDescService(){
    long goodsId=10203L;
    GoodsDesc goodsDesc = newBeeMallGoodsService.getGoodsDesc(goodsId);
    String color = goodsDesc.getColor();
    assertEquals("chilli",color);
  }
}*/



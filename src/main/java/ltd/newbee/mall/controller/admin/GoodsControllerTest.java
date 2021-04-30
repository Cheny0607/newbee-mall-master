package ltd.newbee.mall.controller.admin;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.annotation.Resource;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.service.NewBeeMallGoodsService;


//@SpringBootTest
//class GoodsControllerTest{
//  @Resource
//  NewBeeMallGoodsService newBeeMallGoodsService;
//  @Test
//  public void testGoodsImageService(){
//    long goodsId=10203L;
//    List<GoodsImage> list = newBeeMallGoodsService.getImageList(goodsId);
//    GoodsImage image = list.get(0);
//    String path = image.getPath();
//    assertEquals("/goods-img/18aca3b8-d024-47d3-a971-fb51d374b1ae.jpg",path);
//  }
//
//  @Test
//  public void testGoodsReviewService(){
//    long goodsId=10203L;
//    List<GoodsReview> list = newBeeMallGoodsService.getReviewList(goodsId);
//    GoodsReview review = list.get(0);
//    String customerId = review.getCustomerId();
//    assertEquals("1",customerId);
//
//  }
//
//  @Test
//  public void testGoodsQaService(){
//    long goodsId=10700L;
//    List<GoodsQa> list = newBeeMallGoodsService.getQaList(goodsId);
//    GoodsQa qa = list.get(0);
//    String id = qa.getId();
//    assertEquals("001",id);
//    String question = qa.getQuestion();
//    assertEquals("肌が暗くでも大丈夫ですか",question);
//    String submitDate = qa.getSubmitDate();
//    assertEquals("2020-01-08",submitDate);
//    String answer = qa.getAnswer();
//    assertEquals("大丈夫です",answer);
//    String answerDate = qa.getAnswerDate();
//    assertEquals("2020-1-10",answerDate);
//    String helpedNum = qa.getHelpedNum();
//    assertEquals("2",helpedNum);
//  }
//
//  @Test
//  public void testGoodsDescService(){
//    long goodsId=10203L;
//    GoodsDesc goodsDesc = newBeeMallGoodsService.getGoodsDesc(goodsId);
//    String color = goodsDesc.getColor();
//    assertEquals("chilli",color);
//  }
//
//  @Test
//  public void testGoodsQaPageService(){
//    Map<String, Object> params = new HashMap<String, Object>();
//    params.put("page","1");
//    params.put("limit","3");
//    PageQueryUtil pageUtil = new PageQueryUtil(params);
//    PageResult result = newBeeMallGoodsService.getGoodsQaPage(pageUtil);
//    List<GoodsQa> qaPageList = (List<GoodsQa>) result.getList();
//    int size = 0;
//    if (qaPageList !=null || !qaPageList.isEmpty()){
//      size = qaPageList.size();
//    }
//    assertEquals(3,size);
//
//    GoodsQa expect1 = new GoodsQa();
//    expect1.setId("001");
//    expect1.setQuestion("肌が暗くても大丈夫ですか");
//    expect1.setSubmitDate("2020-01-08");
//    expect1.setAnswer("大丈夫です");
//    expect1.setAnswerDate("2020-1-10");
//    expect1.setHelpedNum("2");
//    expect1.setGoodsId(10700L);
//    GoodsQa expect2 = new GoodsQa();
//    expect2.setId("002");
//    expect2.setQuestion("一人で運べますか");
//    expect2.setSubmitDate("2020-01-08");
//    expect2.setAnswer("余裕");
//    expect2.setAnswerDate("2020-1-10");
//    expect2.setHelpedNum("2");
//    expect2.setGoodsId(10700L);
//    GoodsQa expect3 = new GoodsQa();
//    expect3.setId("003");
//    expect3.setQuestion("重いですか");
//    expect3.setSubmitDate("2020-01-08");
//    expect3.setAnswer("全然");
//    expect3.setAnswerDate("2020-1-10");
//    expect3.setHelpedNum("1");
//    expect3.setGoodsId(10700L);
//    List<GoodsQa> expectList = new ArrayList<GoodsQa>();
//    expectList.add(expect1);
//    expectList.add(expect2);
//    expectList.add(expect3);
//    Boolean isTrue = qaPageList.equals(expectList);
//
//    assertEquals("001",qaPageList.get(0).getId());
//    assertEquals("002",qaPageList.get(1).getId());
//    assertEquals("003",qaPageList.get(2).getId());
//  }
//  @Test
//  public void testGoodsQaSortPageService(){
//    Map<String, Object> params = new HashMap<>();
//    params.put("page","1");
//    params.put("limit","3");
//    params.put("orderBy","submitDate");
//
// params.put("orderBy","helpedNum");
//
//    PageQueryUtil pageUtil = new PageQueryUtil(params);
//    PageResult result = newBeeMallGoodsService.getGoodsQaSortPage(pageUtil);
//    List<GoodsQa> qaSortList = (List<GoodsQa>) result.getList();
//    int size = 0;
//    if (qaSortList !=null || !qaSortList.isEmpty()){
//      size = qaSortList.size();
//    }
//    assertEquals(3,size);
//    assertEquals("2021-01-08",qaSortList.get(0).getSubmitDate());
//    assertEquals("2021-01-09",qaSortList.get(1).getSubmitDate());
//    assertEquals("2021-01-10",qaSortList.get(2).getSubmitDate());
//  }
//  @Test
//  public void testSaveGoodsQaService(){
//    GoodsQa qaInsertList = new GoodsQa();
//    qaInsertList.setId("005");
//    qaInsertList.setQuestion("きれいですか");
//    qaInsertList.setSubmitDate("2020-01-12");
//    qaInsertList.setAnswer("きれいです");
//    qaInsertList.setAnswerDate("2021-01-12");
//    qaInsertList.setGoodsId(10700L);
//    String rs = newBeeMallGoodsService.saveGoodsQa(qaInsertList);
//    assertEquals(ServiceResultEnum.SUCCESS.getResult(),rs);
//  }
//
//}



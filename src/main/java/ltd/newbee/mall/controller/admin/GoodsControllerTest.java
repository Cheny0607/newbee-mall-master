package ltd.newbee.mall.controller.admin;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.annotation.Resource;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.DetailTitle;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.TbGenre;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
//  NewBeeMallGoodsMapper newBeeMallGoodsMapper;
//  @Autowired
//  private NewBeeMallGoodsMapper goodsMapper;
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
//    Long id = qa.getId();
//    assertEquals("001",id);
//    String question = qa.getQuestion();
//    assertEquals("肌が暗くでも大丈夫ですか",question);
//    Date submitDate = qa.getSubmitDate();
//    assertEquals("2020-01-08",submitDate);
//    String answer = qa.getAnswer();
//    assertEquals("大丈夫です",answer);
//    Date answerDate = qa.getAnswerDate();
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
//    expect1.setId(001L);
//    expect1.setQuestion("肌が暗くても大丈夫ですか");
//    expect1.setSubmitDate(2020-01-08);
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
//    params.put("orderBy","helpedNum");
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
//    qaInsertList.setId(005L);
//    qaInsertList.setQuestion("きれいですか");
//    qaInsertList.setAnswer("きれいです");
//    qaInsertList.setGoodsId(10700L);
//    Integer rs = newBeeMallGoodsService.insertQaSelective(qaInsertList);
//    assertEquals(ServiceResultEnum.SUCCESS.getResult(),rs);
//  }
//
//  @Test
//  public void getGsList(){
//    Integer[] ids = {1};
//    List<GoodsSale> list = newBeeMallGoodsService.getGoodsSale(ids);
//    GoodsSale gsList = list.get(0);
//    String name = gsList.getName();
//    assertEquals("夏季大甩卖",name);
//    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
//    Date startDate = gsList.getStartDate();
//    assertEquals("2021-03-31",startDate);
//    Date endDate = gsList.getEndDate();
//    assertEquals("2021-06-30",endDate);
//    String campagin = gsList.getCampaign();
//    assertEquals("满三百减一百",campagin);
//  }
//
//  @Test
//  public void pagingGoodsSale(){
//    Map<String, Object> params = new HashMap<>();
//    params.put("page",1);
//    params.put("limit",3);
//    params.put("orderBy","startDate");
//    params.put("descAsc","desc");
//    params.put("keyword","夏季大甩卖");
//
//    PageQueryUtil pageUtil = new PageQueryUtil(params);
//    PageResult result = newBeeMallGoodsService.pagingGoodsSale(pageUtil);
//    List<GoodsSale> gsList = (List<GoodsSale>) result.getList();
//    int size = 0;
//    if (gsList !=null || !gsList.isEmpty()){
//      size = gsList.size();
//    }
//    assertEquals(1,size);
//
//    Long a = 1L;
//    Long b = 2L;
//    Long c = 1L;
//    assertEquals(a,gsList.get(2).getId());
//    assertEquals(b,gsList.get(1).getId());
//    assertEquals(c,gsList.get(0).getId());
//
//    assertEquals("夏季大甩卖",gsList.get(2).getName());
//    assertEquals("换季清仓",gsList.get(1).getName());
//    assertEquals("夏季大甩卖",gsList.get(0).getName());
//
//    assertEquals("满三百减一百",gsList.get(2).getCampaign());
//    assertEquals("两件九折",gsList.get(1).getCampaign());
//    assertEquals("满三百减一百",gsList.get(0).getCampaign());
//
//    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
//    String starDate1 = sdFormat.format(gsList.get(2).getStartDate());
//    String starDate2 = sdFormat.format(gsList.get(1).getStartDate());
//    String starDate3 = sdFormat.format(gsList.get(0).getStartDate());
//
//    assertEquals("2021-02-07",starDate2);
//    assertEquals("2021/03/31",starDate3);
//    assertEquals("2021-03-31",starDate1);
//  }

//  @Test
//  public void getDetailTitle(){
//    Long resId = 1L;
//    DetailTitle detailTitle = goodsMapper.getDetailTitle(resId);
//    String name = detailTitle.getName();
//    String star = detailTitle.getStar();
//    String score = detailTitle.getScore();
//    String commentNum = detailTitle.getCommentNum();
//    String saveNum = detailTitle.getSaveNum();
//    assertEquals("焼肉",name);
//    assertEquals("4",star);
//    assertEquals("4.12",score);
//    assertEquals("132",commentNum);
//    assertEquals("145",saveNum);
//  }
//
//  @Test
//  public void getDetailSubTitle(){
//    Long resId = 1L;
//    RestaurantDesc subTitle = goodsMapper.getDetailSubTitle(resId);
//    String nearbyStation = subTitle.getNearbyStation();
//    String nightBudget = subTitle.getNightBudget();
//    String dayBudget = subTitle.getDayBudget();
//    String restDay = subTitle.getRestDay();
//    String genreName = subTitle.getGenreName();
//    assertEquals("東京駅",nearbyStation);
//    assertEquals("3000~4000",nightBudget);
//    assertEquals("990~",dayBudget);
//    assertEquals("無",restDay);
//    assertEquals("居酒屋",genreName);
//  }
//
//  @Test
//  public void getGenreList(){
//    Long genreId = 1L;
//    List<TbGenre> genreList = goodsMapper.getGenreList(genreId);
//    TbGenre gL = genreList.get(0);
//    String name = gL.getName1();
//    assertEquals("焼肉",name);
//  }
//
//  @Test
//  public void getGenreCategoryList(){
//    Long categoryId = 1L;
//    List<TabeLogCategory> categoryList = goodsMapper.getGenreCategoryList(categoryId);
//    TabeLogCategory cL = categoryList.get(0);
//    String name = cL.getCategoryName();
//    assertEquals("東京駅・焼肉",name);
//  }
//
//}



package ltd.newbee.mall.controller.mall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import ltd.newbee.mall.common.IndexConfigTypeEnum;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.DetailTitle;
import ltd.newbee.mall.entity.FeaturesRelatedInformation;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpedNum;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.MenuCourse;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.PagingQa;
import ltd.newbee.mall.entity.RestHygiene;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.SeatFacility;
import ltd.newbee.mall.entity.TabeLogJoinCategory;
import ltd.newbee.mall.entity.TabelogBasicInformation;
import ltd.newbee.mall.entity.TabelogGoodNum;
import ltd.newbee.mall.entity.TbComment;
import ltd.newbee.mall.entity.TbGenre;
import ltd.newbee.mall.entity.TopCommentImage;
import ltd.newbee.mall.entity.TopCoupon;
import ltd.newbee.mall.entity.TopCourse;
import ltd.newbee.mall.entity.TopImage;
import ltd.newbee.mall.entity.TopKodawari;
import ltd.newbee.mall.entity.TopPage;
import ltd.newbee.mall.service.NewBeeMallCarouselService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.service.NewBeeMallIndexConfigService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCategoryVO;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class RestApiIndexController {
  @Resource
  private NewBeeMallCategoryService newBeeMallCategoryService;
  @Resource
  private NewBeeMallCarouselService newBeeMallCarouselService;
  @Resource
  private NewBeeMallIndexConfigService newBeeMallIndexConfigService;
  @Resource
  private NewBeeMallGoodsService newBeeMallGoodsService;

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/categories", method = RequestMethod.POST)
  @ResponseBody
  public Result categories() {

    List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();
    if (CollectionUtils.isEmpty(categories)) {
      return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR, Constants.CATEGORY_FETCH_ERROR_MESSAGE);
    }else {
      return ResultGenerator.genSuccessResult(categories);
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/carousels", method = RequestMethod.POST)
  @ResponseBody
  public Result carousels() {
    List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
    if (CollectionUtils.isEmpty(carousels)) {
      return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR, Constants.CATEGORY_FETCH_ERROR_MESSAGE);
    }else{
        return ResultGenerator.genSuccessResult(carousels);
      }
    }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/hotGoodses", method = RequestMethod.POST)
  @ResponseBody
  public Result hotGoodses() {
    List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
    if (CollectionUtils.isEmpty(hotGoodses)) {
      return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR, Constants.CATEGORY_FETCH_ERROR_MESSAGE);
    }else{
      return ResultGenerator.genSuccessResult(hotGoodses);
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/newAndRecommendGoodses", method = RequestMethod.POST)
  @ResponseBody
  public Result newGoodses() {
    Map<Object,List> result = new HashMap<>();
    List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
    List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
    result.put("newGoodses",newGoodses);
    result.put("recommendGoodses",recommendGoodses);
    return ResultGenerator.genSuccessResult(result);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/goodsDetail", method = RequestMethod.POST)
  @ResponseBody
  public Result goodsDetail(Long goodsId) {
    NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(goodsId);
    if (goods == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(goods);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(value = "/smDetailPage/{goodsId}")
  @ResponseBody
  public Result goodsDetailImage(@PathVariable("goodsId") Long goodsId) {
    List<GoodsImage> imageList = newBeeMallGoodsService.getImageList(goodsId);
    if (imageList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(imageList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(value = "/goodsDesc/{goodsId}")
  @ResponseBody
  public Result goodsDesc(@PathVariable("goodsId") Long goodsId) {
    GoodsDesc desc = newBeeMallGoodsService.getGoodsDesc(goodsId);
    if (desc == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(desc);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(value = "/qaList/{goodsId}")
  @ResponseBody
  public Result qaList(@PathVariable("goodsId") Long goodsId) {
    List<GoodsQa> qaList = newBeeMallGoodsService.getQaList(goodsId);
    if (qaList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(qaList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(value = "/reviewList/{goodsId}")
  @ResponseBody
  public Result reviewList(@PathVariable("goodsId") Long goodsId) {
    List<GoodsReview> reviewList = newBeeMallGoodsService.getReviewList(goodsId);
    if (reviewList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(reviewList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/qaPaging")
  @ResponseBody
  public Result getGoodsQaSortPage(@RequestBody PagingQa page) {
    if(Integer.parseInt(page.getPage()) == -1){
      Map<String, Object> par = new HashMap<>();
      par.put("page", 1);
      par.put("limit", 3);
      PageQueryUtil pU = new PageQueryUtil(par);
      PageResult res = newBeeMallGoodsService.getGoodsQaSortPage((pU));
      int a = res.getCurrPage();
      a = res.getTotalPage();
      Map<String, Object> pa = new HashMap<>();
      pa.put("page", a);
      pa.put("limit", 3);
      PageQueryUtil p = new PageQueryUtil(pa);
      PageResult re = newBeeMallGoodsService.getGoodsQaSortPage((p));
//      int currentPage = (int) Math.ceil((double)  res.getTotalCount() / res.getPageSize());
      return ResultGenerator.genSuccessResult(re);
    } else {
      Map<String, Object> params = new HashMap<>();
      params.put("page",page.getPage());
      params.put("limit",Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
      params.put("orderBy","submitDate");
      PageQueryUtil pageUtil = new PageQueryUtil(params);
      PageResult result = newBeeMallGoodsService.getGoodsQaSortPage(pageUtil);
      return ResultGenerator.genSuccessResult(result);
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/showMoreReviews", method = RequestMethod.POST)
  @ResponseBody
  public Result getGoodsReview(@RequestBody  GoodsReview goodsReview) {
    Map<String, Object> params = new HashMap<>();
    params.put("page", 1);
    params.put("goodsId", goodsReview.getGoodsId());
    params.put("limit",Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
//    params.put("orderBy", GoodsReview.getStar());
    PageQueryUtil pageUtil = new PageQueryUtil(params);
    PageResult initialReviewList = newBeeMallGoodsService.getLimitGoodsReview((pageUtil));
    if (goodsReview.getIds() == null) {
      initialReviewList.setInitial(true);
      return ResultGenerator.genSuccessResult(initialReviewList);
    } else {
      List<GoodsReview> detailReviewList =newBeeMallGoodsService.getReviewList(goodsReview.getGoodsId());
      List<GoodsReview> restReviewList = new ArrayList<GoodsReview>();
      Integer[] reviewIds = goodsReview.getIds();
      List<Integer> reviewListB = Arrays.asList(reviewIds);
      restReviewList =detailReviewList.stream().filter(el->!reviewListB.contains(el.getId())).collect(Collectors.toList());
      PageResult pageResult = new PageResult(restReviewList, 0, pageUtil.getLimit(), pageUtil.getPage());
      pageResult.setInitial(false);
      return ResultGenerator.genSuccessResult(pageResult);
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/insertQa", method = RequestMethod.POST)
  @ResponseBody
  public Result insertQaSelective(@RequestBody GoodsQa qa){
    Integer count = null;
    Long qaId = newBeeMallGoodsService.getMaxQaId(qa.getGoodsId());
    qa.setId(qaId);
    Date submitDate = new Date();
    qa.setSubmitDate(submitDate);
    if(qa !=null){
      count = newBeeMallGoodsService.insertQaSelective(qa);
    }
    if(!(count > 0)){
      return ResultGenerator.genFailResult("投稿失敗");
    }
    return ResultGenerator.genSuccessResult(count);
  }

  //reviewHelpedNum
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/reviewHelpedNum",method = RequestMethod.POST)
  @ResponseBody
  public Result insertHelpedNum(@RequestBody GoodsReviewHelpedNum goodsReviewHelpedNum, HttpSession httpSession){
    NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
    if(user !=null){
      goodsReviewHelpedNum.setUserId(user.getUserId());
    }
    boolean addFlag = newBeeMallGoodsService.insertHelpedNum(goodsReviewHelpedNum);
    if(addFlag){
      boolean updateFlag = newBeeMallGoodsService.updateReviewNum(goodsReviewHelpedNum);
      if(updateFlag){
        long reviewNum = newBeeMallGoodsService.getGoodsReviewNum(goodsReviewHelpedNum.getReviewId());
        return ResultGenerator.genSuccessResult(reviewNum);
      }else {
        return ResultGenerator.genFailResult("改修失敗");
      }
    }else {
    }return ResultGenerator.genFailResult("挿入失敗");
  }

  //added by c 2021/7/20 tabe-log
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/detailTitle")
  @ResponseBody
  public Result getDetailTitle(@RequestBody DetailTitle detailTitle) {
    DetailTitle detailTitleList = newBeeMallGoodsService.getDetailTitle(detailTitle.getId());
    double averageStar =newBeeMallGoodsService.getAvgStar();
    int totalCount=newBeeMallGoodsService.getTotalRestComment();
    detailTitleList.getStar();
    detailTitleList.setStar(averageStar);
    detailTitleList.getCommentNum();
    detailTitleList.setCommentNum(totalCount);
    if (detailTitleList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(detailTitleList);
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/subTitle")
  @ResponseBody
  public Result getDetailSubTitle(@RequestBody RestaurantDesc restaurantDesc) {
    RestaurantDesc desc = newBeeMallGoodsService.getDetailSubTitle(restaurantDesc.getId());
    if (desc == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(desc);
  }

  //added by c 2021/7/28 get join category
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/genreList")
  @ResponseBody
  public Result getJoinCategoryList(@RequestBody TabeLogJoinCategory tabeLogJoinCategory) {
    List<TabeLogJoinCategory> joinList = newBeeMallGoodsService.getJoinCategoryList(tabeLogJoinCategory.getId());
    if (joinList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(joinList);
  }

  //added by c 2021/7/29 top page
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/topPageList")
  @ResponseBody
  public Result getTopPage(@RequestBody TopPage topPage) {
    List<TopPage> topPageList = newBeeMallGoodsService.getTopPage(topPage.getId());
    if (topPageList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(topPageList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/topImageList")
  @ResponseBody
  public Result getTopImage(@RequestBody TopImage topImage) {
    List<TopImage> imageList = newBeeMallGoodsService.getTopImage(topImage.getId());
    if (imageList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(imageList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/topKodawariList")
  @ResponseBody
  public Result getTopKodawari(@RequestBody TopKodawari topKodawari) {
    List<TopKodawari> kodawariList = newBeeMallGoodsService.getTopKodawari(topKodawari.getId());
    if (kodawariList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(kodawariList);
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/restHygieneList")
  @ResponseBody
  public Result getRestHygiene(@RequestBody RestHygiene restHygiene) {
    List<RestHygiene> restHygieneList = newBeeMallGoodsService.getRestHygiene(restHygiene.getId());
    if (restHygieneList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(restHygieneList);
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/topCourseList")
  @ResponseBody
  public Result getTopCourse(@RequestBody TopCourse topCourse) {
    List<TopCourse> topCourseList = newBeeMallGoodsService.getTopCourse(topCourse.getId());
    if (topCourseList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(topCourseList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/topCouponList")
  @ResponseBody
  public Result getTopCoupon(@RequestBody TopCoupon topCoupon) {
    List<TopCoupon> topCouponList = newBeeMallGoodsService.getTopCoupon(topCoupon.getId());
    if (topCouponList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(topCouponList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/commentImageList")
  @ResponseBody
  public Result getTopCommentImage(@RequestBody TopCommentImage topCommentImage) {
    List<TopCommentImage> commentImageList = newBeeMallGoodsService.getTopCommentImage(topCommentImage.getId());
    if (commentImageList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(commentImageList);
  }

  //comment good num
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/tabelogHelpedNum",method = RequestMethod.POST)
  @ResponseBody
  public Result insertTabelogHelpedNum(@RequestBody TabelogGoodNum tabelogGoodNum, HttpSession httpSession){
    NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
    if(user !=null){
      tabelogGoodNum.setUserId(user.getUserId());
    }
    boolean addFlag = newBeeMallGoodsService.insertTabelogHelpedNum(tabelogGoodNum);
    if(addFlag){
      boolean updateFlag = newBeeMallGoodsService.updateCommentNum(tabelogGoodNum);
      if(updateFlag){
        long commentNum = newBeeMallGoodsService.getCommentNum(tabelogGoodNum.getCommentId());
        return ResultGenerator.genSuccessResult(commentNum);
      }else {
        return ResultGenerator.genFailResult("改修失敗");
      }
    }else {
    }return ResultGenerator.genFailResult("挿入失敗");
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/basicInfoList")
  @ResponseBody
  public Result getTopBasicInfo(@RequestBody TabelogBasicInformation tabelogBasicInformation) {
    List<TabelogBasicInformation> basicInfoList = newBeeMallGoodsService.getTopBasicInfo(tabelogBasicInformation.getId());
    if (basicInfoList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(basicInfoList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/seatInfoList")
  @ResponseBody
  public Result getTopSeatInfo(@RequestBody SeatFacility seatFacility) {
    List<SeatFacility> seatInfoList = newBeeMallGoodsService.getTopSeatInfo(seatFacility.getId());
    if (seatInfoList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(seatInfoList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/menuCourseList")
  @ResponseBody
  public Result getMenuCourse(@RequestBody MenuCourse menuCourse) {
    List<MenuCourse> menuCourseList = newBeeMallGoodsService.getMenuCourse(menuCourse.getId());
    if (menuCourseList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(menuCourseList);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/relatedInfoList")
  @ResponseBody
  public Result getTopRelatedInfo(@RequestBody FeaturesRelatedInformation featuresRelatedInformation) {
    List<FeaturesRelatedInformation> relatedInfoList = newBeeMallGoodsService.getTopRelatedInfo(featuresRelatedInformation.getId());
    if (relatedInfoList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    return ResultGenerator.genSuccessResult(relatedInfoList);
  }
}



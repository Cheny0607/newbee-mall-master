package ltd.newbee.mall.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallCategoryLevelEnum;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsImageVO;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.controller.vo.TbCategoryVO;
import ltd.newbee.mall.entity.CampaignSet;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.MainCategory;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.TbCategory;
import ltd.newbee.mall.entity.TbSale;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class CategoryController {

  @Resource
  private NewBeeMallCategoryService newBeeMallCategoryService;
  @Resource
  private NewBeeMallGoodsService newBeeMallGoodsService;

  //added by c 2021/5/17
  @GetMapping({"/category", "/category.html"})
  public String getMainCategory(HttpServletRequest request, Long categoryId) {
    List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService
        .selectByLevelAndParentIdsAndNumber(
            Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
    List<MainCategory> joinList = newBeeMallCategoryService.findFirstLevel(categoryId);
    List<GoodsSale> gsList = newBeeMallCategoryService.getGoodsSale();
    request.setAttribute("joinList", joinList);
    request.setAttribute("gsList", gsList);
    request.setAttribute("firstLevelCategories", firstLevelCategories);
    return "admin/category";
  }

  @RequestMapping(value = "/check/event", method = RequestMethod.POST)
  @ResponseBody
  public Result insertCampaign(@RequestBody TbCategory tbCategory) {
    Boolean delete = newBeeMallCategoryService.deleteCampaign(tbCategory.getCategoryId());
    TbCategory tbList = new TbCategory();
    Integer count = null;
    tbList.setId(tbCategory.getId());
    tbList.setCategoryId(tbCategory.getCategoryId());
    tbList.setStartDate(tbCategory.getStartDate());
    tbList.setEndDate(tbCategory.getEndDate());
    Boolean compare = newBeeMallCategoryService.compareCampaign(tbCategory);
    if(!tbCategory.getFlag()){
      if(delete){
        return ResultGenerator.genSuccessResult();
      }
      return ResultGenerator.genFailResult("删除失败");
    } else {
      if (compare) {
        if (tbCategory != null) {
          count = newBeeMallCategoryService.insertCampaign(tbList);
        }
        if (!(count > 0)) {
          return ResultGenerator.genFailResult("投稿失敗");
        }
        return ResultGenerator.genSuccessResult(count);
      }
      return ResultGenerator.genFailResult("期間外です");
    }
  }

//  //popUp
//  @RequestMapping(value = "/popUp/page",method = RequestMethod.POST)
//  @ResponseBody
//  public Result selectParentId(@RequestBody Long categoryId){
////    Boolean flag;
//    Long parentId = categoryId;
//    Long goodsCategoryId = categoryId;
//    List<GoodsSale> gsList = newBeeMallCategoryService.getGoodsSale();
//    List<MainCategory> subCategoryList = newBeeMallCategoryService.selectParentId(parentId);
//    List<NewBeeMallGoods> goodsList = newBeeMallGoodsService.findGiftCategoryId(goodsCategoryId);
//    Map<Object,List> result = new HashMap<>();
//    result.put("gsList",gsList);
//    result.put("subCategoryList",subCategoryList);
//    result.put("goodsList",goodsList);
//    result.put("gsList",gsList);
////    result.put("list",goodsList);
//    return ResultGenerator.genSuccessResult(result);
//  }
  //popUp
  @RequestMapping(value = "/popUp/page",method = RequestMethod.POST)
  @ResponseBody
  public Result selectParentId(@RequestBody Long categoryId){
    Map<Object,List> result = new HashMap<>();
    Long goodsCategoryId = categoryId;
    Long parentId = categoryId;
    List<GoodsSale> gsList = newBeeMallCategoryService.getGoodsSale();
    List<MainCategory> subCategoryList = newBeeMallCategoryService.selectParentId(parentId);
    List<NewBeeMallGoods> goodsList = newBeeMallGoodsService.findGiftCategoryId(goodsCategoryId);
    if (!goodsList.isEmpty()) {
      result.put("list",goodsList);
      result.put("gsList",gsList);
    }else {
      result.put("gsList",gsList);
      result.put("list",subCategoryList);
    }
    return ResultGenerator.genSuccessResult(result);
  }

  //modal dropDownList
  @RequestMapping(value = "/giftGoods",method = RequestMethod.POST)
  @ResponseBody
  public Result findGiftCategoryId(@RequestBody Long goodsCategoryId){
    List<NewBeeMallGoods> goodsList = newBeeMallGoodsService.findGiftCategoryId(goodsCategoryId);
    return ResultGenerator.genSuccessResult(goodsList);
  }

  //modal insert
  @RequestMapping(value = "/campaignSet/insert",method = RequestMethod.POST)
  @ResponseBody
  public Result insertCampaignSet(@RequestBody CampaignSet campaignSet){
    Integer count = null;
    Integer countTbSale = null;
    Long id = newBeeMallCategoryService.getMaxId();
    campaignSet.setId(id);
    TbSale tbList = new TbSale();
    tbList.setId(campaignSet.getCampaignId());
    tbList.setGoodsId(campaignSet.getPrimaryGoodsId());
    tbList.setStartDate(campaignSet.getStartDate());
    tbList.setEndDate(campaignSet.getEndDate());
    if(tbList !=null){
      countTbSale = newBeeMallGoodsService.insertTbSale(tbList);
    }
    if(campaignSet !=null){
      count = newBeeMallCategoryService.insertCampaignSet(campaignSet);
    }
    if(!(count > 0)){
      return ResultGenerator.genFailResult("投稿失敗");
    }
    return ResultGenerator.genSuccessResult(count);
  }
}

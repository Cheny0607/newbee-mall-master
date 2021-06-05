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

  @RequestMapping(value = "/campaign/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result deleteCampaign(@RequestBody Long categoryId) {
    if (newBeeMallCategoryService.deleteCampaign(categoryId)) {
      return ResultGenerator.genSuccessResult();
    } else {
      return ResultGenerator.genFailResult("删除失败");
    }
  }
  @RequestMapping(value = "/campaign/save", method = RequestMethod.POST)
  @ResponseBody
  public Result insertCampaign(@RequestBody TbCategory tbCategory) {
    Integer count = null;
    Boolean compare = newBeeMallCategoryService.compareCampaign(tbCategory);
    if (compare) {
      if (tbCategory != null) {
        count = newBeeMallCategoryService.insertCampaign(tbCategory);
      }
      if (!(count > 0)) {
        return ResultGenerator.genFailResult("投稿失敗");
      }
      return ResultGenerator.genSuccessResult(count);
    }
    return ResultGenerator.genFailResult("期間外です");
  }

  //added by c modal
  @RequestMapping(value = "/campaignSet/insert",method = RequestMethod.POST)
  @ResponseBody
  public Result insertCampaignSet(@RequestBody CampaignSet campaignSet){
    Integer count = null;
    CampaignSet csList = new CampaignSet();
    Long id = newBeeMallCategoryService.getMaxId();
    csList.setId(id);
    csList.setPrimaryGoodsId(campaignSet.getPrimaryGoodsId());
    csList.setSubGoodsId(campaignSet.getSubGoodsId());
    if(csList !=null){
      count = newBeeMallCategoryService.insertCampaignSet(csList);
    }
    if(!(count > 0)){
      return ResultGenerator.genFailResult("投稿失敗");
    }
    return ResultGenerator.genSuccessResult(count);
  }
  //modal dropDownList
  @RequestMapping(value = "/giftGoods",method = RequestMethod.POST)
  @ResponseBody
  public Result findListByGoodsId(@RequestBody Long goodsId){
    List<NewBeeMallGoods> goodsList = newBeeMallGoodsService.findListByGoodsId(goodsId);
    return ResultGenerator.genSuccessResult(goodsList);
  }

  //popUp
  @RequestMapping(value = "/popUp/page",method = RequestMethod.POST)
  @ResponseBody
  public Result selectParentId(@RequestBody Long categoryId){
    MainCategory list = new MainCategory();
    list.setParentId(categoryId);
    List<GoodsSale> gsList = new ArrayList<GoodsSale>();
    List<MainCategory> subCategoryList = newBeeMallCategoryService.selectParentId(list.getParentId());
    for (int i=0; i<subCategoryList.size();i++){
      if(subCategoryList.get(i).getId()!=null){
        List<GoodsSale> c = newBeeMallCategoryService.selectGoodsSale(subCategoryList.get(i).getId());
        gsList.addAll(c);
      }
    }
    Map<Object,List> result = new HashMap<>();
    result.put("subCategoryList",subCategoryList);
    result.put("gsList",gsList);
    return ResultGenerator.genSuccessResult(result);
  }
}

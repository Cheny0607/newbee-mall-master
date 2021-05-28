package ltd.newbee.mall.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsImageVO;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.controller.vo.TbCategoryVO;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.TbCategory;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class CategoryController {

  @Resource
  private NewBeeMallGoodsService newBeeMallGoodsService;

  //added by c 2021/5/17
  @GetMapping({"/category", "/category.html"})
 /* public String pagingGoodsSale(@RequestParam Map<String, Object> params,
      HttpServletRequest request) {
    if (StringUtils.isEmpty(params.get("page"))) {
      params.put("page", 1);
    }
    params.put("limit", 5);
    *//*params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);*//*
    //封装参数供前端回显
    if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
      request.setAttribute("orderBy", params.get("orderBy") + "");
    }
    String keyword = "";
    //对keyword做过滤 去掉空格
    if (params.containsKey("keyword") && !StringUtils
        .isEmpty((params.get("keyword") + "").trim())) {
      keyword = params.get("keyword") + "";
    }
    request.setAttribute("keyword", keyword);
    params.put("keyword", keyword);
    //封装商品数据
    PageQueryUtil pageUtil = new PageQueryUtil(params);
    request.setAttribute("pageResult", newBeeMallGoodsService.pagingGoodsSale(pageUtil));
    return "admin/category";
  }

  @GetMapping("/category")*/
  public String detailCategory(@RequestBody Long id, HttpServletRequest request) {
    //added by c 2021/05/28 getTbCategory
    List<TbCategory> categoryList = newBeeMallGoodsService.getTbCategory(id);
    if (categoryList == null) {
      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
    }
    List<TbCategoryVO> categoryVoList = new ArrayList<TbCategoryVO>();
    for(int i = 0; i < categoryList.size();i++) {
      TbCategory category = new TbCategory();
      category = categoryList.get(i);
      if (category !=null) {
        TbCategoryVO categoryVo = new TbCategoryVO();
        BeanUtil.copyProperties(category, categoryVo);
        categoryVoList.add(categoryVo);
      }
    }
    request.setAttribute("saleCategoryDetail", categoryVoList);
    return "admin/category";
  }
}

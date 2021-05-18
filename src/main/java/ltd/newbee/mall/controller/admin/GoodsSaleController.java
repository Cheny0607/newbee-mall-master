package ltd.newbee.mall.controller.admin;


import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class GoodsSaleController {
  @Resource
  private NewBeeMallGoodsService newBeeMallGoodsService;

  //added by c 2021/5/17
  @GetMapping("/goods/sale")
  public String pagingGoodsSale(@RequestParam Map<String, Object> params, HttpServletRequest request) {
    if (StringUtils.isEmpty(params.get("page"))) {
      params.put("page", 1);
    }
    params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
    //封装参数供前端回显
    if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
      request.setAttribute("orderBy", params.get("orderBy") + "");
    }
    String keyword = "";
    //对keyword做过滤 去掉空格
    if (params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword") + "").trim())) {
      keyword = params.get("keyword") + "";
    }
    request.setAttribute("keyword", keyword);
    params.put("keyword", keyword);
    //封装商品数据
    PageQueryUtil pageUtil = new PageQueryUtil(params);
    request.setAttribute("pageResult", newBeeMallGoodsService.pagingGoodsSale(pageUtil));
    return "admin/sale";
  }
}

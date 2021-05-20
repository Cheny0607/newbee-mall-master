package ltd.newbee.mall.controller.admin;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsQaVO;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.GoodsSaleVO;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/admin")
public class GoodsSaleController {
  @Resource
  private NewBeeMallGoodsService newBeeMallGoodsService;

  //added by c 2021/5/17
  @GetMapping( "/goods/sale")
/*  @ResponseBody*/
  public String pagingGoodsSale(@RequestParam Map<String, Object> params, HttpServletRequest request) {
    if (StringUtils.isEmpty(params.get("page"))) {
      params.put("page",1);
    }
    params.put("limit", 3);
    /*params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);*/
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

//    Map<String, Object> c = new HashMap<>();
//    c.put("page","1");
//    c.put("limit","3");
//    c.put("orderBy","end_date");
//    c.put("descAsc","asc");
//
//    PageQueryUtil gsPageUtil = new PageQueryUtil(c);
//    PageResult gsResult = newBeeMallGoodsService.pagingGoodsSale(pageUtil);
//    List<GoodsSale> gsList = (List<GoodsSale>) gsResult.getList();
//    if(gsList == null) {
//      NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
//    }
//    List<GoodsSaleVO> gsVoList = new ArrayList<GoodsSaleVO>();
//    for(int i = 0; i < gsList.size();i++) {
//      GoodsSale gs = new GoodsSale();
//      gs = gsList.get(i);
//      if (gs !=null) {
//        GoodsSaleVO gsVo = new GoodsSaleVO();
//        BeanUtil.copyProperties(gs, gsVo);
//        gsVoList.add(gsVo);
//
//        SimpleDateFormat sdFormat = new SimpleDateFormat();
//        sdFormat.applyPattern("yyyy-MM-dd");
//        Date newStartDate = new Date();
//        Date newEndDate = new Date();
//        gs.setStartDate(newStartDate);
//        gs.setEndDate(newEndDate);
//      }
//    }
//    request.setAttribute("goodsSaleDetail", gsVoList);
    return "admin/sale";
  }

}

/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

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
import ltd.newbee.mall.controller.vo.GoodsDescVO;
import ltd.newbee.mall.controller.vo.GoodsImageVO;
import ltd.newbee.mall.controller.vo.GoodsQaVO;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.controller.vo.SearchPageCategoryVO;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.PagingQa;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
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
public class GoodsController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        if (params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")) {
            Long categoryId = Long.valueOf(params.get("goodsCategoryId") + "");
            SearchPageCategoryVO searchPageCategoryVO = newBeeMallCategoryService.getCategoriesForSearch(categoryId);
            if (searchPageCategoryVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }
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
        //搜索上架状态下的商品
        params.put("goodsSellStatus",Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", newBeeMallGoodsService.searchNewBeeMallGoods(pageUtil));
        return "mall/search";
    }

    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(goodsId);
        if (goods == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        NewBeeMallGoodsDetailVO goodsDetailVO = new NewBeeMallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", goodsDetailVO);


        //added by c 2021/4/19 imageVO
        List<GoodsImage> imageList = newBeeMallGoodsService.getImageList(goodsId);
        if (imageList == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        List<GoodsImageVO> imageVoList = new ArrayList<GoodsImageVO>();
        for(int i = 0; i < imageList.size(); i++) {
            GoodsImage image = new GoodsImage();
            image = imageList.get(i);
            if (image !=null){
                String path = image.getPath();
                GoodsImageVO imageVo = new GoodsImageVO();
                imageVo.setPath(path);
                imageVoList.add(imageVo);
            }
        }
        //added by c 2021/4/20 qaVO
       /* BeanUtil.copyList(qaList,GoodsQaVO.class);*/
        Map<String, Object> params = new HashMap<>();
        params.put("page","1");
        params.put("limit","3");
        params.put("orderBy","submit_date");

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult result = newBeeMallGoodsService.getGoodsQaSortPage(pageUtil);
        List<GoodsQa> qaList = (List<GoodsQa>) result.getList();


        //List<GoodsQa> qaList =  newBeeMallGoodsService.getQaList(goodsId);
        if(qaList == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        List<GoodsQaVO> qaVoList = new ArrayList<GoodsQaVO>();
        for(int i = 0; i < qaList.size();i++) {
            GoodsQa qa = new GoodsQa();
            qa = qaList.get(i);
            if (qa !=null) {
                GoodsQaVO qaVo = new GoodsQaVO();
                BeanUtil.copyProperties(qa, qaVo);
                qaVoList.add(qaVo);
            }
        }

        //added by c 2021/4/20 reviewVO
        List<GoodsReview> reviewList = newBeeMallGoodsService.getReviewList(goodsId);
        if (reviewList == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        List<GoodsReviewVO> reviewVoList = new ArrayList<GoodsReviewVO>();
        for(int i = 0; i < reviewList.size();i++) {
            GoodsReview review = new GoodsReview();
            review = reviewList.get(i);
            if (review !=null) {
                GoodsReviewVO reviewVo = new GoodsReviewVO();
                BeanUtil.copyProperties(review, reviewVo);
                reviewVoList.add(reviewVo);
            }
        }
       /* BeanUtil.copyList(reviewList,GoodsReviewVO.class);*/

        //added by c 2021/4/20 descVO
        GoodsDesc desc = newBeeMallGoodsService.getGoodsDesc(goodsId);
        if (desc == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        GoodsDescVO descVo = new GoodsDescVO();
        BeanUtil.copyProperties(desc, descVo);


        request.setAttribute("goodsImageDetail", imageVoList);
        request.setAttribute("goodsDescDetail", descVo);
        request.setAttribute("goodsQaDetail", qaVoList);
        request.setAttribute("goodsReviewDetail", reviewVoList);


        return "mall/detail";
    }

    /*paging*/
    //added by c 2021/4/26
    @RequestMapping(value = "goods/qaSort", method = RequestMethod.POST)
    @ResponseBody
    public Result getGoodsQaSortPage(@RequestBody PagingQa page) {

    Map<String, Object> params = new HashMap<>();
    params.put("page",page.getPage());
    params.put("limit",Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
    params.put("orderBy","submitDate");

    PageQueryUtil pageUtil = new PageQueryUtil(params);
    PageResult result = newBeeMallGoodsService.getGoodsQaSortPage(pageUtil);
    return ResultGenerator.genSuccessResult(result);
    }

    //added by c 2021/4/29
    @RequestMapping(value = "goods/insertQa", method = RequestMethod.POST)
    @ResponseBody
    public Result insertGoodsQa(@RequestBody GoodsQa qa){
        Integer count = null;
        //genera qa id
        Long qaId = newBeeMallGoodsService.getMaxQaId(qa.getGoodsId());
        qa.setId(qaId);
        Date submitDate = new Date();
        Date answerDate = new Date();
        qa.setSubmitDate(submitDate);
        qa.setAnswerDate(answerDate);
        if(qa !=null){
            count = newBeeMallGoodsService.insertGoodsQa(qa);
        }
        if(!(count > 0)){
            return ResultGenerator.genFailResult("投稿失敗");
        }
        return ResultGenerator.genSuccessResult(count);
    }

    //showMoreReviews
    @RequestMapping(value = "goods/showMoreReviews", method = RequestMethod.POST)
    @ResponseBody
    public Result getGoodsReview(@RequestBody Long goodsId){
        List<GoodsReviewVO> reviewList = newBeeMallGoodsService.getGoodsReview(goodsId);
        return ResultGenerator.genSuccessResult(reviewList);
    }
}

/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.GoodsSaleVO;
import ltd.newbee.mall.controller.vo.NewBeeMallSearchGoodsVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.DetailTitle;
import ltd.newbee.mall.entity.FeaturesRelatedInformation;
import ltd.newbee.mall.entity.GoodsCoupon;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpedNum;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.MenuCourse;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.RestHygiene;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.SearchHistory;
import ltd.newbee.mall.entity.SeatFacility;
import ltd.newbee.mall.entity.TabeLogJoinCategory;
import ltd.newbee.mall.entity.TabelogBasicInformation;
import ltd.newbee.mall.entity.TabelogGoodNum;
import ltd.newbee.mall.entity.TbCategory;
import ltd.newbee.mall.entity.TbComment;
import ltd.newbee.mall.entity.TbGenre;
import ltd.newbee.mall.entity.TbSale;
import ltd.newbee.mall.entity.TopCommentImage;
import ltd.newbee.mall.entity.TopCoupon;
import ltd.newbee.mall.entity.TopCourse;
import ltd.newbee.mall.entity.TopImage;
import ltd.newbee.mall.entity.TopKodawari;
import ltd.newbee.mall.entity.TopPage;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallGoods(NewBeeMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList) {
        if (!CollectionUtils.isEmpty(newBeeMallGoodsList)) {
            goodsMapper.batchInsert(newBeeMallGoodsList);
        }
    }

    @Override
    public String updateNewBeeMallGoods(NewBeeMallGoods goods) {
        NewBeeMallGoods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
        List<NewBeeMallSearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(goodsList, NewBeeMallSearchGoodsVO.class);
            for (NewBeeMallSearchGoodsVO newBeeMallSearchGoodsVO : newBeeMallSearchGoodsVOS) {
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(newBeeMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<GoodsImage>getImageList(Long goodsId){
        List<GoodsImage> imageList = goodsMapper.getImageList(goodsId);
        return imageList;
    }
    @Override
    public List<GoodsReview>getReviewList(Long goodsId){
        List<GoodsReview> reviewList = goodsMapper.getReviewList(goodsId);
        return reviewList;
    }
    @Override
    public List<GoodsQa>getQaList(Long goodsId){
        List<GoodsQa> qaList = goodsMapper.getQaList(goodsId);
        return qaList;
    }
    @Override
    public GoodsDesc getGoodsDesc(Long goodsId){
        GoodsDesc goodsDesc = goodsMapper.getGoodsDesc(goodsId);
        return goodsDesc;
    }
    //added by c 2021/4/23 ページング
    @Override
    public PageResult getGoodsQaPage(PageQueryUtil pageUtil){
        List<GoodsQa> qaPageList = goodsMapper.findGoodsQaList(pageUtil);
        int total = goodsMapper.getTotalGoodsQa(pageUtil);
        PageResult pageResult = new PageResult(qaPageList,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }
    //added by c 2021/4/24 Sorting
    @Override
    public PageResult getGoodsQaSortPage(PageQueryUtil pageUtil){
        List<GoodsQa> qaSortList = goodsMapper.getSortingQaList(pageUtil);
        int total = goodsMapper.getTotalGoodsQa(pageUtil);
        PageResult pageResult = new PageResult(qaSortList,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

//    @Override
//    public int getTotalQa(PageQueryUtil pageUtil){
//        int total = goodsMapper.getTotalGoodsQa(pageUtil);
//        return total;
//    }
    //added by c 2021/4/24 insert
    @Override
    public int insertQaSelective(GoodsQa question) {
        int count = goodsMapper.insertQaSelective(question);
        return count;
    }

    @Override
    public Long getMaxQaId(Long goodsId) {
        Long maxGoodsId = goodsMapper.getMaxQaId(goodsId);
        if(maxGoodsId !=null){
            return maxGoodsId + 1;
        }else{
            return 1L;
        }
    }

    @Override
    public List<GoodsReviewVO>getGoodsReview(Long goodsId){
        List<GoodsReview> entityList = goodsMapper.getReviewList(goodsId);
        List<GoodsReviewVO> reviewVoList = BeanUtil.copyList(entityList,GoodsReviewVO.class);
        return reviewVoList;
    }

    //added by c 2021/5/4
    @Override
    public boolean insertHelpedNum(GoodsReviewHelpedNum goodsReviewHelpedNum){
        return  goodsMapper.insertHelpedNum(goodsReviewHelpedNum);
    }

    @Override
    public boolean updateReviewNum(GoodsReviewHelpedNum goodsReviewHelpedNum){
        return goodsMapper.updateReviewNum(goodsReviewHelpedNum);
    }

    @Override
    public long getGoodsReviewNum(int reviewId){
        return  goodsMapper.getGoodsReviewNum(reviewId);
    }

    //added by c 2021/5/7
   /* @Override
    public PageResult pagingReviewLi(PageQueryUtil pageUtil){
        List<GoodsReview> reviewPageList = goodsMapper.pagingReviewLi(pageUtil);
        int total = goodsMapper.getTotalGoodsReview(pageUtil);
        PageResult pageResult = new PageResult(reviewPageList,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }*/

    //added by c 2021/5/10 insert search history
    @Override
    public int insertSearchHistory(SearchHistory keyword) {
        int count = goodsMapper.insertSearchHistory(keyword);
        return count;
    }
    @Override
    public Long getMaxSearchId(Long userId) {
        Long maxSearchId = goodsMapper.getMaxSearchId(userId);
        if(maxSearchId !=null){
            return maxSearchId + 1;
        }else{
            return 1L;
        }
    }

    //added by c campaign page
    @Override
    public List<TbSale>getTbSale(Long id){
        List<TbSale> tbSaleList = goodsMapper.getTbSale(id);
        return tbSaleList;
    }
    @Override
    public List<GoodsSale>getGoodsSale(Integer[] ids){
        List<GoodsSale> goodsSaleList = goodsMapper.getGoodsSale(ids);
        return goodsSaleList;
    }
    @Override
    public List<TbCategory>getTbCategory(Long id){
        List<TbCategory> tbCategoryList = goodsMapper.getTbCategory(id);
        return tbCategoryList;
    }
    @Override
    public List<GoodsCoupon>getGoodsCoupon(Long couponId){
        List<GoodsCoupon> couponList = goodsMapper.getGoodsCoupon(couponId);
        return couponList;
    }
    //added by c 20215/13 insert csv
    @Override
    public int insertGoodsSale(GoodsSale goodsSale) {
        int count = goodsMapper.insertGoodsSale(goodsSale);
        return count;
    }
    // //added by c 2021/5/24 max saleId
    @Override
    public Long getMaxSaleId() {
        Long maxSaleId = goodsMapper.findMaxSaleId();
        if(maxSaleId !=null){
            return maxSaleId + 1;
        }else{
            return 1L;
        }
    }

    @Override
    public PageResult pagingGoodsSale(PageQueryUtil pageUtil){
        List<GoodsSale> gsSortList = goodsMapper.pagingGoodsSale(pageUtil);
        int total = goodsMapper.getTotalGoodsSale(pageUtil);
        List<GoodsSaleVO> gsVoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(gsSortList)) {
            gsVoList = BeanUtil.copyList(gsSortList, GoodsSaleVO.class);
            for (GoodsSaleVO goodsSaleVO : gsVoList) {
                String name = goodsSaleVO.getName();
                // 字符串过长导致文字超出的问题
                if (name.length() > 28) {
                    name = name.substring(0, 28) + "...";
                    goodsSaleVO.setName(name);
                }
            }
        }
        PageResult pageResult = new PageResult(gsSortList,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    //modal dropDownList
    @Override
    public List<NewBeeMallGoods> findGiftCategoryId(Long goodsCategoryId){
        List<NewBeeMallGoods> goodsList = goodsMapper.findCategoryId(goodsCategoryId);
        return goodsList;
    }

    //modal insert
    @Override
    public int insertTbSale(TbSale tbSale) {
        int countTbSale = goodsMapper.insertTbSale(tbSale);
        return countTbSale;
    }

    //added by c 2021/7/13
    @Override
    public PageResult getLimitGoodsReview(PageQueryUtil pageUtil){
        List<GoodsReview> reviewList = goodsMapper.getLimitGoodsReview(pageUtil);
        int total = goodsMapper.getTotalGoodsReview(pageUtil);
        PageResult pageResult = new PageResult(reviewList,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }


    //added by c 2021/7/20 tabe-log
    @Override
    public DetailTitle getDetailTitle(Long id){
        DetailTitle detailTitle = goodsMapper.getDetailTitle(id);
        return detailTitle;
    }
    @Override
    public RestaurantDesc getDetailSubTitle(Long id){
        RestaurantDesc restaurantDesc = goodsMapper.getDetailSubTitle(id);
        return restaurantDesc;
    }
//    @Override
//    public List<TbGenre> getGenreList(Long id){
//        List<TbGenre> genreList = goodsMapper.getGenreList(id);
//        return genreList;
//    }
//    @Override
//    public List<TabeLogCategory> getGenreCategoryList(Long genreId){
//        List<TabeLogCategory> tabeLogCategoryList = goodsMapper.getGenreCategoryList(genreId);
//        return tabeLogCategoryList;
//    }
    //added by c 2021/7/26 get tb_comment
//    @Override
//    public List<TbComment> getTbComment(Long id){
//        List<TbComment> commentList = goodsMapper.getTbComment(id);
//        return commentList;
//    }
    //added by c 2021/7/26 get total_comment
    @Override
    public int getTotalRestComment(){
        int total = goodsMapper.getTotalRestComment();
        return total;
    }
    @Override
    public double getAvgStar(){
        double avgStar = goodsMapper.getAvgStar();
        return avgStar;
    }

    //added by c 2021/7/28 get join category
    @Override
    public List<TabeLogJoinCategory> getJoinCategoryList(Long id){
        List<TabeLogJoinCategory> joinList = goodsMapper.getJoinCategoryList(id);
        return joinList;
    }

    //added by c 2021/7/29 top page
    @Override
    public List<TopPage> getTopPage(Long id){
        List<TopPage> topPage = goodsMapper.getTopPage(id);
        return topPage;
    }

    @Override
    public List<TopImage> getTopImage(Long id){
        List<TopImage> imageList = goodsMapper.getTopImage(id);
        return imageList;
    }

    @Override
    public List<TopKodawari> getTopKodawari(Long id){
        List<TopKodawari> kodawariList = goodsMapper.getTopKodawari(id);
        return kodawariList;
    }
    @Override
    public List<RestHygiene> getRestHygiene(Long id){
        List<RestHygiene> restHygieneList = goodsMapper.getRestHygiene(id);
        return restHygieneList;
    }
    @Override
    public List<TopCourse> getTopCourse(Long id){
        List<TopCourse> topCourseList = goodsMapper.getTopCourse(id);
        return topCourseList;
    }
    @Override
    public List<TopCoupon> getTopCoupon(Long id){
        List<TopCoupon> topCouponList = goodsMapper.getTopCoupon(id);
        return topCouponList;
    }
    @Override
    public List<TopCommentImage> getTopCommentImage(Long id){
        List<TopCommentImage> commentImageList = goodsMapper.getTopCommentImage(id);
        return commentImageList;
    }

    //comment good num
    @Override
    public boolean insertTabelogHelpedNum(TabelogGoodNum tabelogGoodNum){
        return  goodsMapper.insertTabelogHelpedNum(tabelogGoodNum);
    }

    @Override
    public boolean updateCommentNum(TabelogGoodNum tabelogGoodNum){
        return goodsMapper.updateCommentNum(tabelogGoodNum);
    }

    @Override
    public long getCommentNum(int commentId){
        return  goodsMapper.getCommentNum(commentId);
    }

    @Override
    public List<TabelogBasicInformation> getTopBasicInfo(Long id){
        List<TabelogBasicInformation> basicInfoList = goodsMapper.getTopBasicInfo(id);
        return basicInfoList;
    }

    @Override
    public List<SeatFacility> getTopSeatInfo(Long id){
        List<SeatFacility> seatInfoList = goodsMapper.getTopSeatInfo(id);
        return seatInfoList;
    }

    @Override
    public List<MenuCourse> getMenuCourse(Long id){
        List<MenuCourse> menuCourseList = goodsMapper.getMenuCourse(id);
        return menuCourseList;
    }

    @Override
    public List<FeaturesRelatedInformation> getTopRelatedInfo(Long id){
        List<FeaturesRelatedInformation> relatedInfoList = goodsMapper.getTopRelatedInfo(id);
        return relatedInfoList;
    }
}

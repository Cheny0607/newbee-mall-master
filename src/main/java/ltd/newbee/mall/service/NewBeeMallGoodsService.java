/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.GoodsReviewVO;
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
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface NewBeeMallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    NewBeeMallGoods getNewBeeMallGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);

    List<GoodsImage> getImageList(Long goodsId);
    List<GoodsReview> getReviewList(Long goodsId);
    List<GoodsQa> getQaList(Long goodsId);
    GoodsDesc getGoodsDesc(Long goodsId);
    //added by c 2021/4/23 ページング
    PageResult getGoodsQaPage(PageQueryUtil pageUtil);
   // int getTotalGoodsQa(PageQueryUtil pageUtil);
    //added by c 2021/4/24 Sorting
    PageResult getGoodsQaSortPage(PageQueryUtil pageUtil);
    //added by c 2021/4/24 insert
    int insertQaSelective(GoodsQa question);
    //added by c 2021/4/29 insert getMaxId
    Long getMaxQaId(Long goodsId);
    //added by c 2021/5/3
    List<GoodsReviewVO> getGoodsReview(Long goodsId);
    //added by c 2021/5/4
    boolean insertHelpedNum(GoodsReviewHelpedNum goodsReviewHelpedNum);
    boolean updateReviewNum(GoodsReviewHelpedNum goodsReviewHelpedNum);
    long getGoodsReviewNum(int reviewId);
    //added by c 2021/5/7
    /*PageResult pagingReviewLi(PageQueryUtil pageUtil);*/
    //added by c 2021/5/10 insert search history
    int insertSearchHistory(SearchHistory keyword);
    Long getMaxSearchId(Long userId);
    //added by c campaign page
    List<TbSale> getTbSale(Long id);
    List<GoodsSale> getGoodsSale(Integer[] ids);
    List<TbCategory> getTbCategory(Long id);
    List<GoodsCoupon> getGoodsCoupon(Long couponId);
    int insertGoodsSale(GoodsSale goodsSale);
    //added by c 2021/5/15 paging goodsSale
    PageResult pagingGoodsSale(PageQueryUtil pageUtil);
    //added by c 2021/5/24 max saleId
    Long getMaxSaleId();
    //modal dropDownList
    List<NewBeeMallGoods> findGiftCategoryId(Long goodsCategoryId);
    //modal insert
    int insertTbSale(TbSale tbSale);
    //added by c 2021/7/13
    PageResult getLimitGoodsReview(PageQueryUtil pageUtil);


    //added by c 2021/7/20 tabe-log
    DetailTitle getDetailTitle(Long id);
    RestaurantDesc getDetailSubTitle(Long id);
//    List<TbGenre> getGenreList(Long id);
//    List<TabeLogCategory> getGenreCategoryList(Long genreId);
    //added by c 2021/7/26 get tb_comment
    //List<TbComment> getTbComment(Long id);
    //added by c 2021/7/26 get total_comment
    //List<TbComment> getTbComment(PageQueryUtil pageUtil);
    int getTotalRestComment ();
    double getAvgStar ();
    //added by c 2021/7/28 get join category
    List<TabeLogJoinCategory> getJoinCategoryList(Long id);
    //added by c 2021/7/29 top page
    List<TopPage> getTopPage(Long id);
    List<TopImage> getTopImage(Long id);
    List<TopKodawari> getTopKodawari(Long id);
    List<RestHygiene> getRestHygiene(Long id);
    List<TopCourse> getTopCourse(Long id);
    List<TopCoupon> getTopCoupon(Long id);
    List<TopCommentImage> getTopCommentImage(Long id);
    //comment good num
    boolean insertTabelogHelpedNum(TabelogGoodNum tabelogGoodNum);
    boolean updateCommentNum(TabelogGoodNum tabelogGoodNum);
    long getCommentNum(int commentId);
    //
    List<TabelogBasicInformation> getTopBasicInfo(Long id);
    List<SeatFacility> getTopSeatInfo(Long id);
    List<MenuCourse> getMenuCourse(Long id);
    List<FeaturesRelatedInformation> getTopRelatedInfo(Long id);

}

/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.dao;

import java.util.Map;
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
import ltd.newbee.mall.entity.StockNumDTO;
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
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewBeeMallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(NewBeeMallGoods record);

    int insertSelective(NewBeeMallGoods record);

    NewBeeMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(NewBeeMallGoods record);

    int updateByPrimaryKeyWithBLOBs(NewBeeMallGoods record);

    int updateByPrimaryKey(NewBeeMallGoods record);

    List<NewBeeMallGoods> findNewBeeMallGoodsList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoods(PageQueryUtil pageUtil);

    List<NewBeeMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    List<NewBeeMallGoods> findNewBeeMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("newBeeMallGoodsList") List<NewBeeMallGoods> newBeeMallGoodsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);

    //added by c 2021/04/17 イメージリスト取得
    List<GoodsImage> getImageList(Long goodsId);
    //added by c 2021/04/17 レビューリスト取得
    List<GoodsReview> getReviewList(Long goodsId);
    //added by c 2021/04/17 QAリスト取得
    List<GoodsQa> getQaList(Long goodsId);
    //added by c 2021/04/17 GoodsDesc取得
    GoodsDesc getGoodsDesc(Long goodsId);
    //added by c 2021/4/23 ページング
    List<GoodsQa> findGoodsQaList(PageQueryUtil pageUtil);
    int getTotalGoodsQa(PageQueryUtil pageUtil);
    //added by c 2021/4/24 Sorting
    List<GoodsQa> getSortingQaList (PageQueryUtil pageUtil);
    //added by c 2021/4/24 insert
    int insertGoodsQa(GoodsQa question);
    int insertQaSelective(GoodsQa question);
    //added by c 2021/4/29 maxId
    Long getMaxQaId(Long goodsId);
    //added by c 2021/5/4
    boolean insertHelpedNum(GoodsReviewHelpedNum goodsReviewHelpedNum);
    boolean updateReviewNum(GoodsReviewHelpedNum goodsReviewHelpedNum);
    long getGoodsReviewNum(int reviewId);
    //added by c 2021/5/7
    //added by c 2021/5/10 insert search history
    int insertSearchHistory(SearchHistory keyword);
    Long getMaxSearchId(Long userId);
    //added by c campaign page
    List<TbSale> getTbSale(Long id);
    List<GoodsSale> getGoodsSale(Integer[] ids);
    List<TbCategory> getTbCategory(Long id);
    List<GoodsCoupon> getGoodsCoupon(Long couponId);
    int insertTbSale(TbSale tbSale);
    int insertGoodsSale(GoodsSale goodsSale);
    int insertTbCategory(TbCategory id);
    int insertGoodsCoupon(GoodsCoupon couponId);
    //added by c 2021/5/15 paging goodsSale
    List<GoodsSale> pagingGoodsSale(PageQueryUtil pageUtil);
    int getTotalGoodsSale(PageQueryUtil pageUtil);
    //added by c 2021/5/15 max saleId
    Long findMaxSaleId();
    //modal dropDownList
    List<NewBeeMallGoods> findCategoryId(Long goodsCategoryId);
    //added by c 2021/7/13
    List<GoodsReview> getLimitGoodsReview(PageQueryUtil pageUtil);
    int getTotalGoodsReview(PageQueryUtil pageUtil);

    //added by c 2021/7/20 tabe-log
    DetailTitle getDetailTitle(Long id);
    RestaurantDesc getDetailSubTitle(Long id);
    //List<TbGenre> getGenreList(Long id);
    //List<TabeLogCategory> getGenreCategoryList(Long genreId);
    //added by c 2021/7/26 get tb_comment
    // List<TbComment> getTbComment(Long id);
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
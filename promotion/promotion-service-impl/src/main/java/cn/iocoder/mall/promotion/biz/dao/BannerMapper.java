package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerMapper {

    BannerDO selectById(@Param("id") Integer id);

    List<BannerDO> selectListByStatus(@Param("status") Integer status);

    List<BannerDO> selectListByTitleLike(@Param("title") String title,
                                         @Param("offset") Integer offset,
                                         @Param("limit") Integer limit);

    Integer selectCountByTitleLike(@Param("title") String title);

    void insert(BannerDO bannerDO);

    int update(BannerDO bannerDO);

}
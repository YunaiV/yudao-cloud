package cn.iocoder.mall.promotionservice.dal.mysql.mapper.banner;

import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.banner.BannerDO;
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
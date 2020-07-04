package cn.iocoder.mall.promotion.biz.service.banner;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListBO;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListOnReleaseBO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerAddDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerListDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerUpdateDTO;

import java.util.List;

/**
 * banner
 *
 * author: sin
 * time: 2020/5/14 14:19
 */
public interface BannerService {

    /**
     * 列表 - 获取已发布的banner
     *
     * @return
     */
    List<BannerListOnReleaseBO> listBannerOnRelease();

    /**
     * 列表 - banner 列表
     *
     * @param bannerPageDTO
     * @return
     */
    PageResult<BannerListBO> listBanner(BannerListDTO bannerPageDTO);

    /**
     * 添加 - 一个banner
     *
     * @param adminsBannerAddDTO
     */
    void addBanner(BannerAddDTO adminsBannerAddDTO);

    /**
     * 更新 - 根据id更新
     *
     * @param adminsBannerUpdateDTO
     */
    void updateBanner(BannerUpdateDTO adminsBannerUpdateDTO);

    // TODO FROM 芋艿 to 小范：貌似要把 dto 搞起来，嘿嘿；
    /**
     * 更新 - banner 状态
     *
     * @param adminId
     * @param bannerId
     * @param status
     * @return
     */
    void updateBannerStatus(Integer adminId, Integer bannerId, @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    /**
     * 删除 - 根据id删除一个banner
     *
     * @param adminId
     * @param bannerId
     */
    void deleteBanner(Integer adminId, Integer bannerId);
}

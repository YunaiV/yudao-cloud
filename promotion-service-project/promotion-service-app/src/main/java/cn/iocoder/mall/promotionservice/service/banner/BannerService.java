package cn.iocoder.mall.promotionservice.service.banner;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.promotion.api.enums.PromotionActivityTypeEnum;
import cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeEnum;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerPageDTO;
import cn.iocoder.mall.promotionservice.convert.activity.PromotionActivityConvert;
import cn.iocoder.mall.promotionservice.convert.banner.BannerConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.banner.BannerDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.activity.PromotionActivityMapper;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.banner.BannerMapper;
import cn.iocoder.mall.promotionservice.service.activity.bo.PromotionActivityPageBO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerAddBO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerBO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerPageBO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.*;


@Service
@Validated
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    public List<BannerBO> getBannerListByStatus(Integer status) {
        List<BannerDO> banners = bannerMapper.selectListByStatus(status);
        return BannerConvert.INSTANCE.convertToBO(banners);
    }

    public BannerPageBO getBannerPage(BannerPageDTO bannerPageDTO) {
        BannerPageBO bannerPageBO = new BannerPageBO();
        // 查询分页数据
        int offset = (bannerPageDTO.getPageNo() - 1) * bannerPageDTO.getPageSize();
        bannerPageBO.setList(BannerConvert.INSTANCE.convertToBO(bannerMapper.selectListByTitleLike(bannerPageDTO.getTitle(),
                offset, bannerPageDTO.getPageSize())));
        // 查询分页总数
        bannerPageBO.setTotal(bannerMapper.selectCountByTitleLike(bannerPageDTO.getTitle()));
        return bannerPageBO;
    }

    public BannerBO addBanner(Integer adminId, BannerAddBO bannerAddDTO) {
        // 保存到数据库
        BannerDO banner = BannerConvert.INSTANCE.convert(bannerAddDTO).setStatus(CommonStatusEnum.ENABLE.getValue());
        banner.setDeleted(DeletedStatusEnum.DELETED_NO.getValue()).setCreateTime(new Date());
        bannerMapper.insert(banner);
        // 返回成功
        return BannerConvert.INSTANCE.convertToBO(banner);
    }

    public Boolean updateBanner(Integer adminId, BannerUpdateBO bannerUpdateDTO) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = BannerConvert.INSTANCE.convert(bannerUpdateDTO);
        bannerMapper.update(updateBanner);
        // 返回成功
        return true;
    }

    public Boolean updateBannerStatus(Integer adminId, Integer bannerId, Integer status) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerId) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = new BannerDO().setId(bannerId).setStatus(status);
        bannerMapper.update(updateBanner);
        // 返回成功
        return true;
    }

    public Boolean deleteBanner(Integer adminId, Integer bannerId) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerId) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = new BannerDO().setId(bannerId);
        updateBanner.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        bannerMapper.update(updateBanner);
        // 返回成功
        return true;
    }

}

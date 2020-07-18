package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.promotion.api.BannerService;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.api.constant.PromotionErrorCodeEnum;
import cn.iocoder.mall.promotion.api.dto.BannerAddDTO;
import cn.iocoder.mall.promotion.api.dto.BannerPageDTO;
import cn.iocoder.mall.promotion.api.dto.BannerUpdateDTO;
import cn.iocoder.mall.promotion.biz.convert.BannerConvert;
import cn.iocoder.mall.promotion.biz.dao.BannerMapper;
import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.BannerService.version}")
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerBO> getBannerListByStatus(Integer status) {
        List<BannerDO> banners = bannerMapper.selectListByStatus(status);
        return BannerConvert.INSTANCE.convertToBO(banners);
    }

    @Override
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

    @Override
    public BannerBO addBanner(Integer adminId, BannerAddDTO bannerAddDTO) {
        // 保存到数据库
        BannerDO banner = BannerConvert.INSTANCE.convert(bannerAddDTO).setStatus(CommonStatusEnum.ENABLE.getValue());
        banner.setDeleted(DeletedStatusEnum.DELETED_NO.getValue()).setCreateTime(new Date());
        bannerMapper.insert(banner);
        // 返回成功
        return BannerConvert.INSTANCE.convertToBO(banner);
    }

    @Override
    public Boolean updateBanner(Integer adminId, BannerUpdateDTO bannerUpdateDTO) {
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

    @Override
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

    @Override
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

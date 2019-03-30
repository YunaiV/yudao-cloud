package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
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

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public CommonResult<BannerPageBO> getBannerPage(BannerPageDTO bannerPageDTO) {
        BannerPageBO bannerPageBO = new BannerPageBO();
        // 查询分页数据
        int offset = (bannerPageDTO.getPageNo() - 1) * bannerPageDTO.getPageSize();
        bannerPageBO.setList(BannerConvert.INSTANCE.convertToBO(bannerMapper.selectListByTitleLike(bannerPageDTO.getTitle(),
                offset, bannerPageDTO.getPageSize())));
        // 查询分页总数
        bannerPageBO.setTotal(bannerMapper.selectCountByTitleLike(bannerPageDTO.getTitle()));
        return CommonResult.success(bannerPageBO);
    }

    @Override
    public CommonResult<BannerBO> addBanner(Integer adminId, BannerAddDTO bannerAddDTO) {
        // 保存到数据库
        BannerDO banner = BannerConvert.INSTANCE.convert(bannerAddDTO).setStatus(CommonStatusEnum.ENABLE.getValue());
        banner.setDeleted(DeletedStatusEnum.DELETED_NO.getValue()).setCreateTime(new Date());
        bannerMapper.insert(banner);
        // 返回成功
        return CommonResult.success(BannerConvert.INSTANCE.convertToBO(banner));
    }

    @Override
    public CommonResult<Boolean> updateBanner(Integer adminId, BannerUpdateDTO bannerUpdateDTO) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = BannerConvert.INSTANCE.convert(bannerUpdateDTO);
        bannerMapper.update(updateBanner);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateBannerStatus(Integer adminId, Integer bannerId, Integer status) {
        // 校验参数
        if (!CommonStatusEnum.isValid(status)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "变更状态必须是开启（1）或关闭（2）"); // TODO 有点搓
        }
        // 更新到数据库
        BannerDO updateBanner = new BannerDO().setId(bannerId).setStatus(status);
        bannerMapper.update(updateBanner);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> deleteBanner(Integer adminId, Integer bannerId) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerId) == null) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = new BannerDO().setId(bannerId);
        updateBanner.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        bannerMapper.update(updateBanner);
        // 返回成功
        return CommonResult.success(true);
    }

}
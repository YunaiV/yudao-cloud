package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.api.constant.CouponTemplateDateTypeEnum;
import cn.iocoder.mall.promotion.api.constant.CouponTemplatePreferentialTypeEnum;
import cn.iocoder.mall.promotion.api.constant.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.constant.CouponTemplateTypeEnum;
import cn.iocoder.mall.promotion.api.dto.*;
import cn.iocoder.mall.promotion.biz.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.biz.dao.CouponTemplateMapper;
import cn.iocoder.mall.promotion.biz.dataobject.CouponTemplateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    @Override
    public CommonResult<CouponTemplatePageBO> getCouponTemplatePage(CouponTemplatePageDTO couponTemplatePageDTO) {
        return null;
    }

    @Override
    public CommonResult<CouponTemplateBO> addCouponCodeTemplate(CouponCodeTemplateAddDTO couponCodeTemplateAddDTO) {
        return null;
    }

    @Override
    public CommonResult<CouponTemplateBO> addCouponCardTemplate(CouponCardTemplateAddDTO couponCardTemplateAddDTO) {
        // 校验生效日期相关
        CommonResult<Boolean> checkCouponCodeTemplateDateTypeResult = this.checkCouponTemplateDateType(
                couponCardTemplateAddDTO.getDateType(),
                couponCardTemplateAddDTO.getValidStartTime(), couponCardTemplateAddDTO.getValidEndTime(),
                couponCardTemplateAddDTO.getFixedBeginTerm(), couponCardTemplateAddDTO.getFixedEndTerm());
        if (checkCouponCodeTemplateDateTypeResult.isError()) {
            return CommonResult.error(checkCouponCodeTemplateDateTypeResult);
        }
        // 校验优惠类型
        CommonResult<Boolean> checkCouponTemplateDateTypeResult = this.checkCouponTemplatePreferentialType(
                couponCardTemplateAddDTO.getPreferentialType(), couponCardTemplateAddDTO.getPercentOff(),
                couponCardTemplateAddDTO.getPriceOff());
        if (checkCouponCodeTemplateDateTypeResult.isError()) {
            return CommonResult.error(checkCouponTemplateDateTypeResult);
        }
        // 保存优惠劵模板到数据库
        CouponTemplateDO template = CouponTemplateConvert.INSTANCE.convert(couponCardTemplateAddDTO)
                .setType(CouponTemplateTypeEnum.CARD.getValue())
                .setStatus(CouponTemplateStatusEnum.ENABLE.getValue())
                .setStatFetchNum(0);
        couponTemplateMapper.insert(template);
        // 返回成功
        return CommonResult.success(CouponTemplateConvert.INSTANCE.convert(template));
    }

    @Override
    public CommonResult<Boolean> updateCouponCodeTemplate(CouponCodeTemplateUpdateDTO couponCodeTemplateUpdateDTO) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateCouponCardTemplate(CouponCardTemplateUpdateDTO couponCardTemplateUpdateDTO) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId, Integer status) {
        return null;
    }

    @Override
    public CommonResult<CouponCardBO> addCouponCard(Integer userId, Integer couponTemplateId) {
        return null;
    }

    @Override
    public CommonResult<Boolean> useCouponCard(Integer userId, Integer couponCardId, Integer usedOrderId, Integer usedPrice) {
        return null;
    }

    @Override
    public CommonResult<Boolean> cancelUseCouponCard(Integer userId, Integer couponCardId) {
        return null;
    }

    @Override
    public CommonResult<CouponCardBO> useCouponCode(Integer userId, String code) {
        return null;
    }

    private CommonResult<Boolean> checkCouponTemplateDateType(Integer dateType, Date validStartTime, Date validEndTime, Integer fixedBeginTerm, Integer fixedEndTerm) {
        if (CouponTemplateDateTypeEnum.FIXED_DATE.getValue().equals(dateType)) { // 固定日期
            if (validStartTime == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效开始时间不能为空");
            }
            if (validEndTime == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效结束时间不能为空");
            }
        } else if (CouponTemplateDateTypeEnum.FIXED_TERM.getValue().equals(dateType)) { // 领取日期
            if (fixedBeginTerm == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "领取日期开始时间不能为空");
            }
            if (fixedEndTerm == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "领取日期结束时间不能为空");
            }
        } else {
            throw new IllegalArgumentException("未知的生效日期类型：" + dateType);
        }
        return CommonResult.success(true);
    }

    private CommonResult<Boolean> checkCouponTemplatePreferentialType(Integer preferentialType, Integer percentOff, Integer priceOff) {
        if (CouponTemplatePreferentialTypeEnum.PRICE.getValue().equals(preferentialType)) {
            if (priceOff == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "优惠金额不能为空");
            }
        } else if (CouponTemplatePreferentialTypeEnum.DISCOUNT.getValue().equals(preferentialType)) {
            if (percentOff == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "折扣百分比不能为空");
            }
        } else {
            throw new IllegalArgumentException("未知的优惠类型：" + preferentialType);
        }
        return CommonResult.success(true);
    }

}

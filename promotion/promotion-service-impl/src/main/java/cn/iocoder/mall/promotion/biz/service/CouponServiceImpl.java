package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.api.constant.*;
import cn.iocoder.mall.promotion.api.dto.*;
import cn.iocoder.mall.promotion.biz.convert.CouponCardConvert;
import cn.iocoder.mall.promotion.biz.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.biz.convert.CouponTemplateConvertImpl;
import cn.iocoder.mall.promotion.biz.dao.CouponCardMapper;
import cn.iocoder.mall.promotion.biz.dao.CouponTemplateMapper;
import cn.iocoder.mall.promotion.biz.dataobject.CouponCardDO;
import cn.iocoder.mall.promotion.biz.dataobject.CouponTemplateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;
    @Autowired
    private CouponCardMapper couponCardMapper;

    // ========== 优惠劵（码）模板 ==========

    @Override
    public CommonResult<CouponTemplateBO> getCouponTemplate(Integer couponTemplateId) {
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        return CommonResult.success(CouponTemplateConvertImpl.INSTANCE.convert(template));
    }

    @Override
    public CommonResult<CouponTemplatePageBO> getCouponTemplatePage(CouponTemplatePageDTO couponTemplatePageDTO) {
        CouponTemplatePageBO couponTemplatePageBO = new CouponTemplatePageBO();
        // 查询分页数据
        int offset = (couponTemplatePageDTO.getPageNo() - 1) * couponTemplatePageDTO.getPageSize();
        couponTemplatePageBO.setList(CouponTemplateConvert.INSTANCE.convertToBO(couponTemplateMapper.selectListByPage(
                couponTemplatePageDTO.getType(), couponTemplatePageDTO.getTitle(),
                couponTemplatePageDTO.getStatus(), couponTemplatePageDTO.getPreferentialType(),
                offset, couponTemplatePageDTO.getPageSize())));
        // 查询分页总数
        couponTemplatePageBO.setTotal(couponTemplateMapper.selectCountByPage(
                couponTemplatePageDTO.getType(), couponTemplatePageDTO.getTitle(),
                couponTemplatePageDTO.getStatus(), couponTemplatePageDTO.getPreferentialType()));
        return CommonResult.success(couponTemplatePageBO);
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
                couponCardTemplateAddDTO.getPriceOff(), couponCardTemplateAddDTO.getPriceAvailable());
        if (checkCouponTemplateDateTypeResult.isError()) {
            return CommonResult.error(checkCouponTemplateDateTypeResult);
        }
        // 保存优惠劵模板到数据库
        CouponTemplateDO template = CouponTemplateConvert.INSTANCE.convert(couponCardTemplateAddDTO)
                .setType(CouponTemplateTypeEnum.CARD.getValue())
                .setStatus(CouponTemplateStatusEnum.ENABLE.getValue())
                .setStatFetchNum(0);
        template.setCreateTime(new Date());
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
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponCardTemplateUpdateDTO.getId());
        if (template == null) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 校验 CouponCardTemplate 是 CARD
        if (!CouponTemplateTypeEnum.CARD.getValue().equals(template.getType())) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_NOT_CARD.getCode());
        }
        // 校验发放数量不能减少
        if (couponCardTemplateUpdateDTO.getTotal() < template.getTotal()) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_TOTAL_CAN_NOT_REDUCE.getCode());
        }
        // 更新优惠劵模板到数据库
        CouponTemplateDO updateTemplateDO = CouponTemplateConvert.INSTANCE.convert(couponCardTemplateUpdateDTO);
        couponTemplateMapper.update(updateTemplateDO);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId, Integer status) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        if (template == null) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        CouponTemplateDO updateTemplateDO = new CouponTemplateDO().setId(couponTemplateId).setStatus(status);
        couponTemplateMapper.update(updateTemplateDO);
        // 返回成功
        return CommonResult.success(true);
    }

    private CommonResult<Boolean> checkCouponTemplateDateType(Integer dateType, Date validStartTime, Date validEndTime, Integer fixedBeginTerm, Integer fixedEndTerm) {
        if (CouponTemplateDateTypeEnum.FIXED_DATE.getValue().equals(dateType)) { // 固定日期
            if (validStartTime == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效开始时间不能为空");
            }
            if (validEndTime == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效结束时间不能为空");
            }
            if (validStartTime.after(validEndTime)) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效开始时间不能大于生效结束时间");
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

    private CommonResult<Boolean> checkCouponTemplatePreferentialType(Integer preferentialType, Integer percentOff,
                                                                      Integer priceOff, Integer priceAvailable) {
        if (CouponTemplatePreferentialTypeEnum.PRICE.getValue().equals(preferentialType)) {
            if (priceOff == null) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "优惠金额不能为空");
            }
            if (priceOff >= priceAvailable) {
                return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "优惠金额不能d大于等于使用金额门槛");
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

    // ========== 优惠劵 ==========

    @Override
    public CommonResult<CouponCardBO> addCouponCard(Integer userId, Integer couponTemplateId) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        if (template == null) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 校验 CouponCardTemplate 是 CARD
        if (!CouponTemplateTypeEnum.CARD.getValue().equals(template.getType())) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_NOT_CARD.getCode());
        }
        // 校验 CouponCardTemplate 状态是否开启
        if (!CouponTemplateStatusEnum.ENABLE.getValue().equals(template.getStatus())) {
            return ServiceExceptionUtil.error(PromotionErrorCodeEnum.PRODUCT_TEMPLATE_STATUS_NOT_ENABLE.getCode());
        }
        // 创建优惠劵
        // 1. 基本信息 + 领取情况
        CouponCardDO card = new CouponCardDO()
                .setTemplateId(couponTemplateId)
                .setStatus(CouponCardStatusEnum.UNUSED.getValue())
                .setUserId(userId)
                .setTakeType(CouponCardTakeTypeEnum.BY_USER.getValue()); // TODO 需要改
        // 2. 使用规则
        setCouponCardValidTime(card, template);
        // 3. 使用效果
        card.setPreferentialType(template.getPreferentialType())
            .setPriceOff(template.getPriceOff())
            .setPercentOff(template.getPercentOff()).setDiscountPriceLimit(template.getDiscountPriceLimit());
        // 保存优惠劵模板到数据库
        card.setCreateTime(new Date());
        couponCardMapper.insert(card);
        // 返回成功
        return CommonResult.success(CouponCardConvert.INSTANCE.convert(card));
    }

    @Override
    public CommonResult<Boolean> useCouponCard(Integer userId, Integer couponCardId, Integer usedOrderId, Integer usedPrice) {
        return null;
    }

    @Override
    public CommonResult<Boolean> cancelUseCouponCard(Integer userId, Integer couponCardId) {
        return null;
    }

    private void setCouponCardValidTime(CouponCardDO card, CouponTemplateDO template) {
        if (CouponTemplateDateTypeEnum.FIXED_DATE.getValue().equals(template.getDateType())) {
            card.setValidStartTime(template.getValidStartTime()).setValidEndTime(template.getValidEndTime());
        } else if (CouponTemplateDateTypeEnum.FIXED_TERM.getValue().equals(template.getDateType())) {
            Date validStartTime = DateUtil.getDayBegin(new Date());
            card.setValidStartTime(DateUtil.addDate(validStartTime, Calendar.DAY_OF_YEAR, template.getFixedStartTerm()));
            Date validEndTime = DateUtil.getDayEnd(card.getValidStartTime());
            card.setValidEndTime(DateUtil.addDate(validEndTime, Calendar.DAY_OF_YEAR, template.getFixedEndTerm() - 1));
        }
    }

    // ========== 优惠码 ==========

    @Override
    public CommonResult<CouponCardBO> useCouponCode(Integer userId, String code) {
        return null;
    }

}

package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.enums.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.*;
import cn.iocoder.mall.promotion.api.enums.*;
import cn.iocoder.mall.promotion.api.dto.*;
import cn.iocoder.mall.promotion.biz.convert.CouponCardConvert;
import cn.iocoder.mall.promotion.biz.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.biz.dao.CouponCardMapper;
import cn.iocoder.mall.promotion.biz.dao.CouponTemplateMapper;
import cn.iocoder.mall.promotion.biz.dataobject.CouponCardDO;
import cn.iocoder.mall.promotion.biz.dataobject.CouponTemplateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.CouponService.version}")
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    // ========== 优惠劵（码）模板 ==========

    @Override
    public CouponTemplateBO getCouponTemplate(Integer couponTemplateId) {
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        return CouponTemplateConvert.INSTANCE.convert(template);
    }

    @Override
    public CouponTemplatePageBO getCouponTemplatePage(CouponTemplatePageDTO couponTemplatePageDTO) {
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
        return couponTemplatePageBO;
    }

    @Override
    public CouponTemplateBO addCouponCodeTemplate(CouponCodeTemplateAddDTO couponCodeTemplateAddDTO) {
        return null;
    }

    @Override
    public CouponTemplateBO addCouponCardTemplate(CouponCardTemplateAddDTO couponCardTemplateAddDTO) {
        // 校验生效日期相关
        checkCouponTemplateDateType(couponCardTemplateAddDTO.getDateType(),
                couponCardTemplateAddDTO.getValidStartTime(), couponCardTemplateAddDTO.getValidEndTime(),
                couponCardTemplateAddDTO.getFixedBeginTerm(), couponCardTemplateAddDTO.getFixedEndTerm());
        // 校验优惠类型
        Boolean checkCouponTemplateDateTypeResult = checkCouponTemplatePreferentialType(
                couponCardTemplateAddDTO.getPreferentialType(), couponCardTemplateAddDTO.getPercentOff(),
                couponCardTemplateAddDTO.getPriceOff(), couponCardTemplateAddDTO.getPriceAvailable());
        // 保存优惠劵模板到数据库
        CouponTemplateDO template = CouponTemplateConvert.INSTANCE.convert(couponCardTemplateAddDTO)
                .setType(CouponTemplateTypeEnum.CARD.getValue())
                .setStatus(CouponTemplateStatusEnum.ENABLE.getValue())
                .setStatFetchNum(0);
        template.setCreateTime(new Date());
        couponTemplateMapper.insert(template);
        // 返回成功
        return CouponTemplateConvert.INSTANCE.convert(template);
    }

    @Override
    public Boolean updateCouponCodeTemplate(CouponCodeTemplateUpdateDTO couponCodeTemplateUpdateDTO) {
        return null;
    }

    @Override
    public Boolean updateCouponCardTemplate(CouponCardTemplateUpdateDTO couponCardTemplateUpdateDTO) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponCardTemplateUpdateDTO.getId());
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 校验 CouponCardTemplate 是 CARD
        if (!CouponTemplateTypeEnum.CARD.getValue().equals(template.getType())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.COUPON_TEMPLATE_NOT_CARD.getCode());
        }
        // 校验发放数量不能减少
        if (couponCardTemplateUpdateDTO.getTotal() < template.getTotal()) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.COUPON_TEMPLATE_TOTAL_CAN_NOT_REDUCE.getCode());
        }
        // 更新优惠劵模板到数据库
        CouponTemplateDO updateTemplateDO = CouponTemplateConvert.INSTANCE.convert(couponCardTemplateUpdateDTO);
        couponTemplateMapper.update(updateTemplateDO);
        // 返回成功
        return true;
    }

    @Override
    public Boolean updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId, Integer status) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        CouponTemplateDO updateTemplateDO = new CouponTemplateDO().setId(couponTemplateId).setStatus(status);
        couponTemplateMapper.update(updateTemplateDO);
        // 返回成功
        return true;
    }

    private Boolean checkCouponTemplateDateType(Integer dateType, Date validStartTime, Date validEndTime, Integer fixedBeginTerm, Integer fixedEndTerm) {
        if (CouponTemplateDateTypeEnum.FIXED_DATE.getValue().equals(dateType)) { // 固定日期
            if (validStartTime == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效开始时间不能为空");
            }
            if (validEndTime == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效结束时间不能为空");
            }
            if (validStartTime.after(validEndTime)) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "生效开始时间不能大于生效结束时间");
            }
        } else if (CouponTemplateDateTypeEnum.FIXED_TERM.getValue().equals(dateType)) { // 领取日期
            if (fixedBeginTerm == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "领取日期开始时间不能为空");
            }
            if (fixedEndTerm == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "领取日期结束时间不能为空");
            }
        } else {
            throw new IllegalArgumentException("未知的生效日期类型：" + dateType);
        }
        return true;
    }

    private Boolean checkCouponTemplatePreferentialType(Integer preferentialType, Integer percentOff,
                                                                      Integer priceOff, Integer priceAvailable) {
        if (PreferentialTypeEnum.PRICE.getValue().equals(preferentialType)) {
            if (priceOff == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "优惠金额不能为空");
            }
            if (priceOff >= priceAvailable) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "优惠金额不能d大于等于使用金额门槛");
            }
        } else if (PreferentialTypeEnum.DISCOUNT.getValue().equals(preferentialType)) {
            if (percentOff == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "折扣百分比不能为空");
            }
        } else {
            throw new IllegalArgumentException("未知的优惠类型：" + preferentialType);
        }
        return true;
    }

}

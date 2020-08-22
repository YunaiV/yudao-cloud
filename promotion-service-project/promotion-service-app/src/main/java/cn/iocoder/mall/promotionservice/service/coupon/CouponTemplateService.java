package cn.iocoder.mall.promotionservice.service.coupon;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.enums.PreferentialTypeEnum;
import cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateDateTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateTypeEnum;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplatePageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.promotionservice.convert.coupon.CouponTemplateConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponTemplateMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;

/**
 * 优惠劵模板 Service
 */
@Service
@Validated
public class CouponTemplateService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    // ========== 通用逻辑 =========

    /**
     * 获得优惠劵（码）模板
     *
     * @param couponCardId 优惠劵模板编号
     * @return 优惠劵（码）模板
     */
    public CouponTemplateRespDTO getCouponTemplate(Integer couponCardId) {
        return CouponTemplateConvert.INSTANCE.convert(couponTemplateMapper.selectById(couponCardId));
    }

    /**
     * 获得优惠劵（码）分页
     *
     * @param pageDTO 分页条件
     * @return 优惠劵（码）分页
     */
    public PageResult<CouponTemplateRespDTO> pageCouponTemplate(CouponTemplatePageReqDTO pageDTO) {
        IPage<CouponTemplateDO> couponTemplatePage = couponTemplateMapper.selectPage(pageDTO);
        return CouponTemplateConvert.INSTANCE.convertPage(couponTemplatePage);
    }

    /**
     * 更新优惠劵（码）模板的状态
     *
     * @param couponTemplateId 优惠劵模板编号
     * @param status 状态
     */
    public void updateCouponTemplateStatus(Integer couponTemplateId, Integer status) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        CouponTemplateDO updateTemplateDO = new CouponTemplateDO().setId(couponTemplateId).setStatus(status);
        couponTemplateMapper.updateById(updateTemplateDO);
    }

    private Boolean checkCouponTemplateDateType(Integer dateType, Date validStartTime, Date validEndTime, Integer fixedStartTerm, Integer fixedEndTerm) {
        // TODO 芋艿：后续这种类型的校验，看看怎么优化到对象里
        if (CouponTemplateDateTypeEnum.FIXED_DATE.getValue().equals(dateType)) { // 固定日期
            if (validStartTime == null) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "生效开始时间不能为空");
            }
            if (validEndTime == null) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "生效结束时间不能为空");
            }
            if (validStartTime.after(validEndTime)) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "生效开始时间不能大于生效结束时间");
            }
        } else if (CouponTemplateDateTypeEnum.FIXED_TERM.getValue().equals(dateType)) { // 领取日期
            if (fixedStartTerm == null) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "领取日期开始时间不能为空");
            }
            if (fixedEndTerm == null) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "领取日期结束时间不能为空");
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
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "优惠金额不能为空");
            }
            if (priceOff >= priceAvailable) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "优惠金额不能d大于等于使用金额门槛");
            }
        } else if (PreferentialTypeEnum.DISCOUNT.getValue().equals(preferentialType)) {
            if (percentOff == null) {
                throw ServiceExceptionUtil.exception(BAD_REQUEST, "折扣百分比不能为空");
            }
        } else {
            throw new IllegalArgumentException("未知的优惠类型：" + preferentialType);
        }
        return true;
    }

    // ========== 优惠劵模板 ==========

    /**
     * 添加优惠劵模板
     *
     * @param createReqDTO 优惠劵模板添加信息
     * @return 优惠劵模板编号
     */
    public Integer createCouponCardTemplate(CouponCardTemplateCreateReqDTO createReqDTO) {
        // 校验生效日期相关
        checkCouponTemplateDateType(createReqDTO.getDateType(),
                createReqDTO.getValidStartTime(), createReqDTO.getValidEndTime(),
                createReqDTO.getFixedStartTerm(), createReqDTO.getFixedEndTerm());
        // 校验优惠类型
        checkCouponTemplatePreferentialType(createReqDTO.getPreferentialType(), createReqDTO.getPercentOff(),
                createReqDTO.getPriceOff(), createReqDTO.getPriceAvailable());
        // 保存优惠劵模板到数据库
        CouponTemplateDO template = CouponTemplateConvert.INSTANCE.convert(createReqDTO)
                .setType(CouponTemplateTypeEnum.CARD.getValue())
                .setStatus(CouponTemplateStatusEnum.ENABLE.getValue())
                .setStatFetchNum(0);
        couponTemplateMapper.insert(template);
        // 返回成功
        return template.getId();
    }

    /**
     * 更新优惠劵模板
     *
     * @param updateReqDTO 优惠劵模板修改信息
     */
    public void updateCouponCardTemplate(CouponCardTemplateUpdateReqDTO updateReqDTO) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(updateReqDTO.getId());
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 校验 CouponCardTemplate 是 CARD
        if (!CouponTemplateTypeEnum.CARD.getValue().equals(template.getType())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_CARD.getCode());
        }
        // 校验发放数量不能减少
        if (updateReqDTO.getTotal() < template.getTotal()) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_TOTAL_CAN_NOT_REDUCE.getCode());
        }
        // 更新优惠劵模板到数据库
        CouponTemplateDO updateTemplateDO = CouponTemplateConvert.INSTANCE.convert(updateReqDTO);
        couponTemplateMapper.updateById(updateTemplateDO);
    }

    // ========== 优惠码模板 TODO 芋艿：以后开发 ==========


}

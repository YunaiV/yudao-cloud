package cn.iocoder.mall.promotionservice.service.coupon;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.card.CouponCardStatusEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.card.CouponCardTakeTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateDateTypeEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateTypeEnum;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableRespDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardRespDTO;
import cn.iocoder.mall.promotionservice.convert.coupon.CouponCardConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponCardDO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponCardMapper;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponTemplateMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 优惠劵 Service
 */
@Service
@Validated
public class CouponCardService {

    /**
     * 获得用户的优惠劵
     *
     * @param userId 用户编号
     * @param couponCardId 优惠劵编号
     * @return 优惠劵
     */
    public CouponCardRespDTO getCouponCard(Integer userId, Integer couponCardId) {
        CouponCardDO card = couponCardMapper.selectById(couponCardId);
        if (card == null) {
            return null;
        }
        if (!Objects.equals(userId, card.getUserId())) {
            return null;
        }
        return CouponCardConvert.INSTANCE.convert(card);
    }

    /**
     * 获得优惠劵分页
     *
     * @param pageReqDTO 优惠劵分页查询
     * @return 优惠劵分页结果
     */
    public PageResult<CouponCardRespDTO> pageCouponCard(CouponCardPageReqDTO pageReqDTO) {
        IPage<CouponCardDO> couponCardDOPage = couponCardMapper.selectPage(pageReqDTO);
        return CouponCardConvert.INSTANCE.convertPage(couponCardDOPage);
    }

    /**
     * 给用户添加优惠劵
     *
     * @param userId 用户编号
     * @param couponTemplateId 优惠劵模板编号
     * @return 优惠劵编号
     */
    @Transactional
    public Integer createCouponCard(Integer userId, Integer couponTemplateId) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 校验 CouponCardTemplate 是 CARD
        if (!CouponTemplateTypeEnum.CARD.getValue().equals(template.getType())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_CARD.getCode());
        }
        // 校验 CouponCardTemplate 状态是否开启
        if (!CouponTemplateStatusEnum.ENABLE.getValue().equals(template.getStatus())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_STATUS_NOT_ENABLE.getCode());
        }
        // 校验 CouponCardTemplate 是否到达可领取的上限
        if (template.getStatFetchNum() > template.getTotal()) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_TOTAL_NOT_ENOUGH.getCode());
        }
        //  校验单人可领取优惠劵是否到达上限
        if (couponCardMapper.selectCountByUserIdAndTemplateId(userId, couponTemplateId) > template.getQuota()) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_CARD_ADD_EXCEED_QUOTA.getCode());
        }
        // 增加优惠劵已领取量
        int updateTemplateCount = couponTemplateMapper.updateStatFetchNumIncr(couponTemplateId);
        if (updateTemplateCount == 0) { // 超过 CouponCardTemplate 发放量
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_CARD_ADD_EXCEED_QUOTA.getCode());
        }
        // 创建优惠劵
        // 1. 基本信息 + 领取情况
        CouponCardDO card = new CouponCardDO()
                .setTemplateId(couponTemplateId)
                .setTitle(template.getTitle())
                .setStatus(CouponCardStatusEnum.UNUSED.getValue())
                .setUserId(userId)
                .setTakeType(CouponCardTakeTypeEnum.BY_USER.getValue()); // TODO 需要改
        // 2. 使用规则
        card.setPriceAvailable(template.getPriceAvailable());
        setCouponCardValidTime(card, template);
        // 3. 使用效果
        card.setPreferentialType(template.getPreferentialType())
                .setPriceOff(template.getPriceOff())
                .setPercentOff(template.getPercentOff()).setDiscountPriceLimit(template.getDiscountPriceLimit());
        // 保存优惠劵模板到数据库
        couponCardMapper.insert(card);
        // 返回成功
        return card.getId();
    }

    /**
     * 用户取消使用优惠劵
     *
     * @param userId 用户编号
     * @param couponCardId 优惠劵编号
     */
    public void cancelUseCouponCard(Integer userId, Integer couponCardId) {
        // 查询优惠劵
        CouponCardDO card = couponCardMapper.selectById(couponCardId);
        if (card == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_NOT_EXISTS.getCode());
        }
        if (!CouponCardStatusEnum.USED.getValue().equals(card.getStatus())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_STATUS_NOT_USED.getCode());
        }
        // 更新优惠劵已使用
        int updateCount = couponCardMapper.updateByIdAndStatus(card.getId(), CouponCardStatusEnum.USED.getValue(),
                new CouponCardDO().setStatus(CouponCardStatusEnum.UNUSED.getValue())); // TODO 芋艿，usedTime 未设置空，后面处理。
        if (updateCount == 0) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_STATUS_NOT_USED.getCode());
        }
        // 有一点要注意，更新会未使用时，优惠劵可能已经过期了，直接让定时器跑过期，这里不做处理。
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

}

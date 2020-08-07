package cn.iocoder.mall.promotionservice.service.coupon;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.mall.promotion.api.enums.*;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.*;
import cn.iocoder.mall.promotionservice.convert.coupon.CouponCardConvert;
import cn.iocoder.mall.promotionservice.convert.coupon.CouponTemplateConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponCardDO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponCardMapper;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponTemplateMapper;
import cn.iocoder.mall.promotionservice.service.coupon.bo.*;
import cn.iocoder.common.framework.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class CouponService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;
    @Autowired
    private CouponCardMapper couponCardMapper;

    // ========== 优惠劵（码）模板 ==========

    public cn.iocoder.mall.promotionservice.service.coupon.bo.CouponTemplateBO getCouponTemplate(Integer couponTemplateId) {
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        return CouponTemplateConvert.INSTANCE.convert(template);
    }

    public CouponTemplatePageRespDTO getCouponTemplatePage(CouponTemplatePageReqDTO couponTemplatePageDTO) {
        CouponTemplatePageRespDTO couponTemplatePageBO = new CouponTemplatePageRespDTO();
        // 查询分页数据
        int offset = (couponTemplatePageDTO.getPageNo() - 1) * couponTemplatePageDTO.getPageSize();
        couponTemplatePageBO.setList(CouponTemplateConvert.INSTANCE.convertToDTO(couponTemplateMapper.selectListByPage(
                couponTemplatePageDTO.getType(), couponTemplatePageDTO.getTitle(),
                couponTemplatePageDTO.getStatus(), couponTemplatePageDTO.getPreferentialType(),
                offset, couponTemplatePageDTO.getPageSize())));
        // 查询分页总数
        couponTemplatePageBO.setTotal(couponTemplateMapper.selectCountByPage(
                couponTemplatePageDTO.getType(), couponTemplatePageDTO.getTitle(),
                couponTemplatePageDTO.getStatus(), couponTemplatePageDTO.getPreferentialType()));
        return couponTemplatePageBO;
    }

    public cn.iocoder.mall.promotionservice.service.coupon.bo.CouponTemplateBO addCouponCardTemplate(CouponCardTemplateAddBO couponCardTemplateAddDTO) {
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

    public Boolean updateCouponCodeTemplate(CouponCodeTemplateUpdateBO couponCodeTemplateUpdateDTO) {
        return null;
    }

    public Boolean updateCouponCardTemplate(CouponCardTemplateUpdateBO couponCardTemplateUpdateDTO) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponCardTemplateUpdateDTO.getId());
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 校验 CouponCardTemplate 是 CARD
        if (!CouponTemplateTypeEnum.CARD.getValue().equals(template.getType())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_CARD.getCode());
        }
        // 校验发放数量不能减少
        if (couponCardTemplateUpdateDTO.getTotal() < template.getTotal()) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_TOTAL_CAN_NOT_REDUCE.getCode());
        }
        // 更新优惠劵模板到数据库
        CouponTemplateDO updateTemplateDO = CouponTemplateConvert.INSTANCE.convert(couponCardTemplateUpdateDTO);
        couponTemplateMapper.update(updateTemplateDO);
        // 返回成功
        return true;
    }

    public Boolean updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId, Integer status) {
        // 校验 CouponCardTemplate 存在
        CouponTemplateDO template = couponTemplateMapper.selectById(couponTemplateId);
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        CouponTemplateDO updateTemplateDO = new CouponTemplateDO().setId(couponTemplateId).setStatus(status);
        couponTemplateMapper.update(updateTemplateDO);
        // 返回成功
        return true;
    }

    private Boolean checkCouponTemplateDateType(Integer dateType, Date validStartTime, Date validEndTime, Integer fixedBeginTerm, Integer fixedEndTerm) {
        /*if (CouponTemplateDateTypeEnum.FIXED_DATE.getValue().equals(dateType)) { // 固定日期
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
        }*/
        return true;
    }

    private Boolean checkCouponTemplatePreferentialType(Integer preferentialType, Integer percentOff,
                                                                      Integer priceOff, Integer priceAvailable) {
        /*if (PreferentialTypeEnum.PRICE.getValue().equals(preferentialType)) {
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
        }*/
        return true;
    }

    // ========== 优惠劵 ==========

    public CouponCardPageRespDTO getCouponCardPage(CouponCardPageReqDTO couponCardPageDTO) {
        CouponCardPageRespDTO pageBO = new CouponCardPageRespDTO();
        // 查询分页数据
        int offset = (couponCardPageDTO.getPageNo() - 1) * couponCardPageDTO.getPageSize();
        pageBO.setList(CouponCardConvert.INSTANCE.convertToDTO(couponCardMapper.selectListByPage(
                couponCardPageDTO.getUserId(), couponCardPageDTO.getStatus(),
                offset, couponCardPageDTO.getPageSize())));
        // 查询分页总数
        pageBO.setTotal(couponCardMapper.selectCountByPage(
                couponCardPageDTO.getUserId(), couponCardPageDTO.getStatus()));
        return pageBO;
    }

    @Transactional
    public CouponCardReqDTO addCouponCard(Integer userId, Integer couponTemplateId) {
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
        card.setCreateTime(new Date());
        couponCardMapper.insert(card);
        // 返回成功
        return CouponCardConvert.INSTANCE.convertToSingleDTO(card);
    }

    public Boolean useCouponCard(Integer userId, Integer couponCardId) {
        // 查询优惠劵
        CouponCardDO card = couponCardMapper.selectById(couponCardId);
        if (card == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_NOT_EXISTS.getCode());
        }
        if (!userId.equals(card.getUserId())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_ERROR_USER.getCode());
        }
        if (!CouponCardStatusEnum.UNUSED.getValue().equals(card.getStatus())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_STATUS_NOT_UNUSED.getCode());
        }
        if (DateUtil.isBetween(card.getValidStartTime(), card.getValidEndTime())) { // 为避免定时器没跑，实际优惠劵已经过期
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_STATUS_NOT_UNUSED.getCode());
        }
        // 更新优惠劵已使用
        int updateCount = couponCardMapper.updateByIdAndStatus(card.getId(), CouponCardStatusEnum.UNUSED.getValue(),
                new CouponCardDO().setStatus(CouponCardStatusEnum.USED.getValue()).setUsedTime(new Date()));
        if (updateCount == 0) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_STATUS_NOT_UNUSED.getCode());
        }
        return true;
    }

    public Boolean cancelUseCouponCard(Integer userId, Integer couponCardId) {
        // 查询优惠劵
        CouponCardDO card = couponCardMapper.selectById(couponCardId);
        if (card == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_NOT_EXISTS.getCode());
        }
        if (!userId.equals(card.getUserId())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_ERROR_USER.getCode());
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
        return true;
    }

    public CouponCardDetailRespDTO getCouponCardDetail(Integer userId, Integer couponCardId) {
        // 查询优惠劵
        CouponCardDO card = couponCardMapper.selectById(couponCardId);
        if (card == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_NOT_EXISTS.getCode());
        }
        if (!userId.equals(card.getUserId())) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_CARD_ERROR_USER.getCode());
        }
        // 查询优惠劵模板
        CouponTemplateDO template = couponTemplateMapper.selectById(card.getTemplateId());
        if (template == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS.getCode());
        }
        // 拼接结果
        CouponCardDetailRespDTO detail = CouponCardConvert.INSTANCE.convert2(card);
        detail.setRangeType(template.getRangeType());
        detail.setRangeValues(StringUtils.splitToInt(template.getRangeValues(), ","));
        return detail;
    }

    public List<CouponCardAvailableBO> getCouponCardList(Integer userId, List<CouponCardSpuBO> spus) {
        // 查询用户未使用的优惠劵列表
        List<CouponCardDO> cards = couponCardMapper.selectListByUserIdAndStatus(userId, CouponCardStatusEnum.UNUSED.getValue());
        if (cards.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询优惠劵模板集合
        Map<Integer, CouponTemplateDO> templates = couponTemplateMapper.selectListByIds(cards.stream().map(CouponCardDO::getTemplateId).collect(Collectors.toSet()))
                .stream().collect(Collectors.toMap(CouponTemplateDO::getId, template -> template));
        // 逐个判断是否可用
        List<CouponCardAvailableBO> availableCards = cards.stream().map(card -> {
            CouponCardAvailableBO availableCard = CouponCardConvert.INSTANCE.convertAvailBO(card, true);
            availableCard.setUnavailableReason(isMatch(card, templates.get(card.getTemplateId()), spus));
            availableCard.setAvailable(availableCard.getUnavailableReason() == null);
            return availableCard;
        }).collect(Collectors.toList());
        // 返回结果
        return availableCards;
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

    // 如果匹配，则返回 null 即可。
    private String isMatch(CouponCardDO card, CouponTemplateDO template, List<CouponCardSpuBO> spus) {
        int totalPrice = 0;
        if (RangeTypeEnum.ALL.getValue().equals(template.getRangeType())) {
            totalPrice = spus.stream().mapToInt(spu -> spu.getPrice() * spu.getQuantity()).sum();
        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(template.getRangeType())) {
            List<Integer> spuIds = StringUtils.splitToInt(template.getRangeValues(), ",");
            totalPrice = spus.stream().mapToInt(spu -> spuIds.contains(spu.getSpuId()) ? spu.getPrice() * spu.getQuantity() : 0).sum();
        } else if (RangeTypeEnum.PRODUCT_EXCLUDE_PART.getValue().equals(template.getRangeType())) {
            List<Integer> spuIds = StringUtils.splitToInt(template.getRangeValues(), ",");
            totalPrice = spus.stream().mapToInt(spu -> !spuIds.contains(spu.getSpuId()) ? spu.getPrice() * spu.getQuantity() : 0).sum();
        } else if (RangeTypeEnum.CATEGORY_INCLUDE_PART.getValue().equals(template.getRangeType())) {
            List<Integer> spuIds = StringUtils.splitToInt(template.getRangeValues(), ",");
            totalPrice = spus.stream().mapToInt(spu -> spuIds.contains(spu.getCategoryId()) ? spu.getPrice() * spu.getQuantity() : 0).sum();
        } else if (RangeTypeEnum.CATEGORY_EXCLUDE_PART.getValue().equals(template.getRangeType())) {
            List<Integer> spuIds = StringUtils.splitToInt(template.getRangeValues(), ",");
            totalPrice = spus.stream().mapToInt(spu -> !spuIds.contains(spu.getCategoryId()) ? spu.getPrice() * spu.getQuantity() : 0).sum();
        }
        // 总价为 0 时，说明优惠劵丫根不匹配
        if (totalPrice == 0) {
            return "优惠劵不匹配";
        }
        // 如果不满足金额
        if (totalPrice < card.getPriceAvailable()) {
            return String.format("差 %1$,.2f 元可用优惠劵", (card.getPriceAvailable() - totalPrice) / 100D);
        }
        return null;
    }

    // ========== 优惠码 ==========

    public CouponCardReqDTO useCouponCode(Integer userId, String code) {
        return null;
    }

}

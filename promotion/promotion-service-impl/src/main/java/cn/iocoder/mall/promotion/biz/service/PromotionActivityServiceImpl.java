package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.mall.promotion.api.PromotionActivityService;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityPageBO;
import cn.iocoder.mall.promotion.api.constant.PromotionActivityTypeEnum;
import cn.iocoder.mall.promotion.api.constant.RangeTypeEnum;
import cn.iocoder.mall.promotion.api.dto.PromotionActivityPageDTO;
import cn.iocoder.mall.promotion.biz.convert.PromotionActivityConvert;
import cn.iocoder.mall.promotion.biz.dao.PromotionActivityMapper;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.PromotionActivityService.version}")
public class PromotionActivityServiceImpl implements PromotionActivityService {

    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    @Override
    public List<PromotionActivityBO> getPromotionActivityListBySpuId(Integer spuId, Collection<Integer> activityStatuses) {
        return this.getPromotionActivityListBySpuIds(Collections.singleton(spuId), activityStatuses);
    }

    @Override
    public List<PromotionActivityBO> getPromotionActivityListBySpuIds(Collection<Integer> spuIds, Collection<Integer> activityStatuses) {
        if (spuIds.isEmpty() || activityStatuses.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询指定状态的促销活动
        List<PromotionActivityDO> activityList = promotionActivityMapper.selectListByStatus(activityStatuses);
        if (activityList.isEmpty()) {
            return Collections.emptyList();
        }
        // 匹配商品
        for (Iterator<PromotionActivityDO> iterator = activityList.iterator(); iterator.hasNext();) {
            PromotionActivityDO activity = iterator.next();
            boolean matched = false;
            for (Integer spuId : spuIds) {
                if (PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())) {
                    matched = isSpuMatchTimeLimitDiscount(spuId, activity);
                } else if (PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType())) {
                    matched = isSpuMatchFullPrivilege(spuId, activity);
                }
                if (matched) {
                    break;
                }
            }
            // 不匹配，则进行移除
            if (!matched) {
                iterator.remove();
            } else { // 匹配，则做一些后续的处理
                // 如果是限时折扣，移除不在 spuId 数组中的折扣规则
                if (PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())) {
                    activity.getTimeLimitedDiscount().getItems().removeIf(item -> !spuIds.contains(item.getSpuId()));
                }
            }
        }
        // 返回最终结果
        return PromotionActivityConvert.INSTANCE.convertToBO(activityList);
    }

    @Override
    public PromotionActivityPageBO getPromotionActivityPage(PromotionActivityPageDTO promotionActivityPageDTO) {
        PromotionActivityPageBO promotionActivityPageBO = new PromotionActivityPageBO();
        // 查询分页数据
        int offset = (promotionActivityPageDTO.getPageNo() - 1) * promotionActivityPageDTO.getPageSize();
        promotionActivityPageBO.setList(PromotionActivityConvert.INSTANCE.convertToBO(promotionActivityMapper.selectListByPage(
                promotionActivityPageDTO.getTitle(), promotionActivityPageDTO.getActivityType(),
                promotionActivityPageDTO.getStatuses(),
                offset, promotionActivityPageDTO.getPageSize())));
        // 查询分页总数
        promotionActivityPageBO.setTotal(promotionActivityMapper.selectCountByPage(
                promotionActivityPageDTO.getTitle(), promotionActivityPageDTO.getActivityType(),
                promotionActivityPageDTO.getStatuses()));
        return promotionActivityPageBO;
    }

    private boolean isSpuMatchTimeLimitDiscount(Integer spuId, PromotionActivityDO activity) {
        Assert.isTrue(PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType()),
                "传入的必须的促销活动必须是限时折扣");
        return activity.getTimeLimitedDiscount().getItems().stream()
                .anyMatch(item -> spuId.equals(item.getSpuId()));
    }

    private boolean isSpuMatchFullPrivilege(Integer spuId, PromotionActivityDO activity) {
        Assert.isTrue(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()),
                "传入的必须的促销活动必须是满减送");
        PromotionActivityDO.FullPrivilege fullPrivilege = activity.getFullPrivilege();
        if (RangeTypeEnum.ALL.getValue().equals(fullPrivilege.getRangeType())) {
            return true;
        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(fullPrivilege.getRangeType())) {
            return fullPrivilege.getRangeValues().contains(spuId);
        } else {
            throw new IllegalArgumentException(String.format("促销活动(%s) 可用范围的类型是不正确", activity.toString()));
        }
    }

}

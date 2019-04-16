package cn.iocoder.mall.promotion.biz.service;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.PromotionActivityService;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import cn.iocoder.mall.promotion.api.constant.PromotionActivityTypeEnum;
import cn.iocoder.mall.promotion.api.constant.RangeTypeEnum;
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
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class PromotionActivityServiceImpl implements PromotionActivityService {

    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    @Override
    public CommonResult<List<PromotionActivityBO>> getPromotionActivityListBySpuId(Integer spuId, Collection<Integer> activityStatuses) {
        return this.getPromotionActivityListBySpuIds(Collections.singleton(spuId), activityStatuses);
    }

    @Override
    public CommonResult<List<PromotionActivityBO>> getPromotionActivityListBySpuIds(Collection<Integer> spuIds, Collection<Integer> activityStatuses) {
        if (spuIds.isEmpty() || activityStatuses.isEmpty()) {
            return CommonResult.success(Collections.emptyList());
        }
        // 查询指定状态的促销活动
        List<PromotionActivityDO> activityList = promotionActivityMapper.selectListByStatus(activityStatuses);
        if (activityList.isEmpty()) {
            return CommonResult.success(Collections.emptyList());
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
            }
        }
        // 返回最终结果
        return CommonResult.success(PromotionActivityConvert.INSTANCE.convertToBO(activityList));
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
